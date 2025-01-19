package com.example.inventory.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.inventory.home.HomeScreen
import com.example.inventory.navigation.graphs.CategoryGraph
import com.example.inventory.navigation.graphs.InventoryGraph
import com.example.inventory.navigation.graphs.ProductGraph
import com.example.inventory.navigation.graphs.categoryGraph
import com.example.inventory.navigation.graphs.inventoryGraph
import com.example.inventory.navigation.graphs.productGraph

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = CategoryGraph.ROUTE,
    viewModels: ViewModels
) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        // Ruta principal
        composable("main") { HomeScreen(onNavigateCategories = { navController.navigate(
            CategoryGraph.categoryListRoute()) },
            onNavigateProducts = {
                navController.navigate(ProductGraph.ROUTE)
            }, onNavigateInventory = {navController.navigate(InventoryGraph.ROUTE)}) }

        categoryGraph(
            navController = navController,
            categoryViewModels = viewModels.categoryViewModels
        )

        productGraph(
            navController = navController
        )
        inventoryGraph(
            navController = navController
        )
    }
}