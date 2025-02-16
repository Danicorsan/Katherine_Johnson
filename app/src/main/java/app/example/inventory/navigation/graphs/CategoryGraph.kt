package com.example.inventory.navigation.graphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import app.features.categorycreation.ui.creation.CategoryCreationScreen
import app.features.categorycreation.ui.edition.CategoryEditionScreen
import app.features.categorydetail.ui.CategoryDetailScreen
import app.features.categorylist.ui.CategoryListScreen
import com.example.inventory.navigation.graphs.CategoryGraph.ROUTE

/**
 * Category graph
 *
 * @constructor Create empty Category graph
 */
object CategoryGraph {
    const val ID = "id"
    const val ROUTE = "category"

    fun categoryListRoute() = "$ROUTE/categoryList"
    fun categoryCreationRoute() = "$ROUTE/categoryCreation"
    fun categoryEditionRoute(id: Int) = "$ROUTE/categoryEdition/$id"
    fun categoryDetailRoute(id: Int) = "$ROUTE/categoryDetail/$id"
}

fun NavGraphBuilder.categoryGraph(
    navController: NavController,
) {
    navigation(startDestination = CategoryGraph.categoryListRoute(), route = ROUTE) {
        categoryListRoute(navController)
        categoryCreationRoute(navController)
        categoryEditionRoute(navController)
        categoryDetailRoute(navController)
    }
}

private fun NavGraphBuilder.categoryListRoute(
    navController: NavController,
) {
    composable(route = CategoryGraph.categoryListRoute()) {
        CategoryListScreen(
            viewModel = hiltViewModel(),
            onClickBack = { navController.popBackStack() },
            onClickNewCategory = { navController.navigate(CategoryGraph.categoryCreationRoute()) },
            onClickDetails = { id -> navController.navigate(CategoryGraph.categoryDetailRoute(id)) }
        )
    }
}

private fun NavGraphBuilder.categoryCreationRoute(
    navController: NavController,
) {
    composable(route = CategoryGraph.categoryCreationRoute()) {
        CategoryCreationScreen(
            viewModel = hiltViewModel(),
            onClickBack = { navController.popBackStack() },
            onClickNewCategory = { navController.popBackStack() }
        )
    }
}

private fun NavGraphBuilder.categoryEditionRoute(
    navController: NavController,
) {
    composable(
        route = "${ROUTE}/categoryEdition/{${CategoryGraph.ID}}",
        arguments = listOf(
            navArgument(CategoryGraph.ID) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getInt(CategoryGraph.ID) ?: 1
        CategoryEditionScreen(
            id = id,
            viewModel = hiltViewModel(),
            onClickBack = { navController.popBackStack() },
        )
    }
}

private fun NavGraphBuilder.categoryDetailRoute(
    navController: NavController,
) {
    composable(
        route = "${ROUTE}/categoryDetail/{${CategoryGraph.ID}}",
        arguments = listOf(
            navArgument(CategoryGraph.ID) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getInt(CategoryGraph.ID) ?: return@composable
        CategoryDetailScreen(
            viewModel = hiltViewModel(),
            id = id,
            onEditCategory = { navController.navigate(CategoryGraph.categoryEditionRoute(id)) },
            onGoBack = { navController.popBackStack() }
        )
    }
}