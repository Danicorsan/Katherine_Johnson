package app.features.productcreation.ui.base

data class ProductoEstado(
    val codigo : String = "",
    val nombre : String = "",
    val nombreCorto : String = "",
    val descripcion : String = "",
    val numeroSerie : String = "",
    val codigoModelo : String = "",
    val tipoProducto : String = "",
    val cantidad : String = "",
    val precio : String = "",
    var stockMinimo: String = "",
    var despliegeMenuCategorias : Boolean = false,
)