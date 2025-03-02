package app.domain.invoicing.product


import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.domain.invoicing.category.Category
import app.domain.invoicing.product.complements.ProductAlarm
import app.domain.invoicing.product.complements.tags.Tags
import app.domain.invoicing.section.Section
import kotlinx.datetime.Instant

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val id : Int? = null,
    var code : String,
    var name : String,
    var shortName : String,
    var description : String = "",
    var serialNumber : String,
    var modelCode : String,
    var productType : String,
    var category : Category,
    var section : Section,
    var state : ProductState = ProductState.new,
    var image : Uri? = null,
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