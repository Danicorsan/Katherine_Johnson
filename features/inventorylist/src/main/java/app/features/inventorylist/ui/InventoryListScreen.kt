package app.features.inventorylist.ui

import NoDataAnimatedScreen
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.base.ui.components.LoadingUi
import app.base.ui.composables.AppDrawer
import app.base.ui.composables.MediumButton
import app.base.ui.composables.baseappbar.Action
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryState
import app.features.inventorylist.R
import app.features.inventorylist.ui.base.composables.InventoryCard
import app.features.inventorylist.ui.base.composables.InventoryTypeHeader

@Composable
fun InventoryListScreen(
    viewModel: InventoryListViewModel,
    onInventoryClick: (Inventory) -> Unit,
    onCreateInventoryClick: () -> Unit,
    onNavigateProducts: () -> Unit = {},
    onNavigateCategories: () -> Unit = {},
    onNavigateInventory: () -> Unit = {},
) {
    val state = viewModel.uiState
    val success = state.success
    val noData = success.isEmpty()
    val scopeCoroutine = rememberCoroutineScope()
    var showDeleteDialog by remember { mutableStateOf(false) }
    var cannotDeleteDialog by remember { mutableStateOf(false) }
    var inventoryToDelete: Inventory? by remember { mutableStateOf(null) }

    AppDrawer(
        drawerState = state.drawerState,
        onNavigateProducts = onNavigateProducts,
        onNavigateCategories = onNavigateCategories,
        onNavigateInventory = onNavigateInventory,
        content = {
            Scaffold(
                topBar = {
                    BaseAppBar(
                        BaseAppBarState(
                            title = stringResource(R.string.lista_de_inventarios),
                            navigationIcon = BaseAppBarIcons.drawerMenuIcon {
                                viewModel.onOpenDrawer(scope = scopeCoroutine)
                            },
                            actions = listOf(
                                Action(
                                    icon = Icons.Filled.Refresh,
                                    contentDescription = "Refresh",
                                    onClick = { viewModel.refreshInventories() }
                                )
                            )
                        )
                    )
                },
                floatingActionButton = {
                    MediumButton(
                        onClick = onCreateInventoryClick,
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.añadir_inventario)
                    )
                },
                content = { paddingValues ->
                    if (state.loading) {
                        LoadingUi()
                    } else if (noData) {
                        NoDataAnimatedScreen()
                    } else {
                        InventoryListContent(
                            inventories = success,
                            onInventoryClick = onInventoryClick,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            onLongClick = { inventory ->
                                if (inventory.state != InventoryState.IN_PROGRESS) {
                                    cannotDeleteDialog = true
                                } else {
                                    inventoryToDelete = inventory
                                    showDeleteDialog = true
                                }
                            }
                        )
                    }
                    if (cannotDeleteDialog) {
                        AlertDialog(
                            onDismissRequest = { cannotDeleteDialog = false },
                            title = { Text(stringResource(R.string.error)) },
                            text = {
                                val inventoryState = inventoryToDelete?.state?.name ?: ""
                                Text(stringResource(R.string.no_puedes_eliminar_un_inventario_con_estado, inventoryState))
                            },
                            confirmButton = {
                                TextButton(onClick = { cannotDeleteDialog = false }) {
                                    Text(stringResource(R.string.aceptar))
                                }
                            },
                        )
                    }
                    if (showDeleteDialog) {
                        AlertDialog(
                            onDismissRequest = { showDeleteDialog = false },
                            title = { Text(stringResource(R.string.confirmar_eliminacion)) },
                            text = { Text(stringResource(R.string.estas_seguro_de_que_quieres_eliminar_este_inventario)) },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        showDeleteDialog = false
                                        inventoryToDelete?.let { inventory ->
                                            viewModel.deleteInventory(inventory)
                                        }
                                    }
                                ) {
                                    Text(stringResource(R.string.si_eliminar))
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { showDeleteDialog = false }) {
                                    Text(stringResource(R.string.cancelar))
                                }
                            }
                        )
                    }
                }
            )
        }
    )
}

@Composable
fun InventoryListContent(
    inventories: List<Inventory>,
    onInventoryClick: (Inventory) -> Unit,
    modifier: Modifier = Modifier,
    onLongClick: (Inventory) -> Unit
) {
    val groupedInventories = inventories.groupBy { it.state }

    LazyColumn(modifier = modifier) {
        groupedInventories.forEach { (state, stateInventories) ->
            item {
                InventoryTypeHeader(state)
            }
            items(stateInventories) { inventory ->
                InventoryCard(
                    inventory = inventory,
                    onClick = { onInventoryClick(inventory) },
                    onLongClick = { onLongClick(inventory) },
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}