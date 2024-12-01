package app.features.productcreation.ui.base

data class EventosProducto(
    val confirmacionSobreProducto : () -> Unit,
    val abandonarPagina : () -> Unit,
    val nombreCambiado : (String) -> Unit
)