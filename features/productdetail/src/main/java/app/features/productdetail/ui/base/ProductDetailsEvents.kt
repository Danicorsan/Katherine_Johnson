package app.features.productdetail.ui.base

import app.features.productdetail.ui.ProductDetailsViewModel

/**
 * Almacena en un data class todos los eventos utilizados en la pantalla de
 * detalles del producto.
 *
 * @property onGoBack Cuando el usuario quiere volver a la pantalla anterior
 * @property onEditProduct Cuando el usuario quiera editar un producto.
 * @property onDeleteProduct Cuando el usuario quiera eliminar un producto.
 * @property onConfirmDeleteProduct Cuando el usuario decide aceptar la eliminiación de un producto.
 * @property onDismissDeleteProduct Cuando el usuario decide cancelar la eliminación de un producto.
 */
data class ProductDetailsEvents(
    val onGoBack : () -> Unit,
    val onEditProduct : () -> Unit,
    val onDeleteProduct : () ->Unit,
    val onConfirmDeleteProduct : () -> Unit,
    val onDismissDeleteProduct : () -> Unit
){
    constructor(viewModel: ProductDetailsViewModel) : this(
        onGoBack = viewModel::onGoBackNavigationClick,
        onEditProduct = viewModel::onEditProduct,
        onDeleteProduct = viewModel::onDeleteProduct,
        onConfirmDeleteProduct = viewModel::onConfirmDeleteProduct,
        onDismissDeleteProduct = viewModel::onDismissDeleteProduct
    )
}
