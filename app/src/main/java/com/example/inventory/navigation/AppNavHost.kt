package com.example.inventory.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.inventory.home.HomeScreen

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
        composable("main") { HomeScreen(onNavigateCategories = { navController.navigate(CategoryGraph.categoryListRoute()) },
            onNavigateProducts = {}, onNavigateInventory = {}) }

        categoryGraph(
            navController = navController,
            categoryViewModels = viewModels.categoryViewModels
        )

    }
}