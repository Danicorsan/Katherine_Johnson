package app.features.productcreation.ui.creation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.features.productcreation.ui.base.Desplegables
import app.features.productcreation.ui.base.ProductoEstado

class ProductoCreacionViewModel : ViewModel() {
    var estadoProducto by mutableStateOf(ProductoEstado())
        private set

    var desplegables by mutableStateOf(Desplegables())

    fun crearProducto(){

    }

    fun abandonarPagina(){

    }

    fun nombreCambiado(nuevoNombre : String){
        estadoProducto = estadoProducto.copy(codigo = nuevoNombre)
    }
}