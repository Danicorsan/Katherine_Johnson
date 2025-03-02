package com.example.inventory.navigation.graphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import app.features.inventorycreation.ui.creation.CreateInventoryScreen
import app.features.inventorycreation.ui.edition.EditInventoryScreen
import app.features.inventorydetail.ui.InventoryDetailScreen
import app.features.inventorylist.ui.InventoryListScreen

object InventoryGraph {
    const val ID = "id"
    const val ROUTE = "inventory"

    fun inventoryListRoute() = "$ROUTE/inventoryList"
    fun inventoryCreationRoute() = "$ROUTE/inventoryCreation"
    fun inventoryEditionRoute(id: Int) = "$ROUTE/inventoryEdition/$id"
    fun inventoryDetailRoute(id: Int) = "$ROUTE/inventoryDetail/$id"
}

fun NavGraphBuilder.inventoryGraph(
    navController: NavController,
) {
    navigation(startDestination = InventoryGraph.inventoryListRoute(), route = InventoryGraph.ROUTE) {
        inventoryListRoute(navController)
        inventoryCreationRoute(navController)
        inventoryEditionRoute(navController)
        inventoryDetailRoute(navController)
    }
}

private fun NavGraphBuilder.inventoryListRoute(
    navController: NavController,
) {
    composable(route = InventoryGraph.inventoryListRoute()) {
        InventoryListScreen(
            viewModel = hiltViewModel(),
            onInventoryClick = { id ->
                navController.navigate(InventoryGraph.inventoryDetailRoute(
                    id = id.id
                )) },
            onCreateInventoryClick = { navController.navigate(InventoryGraph.inventoryCreationRoute()) },
            onNavigateProducts = { navController.navigate(ProductGraph.ROUTE) },
            onNavigateCategories = { navController.navigate(CategoryGraph.ROUTE) },
            onNavigateInventory = { navController.navigate(InventoryGraph.ROUTE) },
        )
    }
}

private fun NavGraphBuilder.inventoryCreationRoute(
    navController: NavController,
) {
    composable(route = InventoryGraph.inventoryCreationRoute()) {
        CreateInventoryScreen(
            viewModel = hiltViewModel(),
            onNavigateToList = { navController.popBackStack() },
            onBackClick = { navController.popBackStack() },
        )
    }
}

private fun NavGraphBuilder.inventoryEditionRoute(
    navController: NavController,
) {
    composable(
        route = "${InventoryGraph.ROUTE}/inventoryEdition/{${InventoryGraph.ID}}",
        arguments = listOf(
            navArgument(InventoryGraph.ID) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getInt(InventoryGraph.ID) ?: return@composable
        EditInventoryScreen(
            viewModel = hiltViewModel(),
            inventoryId = id,
            onNavigateBack = { navController.popBackStack() }
        )
    }
}

private fun NavGraphBuilder.inventoryDetailRoute(
    navController: NavController,
) {
    composable(
        route = "${InventoryGraph.ROUTE}/inventoryDetail/{${InventoryGraph.ID}}",
        arguments = listOf(
            navArgument(InventoryGraph.ID) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getInt(InventoryGraph.ID) ?: return@composable
        InventoryDetailScreen(
            inventoryId = id,
            onNavigateBack = { navController.popBackStack() },
            onEditInventoryClick = { inventory ->
                navController.navigate(InventoryGraph.inventoryEditionRoute(inventory.id))
            },
            viewModel = hiltViewModel(),
        )
    }
}