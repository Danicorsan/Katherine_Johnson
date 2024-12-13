package app.features.productlist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProductListViewModel : ViewModel() {
    var products by mutableStateOf(listOf(
        "Esponjas",
        "Radios",
        "Manzanas",
        "Sacacorchos",
        "Coche"
    ))
    private set

    fun addProduct(){

    }

    fun seeProductDetails(product: String){

    }
}