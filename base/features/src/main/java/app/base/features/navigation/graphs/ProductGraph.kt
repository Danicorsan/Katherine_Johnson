package com.example.inventory.navigation.graphs

object ProductGraph {
    const val ROUTE = "product"
    const val PRODUCTID = "productId"

    fun productListRoute() = "$ROUTE/productList"
    fun productCreationRoute() = "$ROUTE/productCreation"
    fun productEditionRoute() = "$ROUTE/productEdition/{$PRODUCTID}"
    fun productEditionRoute(idParameter : Int) = "$ROUTE/productEdition/$idParameter"
    fun productDetailsRoute() = "$ROUTE/productDetails/{$PRODUCTID}"
    fun productDetailsRoute(idParameter : Int) = "$ROUTE/productDetails/$idParameter"
}