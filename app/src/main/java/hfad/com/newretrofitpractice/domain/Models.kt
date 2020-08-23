package hfad.com.newretrofitpractice.domain

data class CemeteryDomainModel(
    val id: Int? = 100,
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