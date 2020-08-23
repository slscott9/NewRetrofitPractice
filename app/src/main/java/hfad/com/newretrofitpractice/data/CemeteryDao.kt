package hfad.com.newretrofitpractice.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CemeteryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCemetery(cemetery: Cemetery)

    @Query("select * from test_cem_table where row_number= :rowNum")
    suspend fun getCemeteryWithRowNum(rowNum: Int) : Cemetery

    @Query("select * from test_cem_table")
    fun getAllCemeteries() : LiveData<List<Cemetery>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCemeteryNetworkList(vararg cemeteryNetworkList: Cemetery)
}