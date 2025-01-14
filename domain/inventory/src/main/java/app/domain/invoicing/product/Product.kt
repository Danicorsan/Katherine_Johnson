package app.domain.invoicing.product


import app.domain.invoicing.category.Category
import app.domain.invoicing.product.complements.ProductAlarm
import app.domain.invoicing.product.complements.tags.Tags
import kotlinx.datetime.Instant

data class Product(
    val id : Int? = null,
    var code : String,
    var name : String,
    var shortName : String,
    var description : String = "",
    var serialNumber : String,
    var modelCode : String,
    var productType : String,
    var category : Category,
    var section : String,
    var state : ProductState = ProductState.new,
    var stock : UInt,
    var price : Double,
    var productImage : String? = null,
    val acquisitionDate : Instant,
    var discontinuationDate : Instant? = null,
    val notes : String = "",
    val tags : Tags = Tags(),
    var minimunStock : UInt? = null
)
{
    private var _minimunAlarmStock = ProductAlarm.MinimunStock(this)

    val MinimunStockAlarm : ProductAlarm.MinimunStock
        get() = _minimunAlarmStock

}