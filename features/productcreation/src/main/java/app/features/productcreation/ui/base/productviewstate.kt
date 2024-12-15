package app.features.productcreation.ui.base

import kotlinx.datetime.Instant

data class ProductViewState(
    val code : String = "",
    val name : String = "",
    val shortName : String = "",
    val description : String = "",
    val serialNumber : String = "",
    val modelCode : String = "",
    val productType : String = "",
    val stock : String = "",
    val price : String = "",
    val minimunStock: String = "",
    val adquisitionDate : Instant? = null,
    val adquisitionDateRepresentation : String? = null,
    val discontinuationDate : Instant? = null,
    val discontinuationDateRepresentation : String? = null,
    val isLoading : Boolean = false
)