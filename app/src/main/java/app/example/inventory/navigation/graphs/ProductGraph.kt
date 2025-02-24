package com.example.inventory.navigation.graphs

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import app.features.productcreation.ui.creation.ProductCreationScreen
import app.features.productcreation.ui.creation.ProductCreationViewModel
import app.features.productcreation.ui.edition.ProductEditionScreen
import app.features.productcreation.ui.edition.ProductEditionViewModel
import app.features.productdetail.ui.ProductDetailScreen
import app.features.productdetail.ui.ProductDetailsViewModel
import app.features.productlist.ui.ProductListScreen
import app.features.productlist.ui.ProductListViewModel
import app.features.productlist.ui.base.ProductListNavigationEvents

@Deprecated("Por la introducción del BasedNavigationDrawer, se necesitará" +
        "usar y modificar su versión en el modulo Base",
    replaceWith = ReplaceWith("ProductGraph",
        "app.base.features.navigation.graphs.ProductGraph"
        ),
)
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
                        navController.navigate(ProductGraph.productDetailsRoute(idProduct))
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
        route = ProductGraph.productEditionRoute(),
        arguments = listOf(
            navArgument(ProductGraph.PRODUCTID){
                type = NavType.IntType
            }
        )
    ) { navBackStackEntry ->
        val id = navBackStackEntry.arguments?.getInt(ProductGraph.PRODUCTID)
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
        route = ProductGraph.productDetailsRoute(),
        arguments = listOf(
            navArgument(ProductGraph.PRODUCTID){
                type = NavType.IntType
            }
        )
    ) { navBackStackEntry ->
        val id = navBackStackEntry.arguments?.getInt(ProductGraph.PRODUCTID)
        ProductDetailScreen(
            viewModel = remember { ProductDetailsViewModel()},
            productId = id!!,
            onGoBackNav = {
                navController.popBackStack()
            }
        )
    }
}