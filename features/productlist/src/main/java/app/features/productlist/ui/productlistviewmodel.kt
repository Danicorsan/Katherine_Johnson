package app.features.productlist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.product.Product
import app.domain.invoicing.repository.ProductRepository
import app.features.productlist.ui.base.NavigationEvents
import app.features.productlist.ui.base.ProductListState
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class ProductListViewModel : ViewModel() {

    var productListState by mutableStateOf(ProductListState())
    private set

    private val isReady = AtomicBoolean(false)

    lateinit var navigationEvents : NavigationEvents
    private set

    fun onAddProduct(){
        isReady.set(false)
        navigationEvents.onAddProductNav()
    }

    fun onDeleteProduct(product: Product){
    }

    fun onSeeProductDetails(product: Product){
        isReady.set(false)
        navigationEvents.onSeeProductDetailsNav(product)
    }

    fun onEditProduct(product : Product){
        isReady.set(false)
        navigationEvents.onEditProductNav(product)
    }

    fun onGoBack(){
        isReady.set(false)
        navigationEvents.onGoBackNav()
    }

    fun getReady(navigationEvents: NavigationEvents){
        if (!isReady.get()){
            productListState = productListState.copy(isLoading = true)
            this.navigationEvents = navigationEvents
            viewModelScope.launch {
                ProductRepository.getAllProducts().collect{
                    isReady.set(true)
                    productListState = productListState.copy(
                        isLoading = false,
                        productList = it.values.toList()
                    )
                }
            }
        }
    }
}