package app.features.productcreation.ui.edition

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.features.productcreation.ui.base.DropDownItems
import app.features.productcreation.ui.base.ProductViewState

class ProductEditionViewModel : ViewModel() {
    var estadoProducto by mutableStateOf(ProductViewState(
        code = "CodigoDelProdcuto",
        shortName = "ProductoCortoNombre"
    ))
        private set
    var desplegable by mutableStateOf(DropDownItems())
        private set

    fun acceptProductEdition(){

    }

    fun leavePage(){

    }

    fun nameChanged(newName : String){
        estadoProducto = estadoProducto.copy(code = newName)
    }
}