package hfad.com.newretrofitpractice.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hfad.com.newretrofitpractice.data.Cemetery
import hfad.com.newretrofitpractice.data.CemeteryRepo
import hfad.com.newretrofitpractice.data.CemeteryRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


/*
    1. On Creation of ViewModels init calls repos refresh videos which makes network GET request and inserts response into database (as Database models)
    2. Repo's allCemeteries set to all cemeteries from the database. These are transformed to DomainModels for the view model
    3. ViewModels sets its allCemeteries property to repo's allCemeteries property
 */
class CemeteryListActivityViewModel(application: Application): AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val cemDao = CemeteryRoomDatabase.getDatabase(application, viewModelScope).cemDao()
    private val repository = CemeteryRepo(cemDao)

    private val _newCemteryIdentifier = MutableLiveData<Int>()
    val newCemeteryIdentifier: LiveData<Int>
    get() = _newCemteryIdentifier

    init {
        viewModelScope.launch {
            repository.refreshVideos()  //Calls network get request inserts list of network objects into database as database models
        }
        _newCemteryIdentifier.value = 1000

    }

    val allCemeteries = repository.allCemeteries

    private val _cemetery = MutableLiveData<Cemetery>()
    val cemetery : LiveData<Cemetery> = _cemetery

    fun insertCemetery(cemetery: Cemetery){
        viewModelScope.launch {
            repository.insertCemetery(cemetery)
        }
    }

    fun getCemeteryWithRowNum(rowNum : Int) {
        viewModelScope.launch {
             _cemetery.value = repository.getCemeteryWithRowNum(rowNum)
        }
    }

    fun sendNewCemeteryToNetwork(cemetery: Cemetery){
        viewModelScope.launch {
            repository.sendNewCemeteryToNetwork(cemetery){

            }
        }
    }

    fun incrementNewCemeteryIdentifier() {
        _newCemteryIdentifier.value?.let {
            _newCemteryIdentifier.value = _newCemteryIdentifier.value!!.plus(1)
        }
        Log.i("CemeteryViewModel", "Sent cemetery to network the counter is ${newCemeteryIdentifier.value}")

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



}