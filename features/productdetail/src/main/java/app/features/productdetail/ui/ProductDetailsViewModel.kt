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

    private var productId : Int = -1
    private lateinit var onGoBackNavigation : () -> Unit
    private lateinit var onEditProductNav: (Int) -> Unit

    fun loadDataAndStablishNavigationEvent(
        productId : Int,
        onGoBackNavigationAction : () -> Unit,
        onEditProductNav : (Int) -> Unit
    ){
        this.onGoBackNavigation = onGoBackNavigationAction
        this.onEditProductNav = onEditProductNav
        this.productId = productId
        viewModelScope.launch {
           val response = ProductRepository.getProductById(productId)
            if (response is BaseResult.Success){
                productDetailsState = productDetailsState.copy(
                    product = response.data
                )
            }
            else
                onGoBackNavigationAction()
        }
    }

    fun onEditProduct(){
        onEditProductNav(productId)
    }

    fun onGoBackNavigationClick(){
        onGoBackNavigation()
    }

    fun onDeleteProduct(){
        productDetailsState = productDetailsState.copy(
            productBeingDeleted = true
        )
    }

    fun onConfirmDeleteProduct(){
        productDetailsState = productDetailsState.copy(
            product = null
        )
        viewModelScope.launch {
            ProductRepository.removeProduct(productId)
            onGoBackNavigation()
        }
    }

    fun onDismissDeleteProduct(){
        productDetailsState = productDetailsState.copy(
            productBeingDeleted = false
        )
    }
}