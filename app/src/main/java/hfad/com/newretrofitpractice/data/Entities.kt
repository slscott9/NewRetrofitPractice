package hfad.com.newretrofitpractice.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import hfad.com.newretrofitpractice.domain.CemeteryDomainModel

@Entity(tableName = "test_cem_table")
data class Cemetery(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "row_number")
    val id: Int,
    val cemeteryName: String,
    val cemeteryLocation: String,
    val cemeteryState: String,
    val cemeteryCounty: String,
    val township: String,
    val range: String,
    val spot: String,
    val firstYear: String,
    val section: String

)

@Entity(tableName = "test_grave_table")
data class Grave(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val cemeteryId: Int,
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val deathDate: String,
    val marriageYear: String,
    val comment: String,
    val graveNumber: String
)


fun List<Cemetery>.asDomainModel(): List<CemeteryDomainModel> {
    return map {
        CemeteryDomainModel(
            id = it.id,
            cemeteryName = it.cemeteryName,
            cemeteryCounty = it.cemeteryCounty,
            cemeteryState = it.cemeteryState,
            cemeteryLocation = it.cemeteryLocation,
            township = it.township,
            range = it.range,
            spot = it.spot,
            firstYear = it.firstYear,
            section = it.section
        )
    }
}