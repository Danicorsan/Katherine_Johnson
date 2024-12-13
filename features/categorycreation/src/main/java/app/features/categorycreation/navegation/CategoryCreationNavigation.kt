package app.features.categorycreation.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.features.categorycreation.ui.creation.CategoryCreationScreen
import app.features.categorycreation.ui.edition.CategoryEditionScreen

@Composable
fun CategoryCreationNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = CategoryCreationNavigation.Creation.route
    ) {
        composable(CategoryCreationNavigation.Creation.route) {
            CategoryCreationScreen()
        }
        composable(CategoryCreationNavigation.Edition.route) {
            CategoryEditionScreen()
        }
    }
}

sealed class CategoryCreationNavigation(val route: String) {
    data object Creation : CategoryCreationNavigation("creation")
    data object Edition : CategoryCreationNavigation("edition")
}