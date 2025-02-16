package app.features.inventorylist.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.MediumButton
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import app.features.inventorylist.R
import app.features.inventorylist.ui.base.InventoryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryListScreen(
    viewModel: InventoryListViewModel,
    onBackClick: () -> Unit,
    onInventoryClick: (Inventory) -> Unit,
    onCreateInventoryClick: () -> Unit,
    onEditInventoryClick: (Inventory) -> Unit
) {
    // Listar
    val state = viewModel.uiState
    val inventories = state.inventories

    // Eliminar
    var showDialog by remember { mutableStateOf(false) }
    var selectedInventory: Inventory? by remember { mutableStateOf(null) }
    val onDeleteInventoryClick: (Inventory) -> Unit = { inventory ->
        selectedInventory = inventory
        showDialog = true
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.lista_de_inventarios),
                        )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.volver)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            MediumButton(
                onClick = onCreateInventoryClick,
                imageVector = Icons.Default.Add,
                contentDescription = "Añadir inventario"
            )
        },
        content = { paddingValues ->
            // Mostrar LoadingUI cuando isLoading es true
            if (state.isLoading) {
                LoadingUI() // Esto se mostrará mientras el estado sea de carga
            } else {
                if (inventories.isEmpty()) {
                    Text(
                        text = "No hay inventarios",
                        modifier = Modifier.fillMaxSize(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        items(inventories) { inventory ->
                            InventoryCard(
                                inventory = inventory,
                                onClick = { onInventoryClick(inventory) },
                                onDeleteClick = { onDeleteInventoryClick(inventory) },
                                onEditClick = { onEditInventoryClick(inventory) }
                            )
                        }
                    }
                }
            }
        }
    )
    if (showDialog) {
        selectedInventory?.let { inventory ->
            BaseAlertDialog(
                title = "Eliminar inventario",
                text = "¿Estás seguro de eliminar el inventario ${inventory.name}?",
                confirmText = "Eliminar",
                dismissText = "Cancelar",
                onConfirm = {
                    viewModel.deleteInventory(inventory)
                    showDialog = false
                },
                onDismiss = {
                    showDialog = false
                }
            )
        }
    }
}




@Preview(showSystemUi = true)
@Composable
fun InventoryListPreview() {
    InventoryListScreen(
        viewModel = InventoryListViewModel(InventoryRepository),
        onBackClick = {},
        onInventoryClick = {},
        onCreateInventoryClick = {},
        onEditInventoryClick = {}
    )
}
