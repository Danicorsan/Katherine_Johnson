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

/**
 * El [ViewModel] correspondiente a la pantalla [app.features.productdetail.ui.ProductDetailScreen].
 *
 */
@HiltViewModel
class ProductDetailsViewModel @Inject constructor() : ViewModel(){
    var productDetailsState by mutableStateOf(ProductDetailsState())
    private set

    private var productId : Int = -1
    private lateinit var onGoBackNavigation : () -> Unit
    private lateinit var onEditProductNav: (Int) -> Unit

    /**
     * Establece los eventos necesarios y carga los datos del productos.
     *
     * @param productId El id del producto del cual queremos obtener sus datos.
     * @param onGoBackNavigationAction La acción para navegar a la pantalla anterior.
     * @param onEditProductNav La acción para navegar a la pantalla de edición de un producto.
     */
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

    /**
     * Evento lanzado cuando el usuario quiere editar un producto.
     *
     */
    fun onEditProduct(){
        onEditProductNav(productId)
    }

    /**
     * Evento lanzado cuando el usuario quiere volver a la pantalla anterior.
     *
     */
    fun onGoBackNavigationClick(){
        onGoBackNavigation()
    }

    /**
     * Evento lanzado cuando el usuario quiere eliminar un producto.
     *
     */
    fun onDeleteProduct(){
        productDetailsState = productDetailsState.copy(
            productBeingDeleted = true
        )
    }

    /**
     * Evento lanzado cuando el usuario confirma que quiere eliminar un producto.
     *
     */
    fun onConfirmDeleteProduct(){
        productDetailsState = productDetailsState.copy(
            product = null
        )
        viewModelScope.launch {
            ProductRepository.removeProduct(productId)
            onGoBackNavigation()
        }
    }

    /**
     * Evento lanzado cuando el usuario decide cancelar la eliminación del producto.
     *
     */
    fun onDismissDeleteProduct(){
        productDetailsState = productDetailsState.copy(
            productBeingDeleted = false
        )
    }
}