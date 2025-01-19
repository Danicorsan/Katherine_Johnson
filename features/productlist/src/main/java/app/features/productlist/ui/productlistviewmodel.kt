package app.features.productlist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.product.Product
import app.domain.invoicing.repository.ProductRepository
import app.features.productlist.ui.base.ProductListNavigationEvents
import app.features.productlist.ui.base.ProductListState
import kotlinx.coroutines.launch

class ProductListViewModel(private val productListNavigationEvents : ProductListNavigationEvents) : ViewModel() {

    var productListState by mutableStateOf(ProductListState())
    private set

    init {
        productListState = productListState.copy(isLoading = true)
        viewModelScope.launch {
            ProductRepository.getAllProducts().collect{
                productListState = productListState.copy(
                    isLoading = false,
                    productList = it.values.toList()
                )
            }
        }
    }

    fun onCreateProduct(){
        productListNavigationEvents.onCreateProductNav()
    }

    fun onDeleteProduct(product: Product){
    }

    fun onSeeProductDetails(product: Product){
        productListNavigationEvents.onSeeProductDetailsNav(product)
    }

    fun onEditProduct(product : Product){
        productListNavigationEvents.onEditProductNav(product)
    }

    fun onGoBack(){
        productListNavigationEvents.onGoBackNav()
    }

}