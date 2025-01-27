package com.example.inventory.navigation.graphs

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.features.productcreation.ui.creation.ProductCreationScreen
import app.features.productcreation.ui.creation.ProductCreationViewModel
import app.features.productcreation.ui.edition.ProductEditionScreen
import app.features.productcreation.ui.edition.ProductEditionViewModel
import app.features.productdetail.ui.ProductDetailScreen
import app.features.productlist.ui.ProductListScreen
import app.features.productlist.ui.ProductListViewModel
import app.features.productlist.ui.base.ProductListNavigationEvents

//TODO(Transformar el productGraph a una clase sellada para una mayor limpieza con los argumentos)
object ProductGraph {
    const val ROUTE = "product"

    fun productListRoute() = "$ROUTE/productList"
    fun productCreationRoute() = "$ROUTE/productCreation"
    fun productEditionRoute() = "$ROUTE/productEdition/{productId}"
    fun productEditionRoute(idParameter : Int) = "$ROUTE/productEdition/$idParameter"
    fun productDetailsRoute() = "$ROUTE/productDetails/{productId}"
    fun productDetailsRoute(idParameter : Int) = "$ROUTE/productDetails/$idParameter"
}

fun NavGraphBuilder.productGraph(navController: NavController){
    navigation(
        startDestination = ProductGraph.productListRoute(),
        route = ProductGraph.ROUTE
    ){
        productListRoute(navController)
        productCreationRoute(navController)
        productEditionRoute(navController)
        productDetailsRoute(navController)
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
                    onSeeProductDetailsNav = { idProduct ->

                    },
                    onEditProductNav = { idProduct ->
                        navController.navigate(ProductGraph.productEditionRoute(idProduct))
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

private fun NavGraphBuilder.productEditionRoute(
    navController: NavController
){
    composable(
        route = ProductGraph.productEditionRoute()
    ) { navBackStackEntry ->
        val id = navBackStackEntry.arguments?.getString("productId")?.toInt()
        ProductEditionScreen(
            viewModel = remember { ProductEditionViewModel(
                onGoBackNav = {
                    navController.popBackStack()
                },
                productToEditId = id!!
            ) }
        )
    }
}

private fun NavGraphBuilder.productDetailsRoute(
    navController: NavController
){
    composable(
        route = ProductGraph.productCreationRoute()
    ) { navBackStackEntry ->
        ProductDetailScreen(
            onGoBackNav = {
                navController.popBackStack()
            }
        )
    }
}