package app.features.inventorylist.ui

import InventoryType
import NoDataScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.base.ui.components.LoadingUi
import app.base.ui.composables.AppDrawer
import app.base.ui.composables.MediumButton
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import app.features.inventorylist.R
import app.features.inventorylist.ui.base.InventoryCard

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
                                viewModel.onOpenDrawer(
                                    scope = scopeCoroutine
                                )
                            }
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
                        NoDataScreen()
                    } else {
                        InventoryListContent(
                            inventories = success,
                            onInventoryClick = onInventoryClick,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            onLongClick = { inventory ->
                                inventoryToDelete = inventory
                                showDeleteDialog = true
                            }
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
    val groupedInventories = inventories.groupBy { it.inventoryType }

    LazyColumn(modifier = modifier) {
        groupedInventories.forEach { (type, inventoriesOfType) ->
            item {
                InventoryTypeHeader(type)
            }
            items(inventoriesOfType) { inventory ->
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

@Composable
fun InventoryTypeHeader(type: InventoryType) {
    val typeNameResId = when (type) {
        InventoryType.WEEKLY -> R.string.inventory_type_semanal
        InventoryType.MONTHLY -> R.string.inventory_type_mensual
        InventoryType.TRIMESTRAL -> R.string.inventory_type_trimestral
        InventoryType.SEMESTRAL -> R.string.inventory_type_semestral
        InventoryType.ANNUAL -> R.string.inventory_type_anual
        InventoryType.PERMANENT -> R.string.inventory_type_permanente
    }

    Text(
        text = stringResource(typeNameResId),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    )
}

@Preview(showSystemUi = true)
@Composable
fun InventoryListPreview() {
    InventoryListScreen(
        viewModel = InventoryListViewModel(InventoryRepository),
        onInventoryClick = {},
        onCreateInventoryClick = {},
    )
}