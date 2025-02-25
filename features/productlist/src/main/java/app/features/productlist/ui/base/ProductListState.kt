package app.features.productlist.ui.base

import app.domain.invoicing.product.Product

/**
 * Un data class que alamcena el estado de la vista
 * [app.features.productlist.ui.ProductListScreen]
 *
 * @property productList La lista de [Product] a mostrar.
 * @property productIsBeingDeleted Indica si hay un producto siendo eliminado.
 * @property isLoading Indica si la pantalla está cargando.
 */
data class ProductListState(
    val productList : List<Product> = emptyList(),
    val productIsBeingDeleted : Boolean = false,
    val isLoading : Boolean = false
)
