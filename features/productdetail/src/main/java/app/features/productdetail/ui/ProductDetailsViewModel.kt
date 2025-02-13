package app.features.productdetail.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.network.BaseResult
import app.domain.invoicing.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductDetailsViewModel : ViewModel(){
    var productDetailsState by mutableStateOf(ProductDetailsState())
    private set

    private lateinit var onGoBackNavigation : () -> Unit

    fun getReady(
        idProduct : Int,
        onGoBackNavigationAction : () -> Unit
    ){
        this.onGoBackNavigation = onGoBackNavigationAction
        viewModelScope.launch {
           val response = ProductRepository.getProductById(idProduct)
            if (response is BaseResult.Success){
                productDetailsState = productDetailsState.copy(
                    product = response.data
                )
            }
            else
                onGoBackNavigationAction()
        }
    }

    fun onGoBackNavigationButtonClick(){
        onGoBackNavigation()
    }
}