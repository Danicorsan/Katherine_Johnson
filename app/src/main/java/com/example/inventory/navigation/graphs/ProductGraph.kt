package com.example.inventory.navigation.graphs

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.features.productcreation.ui.creation.ProductCreationScreen
import app.features.productcreation.ui.creation.ProductCreationViewModel
import app.features.productlist.ui.ProductListScreen
import app.features.productlist.ui.ProductListViewModel
import app.features.productlist.ui.base.ProductListNavigationEvents

object ProductGraph {
    const val ROUTE = "product"

    fun productListRoute() = "$ROUTE/productList"
    fun productCreationRoute() = "$ROUTE/productCreation"
    fun productEditionRoute() = "$ROUTE/productEdition"
    fun productDetailsRoute() = "$ROUTE/productDetails"
}

fun NavGraphBuilder.productGraph(navController: NavController){
    navigation(
        startDestination = ProductGraph.productListRoute(),
        route = ProductGraph.ROUTE
    ){
        productListRoute(navController)
        productCreationRoute(navController)
    }
}

private fun NavGraphBuilder.productListRoute(
    navController: NavController
){
    composable(
        route = ProductGraph.productListRoute()
    ) {
        ProductListScreen(
            viewModel = remember { ProductListViewModel(
                ProductListNavigationEvents(
                    onCreateProductNav = {
                        navController.navigate(ProductGraph.productCreationRoute())
                    },
                    onSeeProductDetailsNav = {

                    },
                    onEditProductNav = {

                    },
                    onGoBackNav = {
                        navController.popBackStack()
                    }
                )
            ) },

        )
    }
}

private fun NavGraphBuilder.productCreationRoute(
    navController: NavController
){
    composable(
        route = ProductGraph.productCreationRoute()
    ) {
        ProductCreationScreen(
            viewModel = remember { ProductCreationViewModel {
                navController.popBackStack()
            }
            }
        )
    }
}