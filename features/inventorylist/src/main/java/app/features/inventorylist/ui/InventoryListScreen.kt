package app.features.inventorylist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.MediumButton
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import app.features.inventorylist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryListScreen(
    viewModel: InventoryListViewModel,
    onBackClick: () -> Unit,
    onInventoryClick: (Inventory) -> Unit,
    onCreateInventoryClick: () -> Unit,
    onEditInventoryClick: (Inventory) -> Unit
) {
    val state = viewModel.uiState // Asegúrate de usar `state` en vez de `uiState`
    val inventories = state.inventories

    // Función para eliminar un inventario
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
                LoadingUi() // Esto se mostrará mientras el estado sea de carga
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
                title = stringResource(R.string.eliminar_inventario),
                text = stringResource(R.string.seguro_que_quieres_eliminar_el_inventario, inventory.name),
                onDismiss = { showDialog = false },
                onConfirm = {
                    showDialog = false
                    viewModel.deleteInventory(inventory)
                },
                confirmText = "Sí, quiero eliminarlo",
                dismissText = "No, no quiero eliminarlo"
            )
        }
    }
}


@Composable
fun InventoryCard(
    inventory: Inventory,
    onClick: (Inventory) -> Unit,
    onEditClick: (Inventory) -> Unit,
    onDeleteClick: (Inventory) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(inventory) },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.almacen1), // Puedes usar cualquier recurso aquí
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .padding(end = 16.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = inventory.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = inventory.description, style = MaterialTheme.typography.bodySmall)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    IconButton(onClick = { onEditClick(inventory) }) {
                        Icon(imageVector = Icons.Filled.Edit, contentDescription = stringResource(R.string.editar_inventario))
                    }
                    IconButton(onClick = { onDeleteClick(inventory) }) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = stringResource(R.string.eliminar_inventario))
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    InventoryListScreen(
        viewModel = InventoryListViewModel(InventoryRepository),
        onBackClick = {},
        onInventoryClick = {},
        onCreateInventoryClick = {},
        onEditInventoryClick = {}
    )
}
