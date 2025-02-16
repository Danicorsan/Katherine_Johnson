package com.example.inventory.navigation.graphs

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.domain.invoicing.repository.InventoryRepository
import app.features.inventorycreation.ui.creation.CreateInventoryScreen
import app.features.inventorycreation.ui.creation.CreateInventoryViewModel
import app.features.inventorycreation.ui.edition.EditInventoryScreen
import app.features.inventorycreation.ui.edition.EditInventoryViewModel
import app.features.inventorydetail.ui.InventoryDetailScreen
import app.features.inventorydetail.ui.InventoryDetailViewModel
import app.features.inventorylist.ui.InventoryListScreen
import app.features.inventorylist.ui.InventoryListViewModel

object InventoryGraph {
    const val ROUTE = "inventory"

    fun inventoryListRoute() = "$ROUTE/inventoryList"
    fun inventoryCreationRoute() = "$ROUTE/inventoryCreation"
    fun inventoryEditionRoute(inventoryId: String) = "$ROUTE/inventoryEdition/$inventoryId"
    fun inventoryDetailsRoute(inventoryId: String) = "$ROUTE/inventoryDetails/$inventoryId"
}

fun NavGraphBuilder.inventoryGraph(navController: NavController) {
    navigation(
        startDestination = InventoryGraph.inventoryListRoute(),
        route = InventoryGraph.ROUTE
    ) {
        inventoryListRoute(navController)
        inventoryCreationRoute(navController)
        inventoryEditionRoute(navController)
        inventoryDetailsRoute(navController)
    }
}

private fun NavGraphBuilder.inventoryListRoute(navController: NavController) {
    composable(route = InventoryGraph.inventoryListRoute()) {
        val viewModel = remember { InventoryListViewModel(InventoryRepository) }
        InventoryListScreen(
            viewModel = viewModel,
            onBackClick = {
                navController.popBackStack()
            },
            onInventoryClick = { inventory ->
                navController.navigate(InventoryGraph.inventoryDetailsRoute(inventory.id.toString()))
            },
            onCreateInventoryClick = {
                navController.navigate(InventoryGraph.inventoryCreationRoute())
            },
            onEditInventoryClick = { inventory ->
                navController.navigate(InventoryGraph.inventoryEditionRoute(inventory.id.toString()))
            },
        )
    }
}

private fun NavGraphBuilder.inventoryCreationRoute(navController: NavController) {
    composable(route = InventoryGraph.inventoryCreationRoute()) {
        val inventoryRepository = remember { InventoryRepository }

        CreateInventoryScreen(
            onNavigateToList = {
                navController.popBackStack()
            },
            onBackClick = {
                navController.popBackStack()
            },
            viewModel = CreateInventoryViewModel(inventoryRepository)
        )
    }
}


private fun NavGraphBuilder.inventoryEditionRoute(navController: NavController) {
    composable(
        route = InventoryGraph.inventoryEditionRoute("{inventoryId}")
    ) { backStackEntry ->
        val inventoryId = backStackEntry.arguments?.getString("inventoryId")?.toInt() ?: 0
        val inventoryRepository = remember { InventoryRepository }
        val viewModel: EditInventoryViewModel = remember { EditInventoryViewModel(inventoryRepository) }

        EditInventoryScreen(
            viewModel = viewModel,
            inventoryId = inventoryId,
            onNavigateBack = {
                navController.popBackStack()
            }
        )
    }
}

private fun NavGraphBuilder.inventoryDetailsRoute(navController: NavController) {
    composable(
        route = InventoryGraph.inventoryDetailsRoute("{inventoryId}")
    ) { backStackEntry ->
        val inventoryId = backStackEntry.arguments?.getString("inventoryId")?.toInt() ?: 0

        InventoryDetailScreen(
            inventoryId = inventoryId,
            onNavigateBack = {
                navController.popBackStack()
            },
        )
    }
}