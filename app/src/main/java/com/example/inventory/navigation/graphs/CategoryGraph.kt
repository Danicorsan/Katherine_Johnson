package com.example.inventory.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import app.features.categorycreation.ui.creation.CategoryCreationScreen
import app.features.categorycreation.ui.creation.CategoryCreationViewModel
import app.features.categorycreation.ui.edition.CategoryEditionScreen
import app.features.categorycreation.ui.edition.CategoryEditionViewModel
import app.features.categorydetail.ui.CategoryDetailScreen
import app.features.categorydetail.ui.CategoryDetailViewModel
import app.features.categorylist.ui.CategoryListScreen
import app.features.categorylist.ui.CategoryListViewModel
import com.example.inventory.navigation.CategoryViewModels

object CategoryGraph {
    const val ID = "id"
    const val ROUTE = "category"

    fun categoryListRoute() = "$ROUTE/categoryList"
    fun categoryCreationRoute() = "$ROUTE/categoryCreation"
    fun categoryEditionRoute() = "$ROUTE/categoryEdition"
    fun categoryDetailRoute(id: Int) = "$ROUTE/categoryDetail/$id"
}

fun NavGraphBuilder.categoryGraph(
    navController: NavController,
    categoryViewModels: CategoryViewModels
) {
    navigation(startDestination = CategoryGraph.categoryListRoute(), route = CategoryGraph.ROUTE) {
        categoryListRoute(navController, categoryViewModels.categoryListViewModel)
        categoryCreationRoute(navController, categoryViewModels.categoryCreationViewModel)
        categoryEditionRoute(navController, categoryViewModels.categoryEditionViewModel)
        categoryDetailRoute(navController, categoryViewModels.categoryDetailViewModel)
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
            onClickNewCategory = { navController.navigate(CategoryGraph.categoryCreationRoute()) },
            onClickEditCategory = { navController.navigate(CategoryGraph.categoryEditionRoute()) },
            onClickDetails = { id -> navController.navigate(CategoryGraph.categoryDetailRoute(id)) }
        )
    }
}

private fun NavGraphBuilder.categoryCreationRoute(
    navController: NavController,
    categoryCreationViewModel: CategoryCreationViewModel
) {
    composable(route = CategoryGraph.categoryCreationRoute()) {
        CategoryCreationScreen(
            viewModel = categoryCreationViewModel,
            onClickBack = { navController.popBackStack() },
            onClickNewCategory = { navController.popBackStack() }
        )
    }
}

private fun NavGraphBuilder.categoryEditionRoute(
    navController: NavController,
    categoryEditionViewModel: CategoryEditionViewModel
) {
    composable(route = CategoryGraph.categoryEditionRoute()) {
        CategoryEditionScreen(
            categoryEditionViewModel = categoryEditionViewModel,
            onClick = { navController.popBackStack() }
        )
    }
}

private fun NavGraphBuilder.categoryDetailRoute(
    navController: NavController,
    categoryDetailViewModel: CategoryDetailViewModel
) {
    composable(
        route = "${CategoryGraph.ROUTE}/categoryDetail/{${CategoryGraph.ID}}",
        arguments = listOf(
            navArgument(CategoryGraph.ID) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getInt(CategoryGraph.ID) ?: return@composable
        CategoryDetailScreen(
            viewModel = categoryDetailViewModel,
            id = id,
            onClickBack = { navController.popBackStack() }
        )
    }
}
