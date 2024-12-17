package app.features.productlist.ui.base

import app.domain.invoicing.product.Product

data class ProductListState(
    val productList : List<Product> = emptyList(),
    val isLoading : Boolean = false
)
