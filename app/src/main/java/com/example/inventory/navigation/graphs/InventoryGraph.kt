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

    // Definimos las rutas para las pantallas
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
        // Ruta para la pantalla de lista de inventarios
        inventoryListRoute(navController)
        // Ruta para la pantalla de creación de inventarios
        inventoryCreationRoute(navController)
        // Ruta para la pantalla de edición de inventarios
        inventoryEditionRoute(navController)
        // Ruta para la pantalla de detalles de inventarios
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
                // Navegar a los detalles de ese inventario, pasando el inventoryId
                navController.navigate(InventoryGraph.inventoryDetailsRoute(inventory.id.toString()))
            },
            onCreateInventoryClick = {
                // Navegar a la pantalla de creación de inventarios
                navController.navigate(InventoryGraph.inventoryCreationRoute())
            }
        )
    }
}

private fun NavGraphBuilder.inventoryCreationRoute(navController: NavController) {
    composable(route = InventoryGraph.inventoryCreationRoute()) {
        // Instanciamos el repositorio directamente
        val inventoryRepository = remember { InventoryRepository() }

        // Instanciamos el ViewModel y pasamos el repositorio
        val viewModel: CreateInventoryViewModel = viewModel(
            factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CreateInventoryViewModel(inventoryRepository) as T
                }
            }
        )

        // Pasamos el ViewModel y el callback de navegación
        CreateInventoryScreen(
            onNavigateToList = {
                // Cuando se crea un inventario, regresamos a la lista
                navController.popBackStack()  // Volver a la lista de inventarios
            },
            onBackClick = {
                // Acción para ir hacia atrás cuando se presiona el botón de retroceso
                navController.popBackStack()  // Volver a la pantalla anterior
            }
        )
    }
}


private fun NavGraphBuilder.inventoryEditionRoute(navController: NavController) {
    composable(
        route = InventoryGraph.inventoryEditionRoute("{inventoryId}")
    ) { backStackEntry ->
        // Obtener el parámetro inventoryId de la ruta
        val inventoryId = backStackEntry.arguments?.getString("inventoryId")?.toInt() ?: 0

        // Instanciamos el repositorio y el ViewModel
        val inventoryRepository = remember { InventoryRepository() }
        val viewModel: EditInventoryViewModel = remember { EditInventoryViewModel(inventoryRepository) }

        // Llamamos a la pantalla de edición de inventario y pasamos los parámetros necesarios
        EditInventoryScreen(
            viewModel = viewModel,
            inventoryId = inventoryId,
            onInventoryEdited = {
                // Cuando se edita un inventario, regresamos a la lista
                navController.popBackStack()
            },
            onNavigateBack = {
                // Regresar a la pantalla anterior
                navController.popBackStack()
            }
        )
    }
}

private fun NavGraphBuilder.inventoryDetailsRoute(navController: NavController) {
    composable(
        route = InventoryGraph.inventoryDetailsRoute("{inventoryId}")
    ) { backStackEntry ->
        // Obtener el parámetro inventoryId de la ruta
        val inventoryId = backStackEntry.arguments?.getString("inventoryId")?.toInt() ?: 0
        val viewModel = remember { InventoryDetailViewModel(InventoryRepository()) }

        InventoryDetailScreen(
            viewModel = viewModel,
            inventoryId = inventoryId,
            onEditClick = {
                // Al hacer clic en el botón de editar, navegamos a la pantalla de edición
                navController.navigate(InventoryGraph.inventoryEditionRoute(inventoryId.toString())) // Pasar el inventoryId
            },
            onNavigateBack = {
                // Al hacer clic en "volver", navegamos hacia atrás
                navController.popBackStack()
            }
        )
    }
}
