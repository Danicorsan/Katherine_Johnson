package app.features.productcreation.ui.base

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
    var minimunStock: String = "",
)