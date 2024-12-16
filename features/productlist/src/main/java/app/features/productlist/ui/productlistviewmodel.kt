package app.features.productlist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.product.Product
import app.domain.invoicing.repository.ProductRepository
import app.features.productlist.ui.base.ProductListState
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class ProductListViewModel : ViewModel() {

    var productListState by mutableStateOf(ProductListState())

    val isReady = AtomicBoolean(false)

    fun onAddProduct(){

    }

    fun onDeleteProduct(product: Product){

    }

    fun onSeeProductDetails(product: Product){

    }

    fun onEditProduct(product : Product){

    }

    fun onGoBack(){

    }

    fun getReady(){
        if (!isReady.get()){
            productListState = productListState.copy(isLoading = true)
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