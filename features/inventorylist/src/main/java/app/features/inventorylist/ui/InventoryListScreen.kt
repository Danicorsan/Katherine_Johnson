package app.features.inventorylist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.Item
import app.features.inventorylist.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryListScreen(
    viewModel: InventoryListViewModel,
    onBackClick: () -> Unit,
    onInventoryClick: (Inventory) -> Unit,
    onCreateInventoryClick: () -> Unit,
    onEditInventoryClick: (Inventory) -> Unit,
    onDeleteInventoryClick: (Inventory) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.lista_de_inventarios),
                        style = MaterialTheme.typography.headlineMedium
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
            FloatingActionButton(
                onClick = onCreateInventoryClick,
                modifier = Modifier.padding(16.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Text(
                    text = stringResource(R.string.crear_inventario),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(uiState.inventories) { inventory ->
                    InventoryCard(
                        inventory = inventory,
                        onClick = { onInventoryClick(inventory) },
                        onEditClick = { onEditInventoryClick(inventory) },
                        onDeleteClick = { onDeleteInventoryClick(inventory) }
                    )
                }
            }
        }
    )
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
        shape = MaterialTheme.shapes.medium, // MaterialShape
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp) // Elevation
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = inventory.name, style = MaterialTheme.typography.bodyLarge)
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

@Preview(showBackground = true)
@Composable
fun PreviewInventoryListScreen() {
    val sampleInventories = listOf(
        Inventory(
            id = 1, name = "Inventario 1", description = "Descripción del Inventario 1",
            items = listOf(
                Item(1, "Camiseta Roja", "Camiseta de algodón, talla M"),
                Item(2, "Pantalones Azules", "Pantalones de mezclilla, talla 32"),
                Item(3, "Zapatos Negros", "Zapatos de cuero, talla 42")
            )
        ),
        Inventory(
            id = 2, name = "Inventario 2", description = "Descripción del Inventario 2",
            items = listOf(
                Item(1, "Camiseta Roja", "Camiseta de algodón, talla M"),
                Item(2, "Pantalones Azules", "Pantalones de mezclilla, talla 32"),
                Item(3, "Zapatos Negros", "Zapatos de cuero, talla 42")
            )
        ),
        Inventory(
            id = 3, name = "Inventario 3", description = "Descripción del Inventario 3",
            items = listOf(
                Item(1, "Camiseta Roja", "Camiseta de algodón, talla M"),
                Item(2, "Pantalones Azules", "Pantalones de mezclilla, talla 32"),
                Item(3, "Zapatos Negros", "Zapatos de cuero, talla 42")
            )
        )
    )

    val previewViewModel = object : InventoryListViewModel() {
        override val uiState: StateFlow<InventoryListState> = MutableStateFlow(InventoryListState(inventories = sampleInventories))
    }

    InventoryListScreen(
        viewModel = previewViewModel,
        onBackClick = {
            println("Volver a la pantalla anterior")
        },
        onInventoryClick = { inventory ->
            println("Inventario seleccionado: ${inventory.name}")
        },
        onCreateInventoryClick = {
            println("Crear nuevo inventario")
        },
        onEditInventoryClick = { inventory ->
            println("Editar inventario: ${inventory.name}")
        },
        onDeleteInventoryClick = { inventory ->
            println("Eliminar inventario: ${inventory.name}")
        }
    )
}
