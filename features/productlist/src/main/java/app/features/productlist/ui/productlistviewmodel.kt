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

/**
 * El [ViewModel] correspondiente a la pantalla
 * [app.features.productlist.ui.ProductListScreen]
 *
 */
@HiltViewModel
class ProductListViewModel @Inject constructor() : ViewModel() {

    var productListState by mutableStateOf(ProductListState())
    private set

    private var productToDelete : Product? = null

    private lateinit var productListNavigationEvents : ProductListNavigationEvents

    /**
     * Carga todos los productos de la base de datos.
     *
     */
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

    /**
     * Establece todas las acciones de navegación en el [ProductListViewModel]
     *
     * @param navigationsEvents El objeto [ProductListNavigationEvents]
     * con las acciones de navegación
     */
    fun stablishNavigationEvents(navigationsEvents : ProductListNavigationEvents){
        this.productListNavigationEvents = navigationsEvents
    }

    /**
     * Evento cuando el usuario quiere crear un nuevo producto.
     *
     */
    fun onCreateProduct(){
        productListNavigationEvents.onCreateProductNav()
    }

    /**
     * Evento cuando el usuario quiere eliminar un producto.
     *
     * @param product El [Product] que se quiere eliminar.
     */
    fun onDeleteProduct(product: Product){
        println("Producto siendo eliminado")
        productToDelete = product
        productListState = productListState.copy(
            productIsBeingDeleted = true
        )
    }

    /**
     * Evento cuando el usuario quiere ver los detalles de un producto.
     *
     * @param product El [Product] del cual el usuario quiere conocer los detalles.
     */
    fun onSeeProductDetails(product: Product){
        productListNavigationEvents.onSeeProductDetailsNav(product.id!!)
    }

    /**
     * Evento cuando el usuario quiere volver a la pantalla anterior.
     *
     */
    fun onGoBack(){
        productListNavigationEvents.onGoBackNav()
    }

    /**
     * Evento cuando el usuario confirma que quiere eliminar el producto seleccionado.
     *
     */
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

    /**
     * Evento cuando el usuario cancela la eliminación del producto seleccionado.
     *
     */
    fun onDissmissDeleteProduct() {
        productToDelete = null
        productListState = productListState.copy(
            productIsBeingDeleted = false
        )
    }
}