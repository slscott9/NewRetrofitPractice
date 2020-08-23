package hfad.com.newretrofitpractice.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import hfad.com.newretrofitpractice.domain.CemeteryDomainModel
import hfad.com.newretrofitpractice.network.NetworkCemetery
import hfad.com.newretrofitpractice.network.ServiceApi
import hfad.com.newretrofitpractice.network.ServiceBuilder
import hfad.com.newretrofitpractice.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

/*
       Repository calls ServiceApi's GET, converts the CemeteryNetworkContainer into database models and inserts into database.
       Repo sets allCemeteries to Transformed list of database objects to List of DomainModels
       ViewModel sets its allCemeteries to repo's allCemeteries
*/

class CemeteryRepo(private val cemDao: CemeteryDao) {

//List of Database Models converted to Domain models. ViewModels uses allCemeteries
    val allCemeteries: LiveData<List<CemeteryDomainModel>> = Transformations.map(cemDao.getAllCemeteries()){
        it.asDomainModel()
    }

    suspend fun insertCemetery(cemetery: Cemetery){
        cemDao.insertCemetery(cemetery)
    }

    suspend fun getCemeteryWithRowNum(rowNum : Int): Cemetery{
       return cemDao.getCemeteryWithRowNum(rowNum)
    }


    //Called from ViewModel's init block, this ensures we have an up to date list of cemeteries when view model is created
    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val retrofit = ServiceBuilder.buildService(ServiceApi::class.java)
            val cemeteryNetworkList = retrofit.getCemeteriesFromNetwork().await()
            cemDao.insertCemeteryNetworkList(*cemeteryNetworkList.asDatabaseModel())
        }
    }


    fun sendNewCemeteryToNetwork(cemetery: Cemetery, onResult: (Cemetery?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ServiceApi::class.java)
        retrofit.sendNewCemeteryToNetwork(
            cemId = cemetery.id.toString(),
            cemName = cemetery.cemeteryName,
            location = cemetery.cemeteryLocation,
            county = cemetery.cemeteryCounty,
            township = cemetery.township,
            range = cemetery.range,
            spot = cemetery.spot,
            yearFounded = cemetery.firstYear,
            section = cemetery.section,
            state = cemetery.cemeteryState).enqueue(

            object : retrofit2.Callback<Cemetery> {
                override fun onFailure(call: Call<Cemetery>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<Cemetery>, response: Response<Cemetery>) {
                    val addedCemetery = response.body()
                    onResult(addedCemetery)
                }
            }
        )
    }

}