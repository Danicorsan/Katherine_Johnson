package app.features.productlist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ListadoProductoViewModel : ViewModel() {
    var productos by mutableStateOf(listOf(
        "Esponjas",
        "Radios",
        "Manzanas",
        "Sacacorchos",
        "Coche"
    ))
    private set

    fun añadirProducto(){

    }

    fun verDetallesProducto(producto: String){

    }
}