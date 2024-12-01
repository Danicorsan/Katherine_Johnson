package app.domain.invoicing.product

import app.domain.invoicing.product.classHolders.ICategoria
import app.domain.invoicing.product.classHolders.ISeccion
import app.domain.invoicing.product.complements.AlarmaProducto
import app.domain.invoicing.product.complements.notes.Notas
import app.domain.invoicing.product.complements.tags.Etiquetas
import kotlinx.datetime.LocalDate

data class Producto(
    private val _id : UInt? = null,
    var codigo : String,
    var nombre : String,
    var nombreCorto : String,
    var descripcion : String?,
    var numeroSerie : String,
    var codigoModelo : String,
    var tipoProducto : String,
    var categoria : ICategoria,
    var seccion : ISeccion,
    var estado : EstadoProducto = EstadoProducto.new,
    private var _cantidad : UInt,
    var precio : Double,
    var imagenProducto : ByteArray? = null,
    val fechaAdquisicion : LocalDate,
    var fechaBaja : LocalDate? = null,
    val notas : Notas = Notas(),
    val etiquetas : Etiquetas = Etiquetas(),
    var stockMinimo : UInt? = null
)
{
    private var _alarmaStockMinimo = AlarmaProducto.StockMinimo(this)

    val AlarmaStockMinimo : AlarmaProducto.StockMinimo
        get() = _alarmaStockMinimo

    val id : UInt?
        get() = _id

    var cantidad : UInt
        get() = _cantidad
        private set(nuevaCantidad) {
            _cantidad = nuevaCantidad
            _alarmaStockMinimo.ejecutarComprobacion()
        }
}