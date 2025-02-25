package app.features.productlist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.network.BaseResult
import app.domain.invoicing.product.Product
import app.domain.invoicing.repository.ProductRepository
import app.features.productlist.ui.base.ProductListNavigationEvents
import app.features.productlist.ui.base.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor() : ViewModel() {

    var productListState by mutableStateOf(ProductListState())
    private set

    private var productToDelete : Product? = null

    private lateinit var productListNavigationEvents : ProductListNavigationEvents

    fun getAllProductList(){
        productListState = productListState.copy(isLoading = true)
        viewModelScope.launch {
            val result = ProductRepository.getAllProducts()
            if (result is BaseResult.Success){
                result.data.collect{
                    productListState = productListState.copy(
                        isLoading = false,
                        productList = it.values.toList()
                    )
                }
            } else {
                productListState = productListState.copy(
                    isLoading = false,
                    productList = emptyList()
                )
            }
        }
    }

    fun stablishNavigationEvents(navigationsEvents : ProductListNavigationEvents){
        this.productListNavigationEvents = navigationsEvents
    }

    fun onCreateProduct(){
        productListNavigationEvents.onCreateProductNav()
    }

    fun onDeleteProduct(product: Product){
        println("Producto siendo eliminado")
        productToDelete = product
        productListState = productListState.copy(
            productIsBeingDeleted = true
        )
    }

    fun onSeeProductDetails(product: Product){
        productListNavigationEvents.onSeeProductDetailsNav(product.id!!)
    }

    fun onGoBack(){
        productListNavigationEvents.onGoBackNav()
    }

    fun onConfirmDeleteProduct(){
        productListState = productListState.copy(
            isLoading = true,
            productIsBeingDeleted = false
        )
        viewModelScope.launch {
            ProductRepository.removeProduct(productToDelete!!.id!!)//Siempre habrá un producto con un id
            val result = ProductRepository.getAllProducts()
            if (result is BaseResult.Success){
                result.data.collect{
                    productListState = productListState.copy(
                        isLoading = false,
                        productList = it.values.toList()
                    )
                }
            } else {
                productListState = productListState.copy(
                    isLoading = false,
                    productList = emptyList()
                )
            }
        }
    }

    fun onDissmissDeleteProduct() {
        productToDelete = null
        productListState = productListState.copy(
            productIsBeingDeleted = false
        )
    }

}