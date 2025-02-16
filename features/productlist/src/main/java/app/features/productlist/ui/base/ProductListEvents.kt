package app.features.productlist.ui.base

import app.domain.invoicing.product.Product
import app.features.productlist.ui.ProductListViewModel

data class ProductListEvents(
    val onAddProduct : () -> Unit,
    val onDeleteProduct : (Product) -> Unit,
    val onConfirmDeleteProduct : () -> Unit,
    val onDissmissDeleteProduct : () -> Unit,
    val seeProductDetails : (Product) -> Unit,
    val onEditProduct : (Product) -> Unit,
    val onGoBack : () -> Unit
){
    companion object{
        fun build(viewModel : ProductListViewModel) : ProductListEvents{
            return ProductListEvents(
                onAddProduct = viewModel::onCreateProduct,
                onDeleteProduct = viewModel::onDeleteProduct,
                onConfirmDeleteProduct = viewModel::onConfirmDeleteProduct,
                onDissmissDeleteProduct = viewModel::onDissmissDeleteProduct,
                seeProductDetails = viewModel::onSeeProductDetails,
                onEditProduct = viewModel::onEditProduct,
                onGoBack = viewModel::onGoBack
            )
        }
    }
}

data class ProductListNavigationEvents(
    val onCreateProductNav: () -> Unit = {},
    val onSeeProductDetailsNav : (Int) -> Unit = {},
    val onEditProductNav : (Int) -> Unit = {},
    val onGoBackNav : () -> Unit = {}
)