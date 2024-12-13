package app.domain.invoicing.data.product


import app.domain.invoicing.category.Category
import app.domain.invoicing.data.product.complements.AlarmaProducto
import app.domain.invoicing.data.product.complements.notes.Notas
import app.domain.invoicing.data.product.complements.tags.Etiquetas
import kotlinx.datetime.Instant

data class Producto(
    val id : Int? = null,
    var codigo : String,
    var nombre : String,
    var nombreCorto : String,
    var descripcion : String?,
    var numeroSerie : String,
    var codigoModelo : String,
    var tipoProducto : String,
    var categoria : Category,
    var seccion : String,
    var estado : EstadoProducto = EstadoProducto.new,
    var cantidad : UInt,
    var precio : Double,
    var imagenProducto : ByteArray? = null,
    val fechaAdquisicion : Instant,
    var fechaBaja : Instant? = null,
    val notas : Notas = Notas(),
    val etiquetas : Etiquetas = Etiquetas(),
    var stockMinimo : UInt? = null
)
{
    private var _alarmaStockMinimo = AlarmaProducto.StockMinimo(this)

    val AlarmaStockMinimo : AlarmaProducto.StockMinimo
        get() = _alarmaStockMinimo

}