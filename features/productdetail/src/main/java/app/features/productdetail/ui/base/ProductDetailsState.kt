package app.features.productdetail.ui.base

import app.domain.invoicing.product.Product

/**
 * Guarda en un data class un estado para la vista
 *
 * @property product El producto del cual se quiere mostrar sus detalles
 * @property productBeingDeleted Indicativo de si el usuario quiere eliminar el producto.
 */
data class ProductDetailsState(
    val product: Product? = null,
    val productBeingDeleted : Boolean = false
)