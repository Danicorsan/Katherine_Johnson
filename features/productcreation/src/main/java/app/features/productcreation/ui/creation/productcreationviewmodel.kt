package app.features.productcreation.ui.creation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.features.productcreation.ui.base.DropDownItems
import app.features.productcreation.ui.base.ProductViewState

class ProductCreationViewModel : ViewModel() {
    var productViewState by mutableStateOf(ProductViewState())
        private set

    var dropDownItems by mutableStateOf(DropDownItems())

    fun createProduct(){

    }

    fun leavePage(){

    }

    fun nameChanged(nuevoNombre : String){
        productViewState = productViewState.copy(code = nuevoNombre)
    }
}