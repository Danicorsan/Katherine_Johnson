package com.example.inventory.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.features.categorycreation.ui.creation.CategoryCreationScreen
import app.features.categorycreation.ui.creation.CategoryCreationViewModel
import app.features.categorycreation.ui.edition.CategoryEditionScreen
import app.features.categorycreation.ui.edition.CategoryEditionViewModel
import app.features.categorylist.ui.CategoryListScreen
import app.features.categorylist.ui.CategoryListViewModel
import com.example.inventory.navigation.CategoryGraph.categoryCreationRoute


object CategoryGraph {
    const val ROUTE = "category"

    fun categoryListRoute() = "$ROUTE/categoryList"
    fun categoryCreationRoute() = "$ROUTE/categoryCreation"
    fun categoryEditionRoute() = "$ROUTE/categoryEdition"
}

fun NavGraphBuilder.categoryGraph(
    navController: NavController,
    categoryViewModels: CategoryViewModels
) {

    navigation(startDestination = CategoryGraph.categoryListRoute(), route = CategoryGraph.ROUTE) {
        categoryListRoute(navController, categoryViewModels.categoryListViewModel)
        categoryCreationRoute(navController, categoryViewModels.categoryCreationViewModel)
        categoryEditionRoute(navController, categoryViewModels.categoryEditionViewModel)
    }
}

private fun NavGraphBuilder.categoryListRoute(
    navController: NavController,
    categoryListViewModel: CategoryListViewModel
) {
    composable(route = CategoryGraph.categoryListRoute()) {
        CategoryListScreen(
            viewModel = categoryListViewModel,
            onClickBack = { navController.popBackStack() },
            onClickNewCategory = { navController.navigate(categoryCreationRoute()) } )
    }
}

private fun NavGraphBuilder.categoryCreationRoute(
    navController: NavController,
    categoryCreationViewModel: CategoryCreationViewModel
) {
    composable(route = categoryCreationRoute()) {
        CategoryCreationScreen(
            viewModel = categoryCreationViewModel,
            onClickBack = { navController.popBackStack() },
            onClickNewCategory = { navController.popBackStack() })
    }
}

private fun NavGraphBuilder.categoryEditionRoute(
    navController: NavController,
    categoryEditionViewModel: CategoryEditionViewModel
) {
    composable(route = CategoryGraph.categoryEditionRoute()) {
        CategoryEditionScreen(
            categoryEditionViewModel = categoryEditionViewModel,
            onClick = { navController.popBackStack() })
    }
}