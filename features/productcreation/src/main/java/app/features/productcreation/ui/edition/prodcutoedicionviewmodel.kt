package app.features.productcreation.ui.edition

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.features.productcreation.ui.base.Desplegables
import app.features.productcreation.ui.base.ProductoEstado

class ProductoEdicionViewModel : ViewModel() {
    var estadoProducto by mutableStateOf(ProductoEstado(
        codigo = "CodigoDelProdcuto",
        nombreCorto = "ProductoCortoNombre"
    ))
        private set
    var desplegable by mutableStateOf(Desplegables())
        private set

    fun confirmarEdicionProducto(){

    }

    fun abandonarPagina(){

    }

    fun nombreCambiado(nuevoNombre : String){
        estadoProducto = estadoProducto.copy(codigo = nuevoNombre)
    }
}