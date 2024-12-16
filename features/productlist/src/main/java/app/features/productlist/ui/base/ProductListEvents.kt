package app.features.productlist.ui.base

import app.domain.invoicing.product.Product
import app.features.productlist.ui.ProductListViewModel

data class ProductListEvents(
    val onAddProduct : () -> Unit,
    val onDeleteProduct : (Product) -> Unit,
    val seeProductDetails : (Product) -> Unit,
    val onEditProduct : (Product) -> Unit,
    val onGoBack : () -> Unit
){
    companion object{
        fun build(viewModel : ProductListViewModel) : ProductListEvents{
            return ProductListEvents(
                onAddProduct = viewModel::onAddProduct,
                onDeleteProduct = viewModel::onDeleteProduct,
                seeProductDetails = viewModel::onSeeProductDetails,
                onEditProduct = viewModel::onEditProduct,
                onGoBack = viewModel::onGoBack
            )
        }
    }
}
