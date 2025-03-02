package app.domain.invoicing.product

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import app.domain.invoicing.category.Category
import app.domain.invoicing.product.complements.ProductAlarm
import app.domain.invoicing.product.complements.tags.Tags
import app.domain.invoicing.section.Section
import kotlinx.datetime.Instant


/**
 * Representa un producto.
 *
 * @property id El id del producto.
 * @property code El código del producto.
 * @property name El nombre del producto.
 * @property shortName El nombre corto del producto.
 * @property description La descripción del producto.
 * @property serialNumber El número de serie del producto.
 * @property modelCode El código de modelo del producto.
 * @property productType El tipo de producto del modelo.
 * @property category La categoria del producto.
 * @property section La sección a la que pertenece el producto.
 * @property state El estado del producto.
 * @property image Una [Uri] a la imagen del producto.
 * @property stock El stock del producto,
 * @property price El precio del producto.
 * @property acquisitionDate La fecha de aquisicion.
 * @property discontinuationDate La fecha de baja.
 * @property notes Las notas.
 * @property tags Las etiquetas del producto.
 * @property minimunStock El stock mínimo.
 */
@Entity
data class Product(
    @PrimaryKey
    val id : Int? = null,
    var code : String,
    var name : String,
    @ColumnInfo(name = "short_name")
    var shortName : String,
    var description : String = "",
    @ColumnInfo(name = "serial_number")
    var serialNumber : String,
    @ColumnInfo(name = "model_code")
    var modelCode : String,
    @ColumnInfo(name = "product_type")
    var productType : String,
    var category : Category,
    var section : Section,
    var state : ProductState = ProductState.new,
    var image : Uri? = null,
    var stock : UInt,
    var price : Double,
    @ColumnInfo(name = "adquisition_date")
    val acquisitionDate : Instant,
    @ColumnInfo(name = "discontinuation_date")
    var discontinuationDate : Instant? = null,
    val notes : String = "",
    val tags : Tags = Tags(),
    @ColumnInfo(name = "minimun_stock")
    var minimunStock : UInt? = null
)
{
}