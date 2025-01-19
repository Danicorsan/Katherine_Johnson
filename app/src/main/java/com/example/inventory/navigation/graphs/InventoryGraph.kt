package com.example.inventory.navigation.graphs

import app.features.inventorylist.ui.InventoryListScreen
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.features.inventorycreation.ui.creation.CreateInventoryScreen
import app.features.inventorycreation.ui.creation.CreateInventoryViewModel
import app.features.inventorylist.ui.InventoryListViewModel
import app.features.inventorydetail.ui.InventoryDetailScreen
import app.features.inventorydetail.ui.InventoryDetailViewModel
import app.features.inventorycreation.ui.edition.EditInventoryScreen
import app.features.inventorycreation.ui.edition.EditInventoryViewModel
import app.domain.invoicing.repository.InventoryRepository

object InventoryGraph {
    const val ROUTE = "inventory"

    fun inventoryListRoute() = "$ROUTE/inventoryList"
    fun inventoryCreationRoute() = "$ROUTE/inventoryCreation"
    fun inventoryEditionRoute(inventoryId: String) = "$ROUTE/inventoryEdition/$inventoryId"  // Aceptamos un parámetro de ID
    fun inventoryDetailsRoute(inventoryId: String) = "$ROUTE/inventoryDetails/$inventoryId"  // Aceptamos un parámetro de ID
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
        val viewModel = remember { InventoryListViewModel(InventoryRepository()) }
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
            onDeleteInventoryClick = { inventory ->
                navController.navigate(InventoryGraph.inventoryEditionRoute(inventory.id.toString()))
            }
        )
    }
}

private fun NavGraphBuilder.inventoryCreationRoute(navController: NavController) {
    composable(route = InventoryGraph.inventoryCreationRoute()) {
        val inventoryRepository = remember { InventoryRepository() }

        val viewModel: CreateInventoryViewModel = viewModel(
            factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CreateInventoryViewModel(inventoryRepository) as T
                }
            }
        )

        CreateInventoryScreen(
            onNavigateToList = {
                navController.popBackStack()
            },
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
}


private fun NavGraphBuilder.inventoryEditionRoute(navController: NavController) {
    composable(
        route = InventoryGraph.inventoryEditionRoute("{inventoryId}")
    ) { backStackEntry ->
        val inventoryId = backStackEntry.arguments?.getString("inventoryId")?.toInt() ?: 0
        val inventoryRepository = remember { InventoryRepository() }
        val viewModel: EditInventoryViewModel = remember { EditInventoryViewModel(inventoryRepository) }

        EditInventoryScreen(
            viewModel = viewModel,
            inventoryId = inventoryId,
            onInventoryEdited = {
                navController.popBackStack()
            },
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
        val viewModel = remember { InventoryDetailViewModel(InventoryRepository()) }

        InventoryDetailScreen(
            viewModel = viewModel,
            inventoryId = inventoryId,
            onEditClick = {
                navController.navigate(InventoryGraph.inventoryEditionRoute(inventoryId.toString())) // Pasar el inventoryId
            },
            onNavigateBack = {
                navController.popBackStack()
            }
        )
    }
}
