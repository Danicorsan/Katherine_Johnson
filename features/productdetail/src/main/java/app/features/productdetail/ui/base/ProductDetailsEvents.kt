package app.features.productdetail.ui.base

import app.features.productdetail.ui.ProductDetailsViewModel

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
