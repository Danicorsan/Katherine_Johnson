package app.features.productdetail.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.network.BaseResult
import app.domain.invoicing.repository.ProductRepository
import app.features.productdetail.ui.base.ProductDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor() : ViewModel(){
    var productDetailsState by mutableStateOf(ProductDetailsState())
    private set

    private lateinit var onGoBackNavigation : () -> Unit

    fun loadDataAndStablishNavigationEvent(
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