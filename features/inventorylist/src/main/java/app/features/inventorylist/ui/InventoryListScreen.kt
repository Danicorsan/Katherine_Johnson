package app.features.inventorylist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun InventoryListScreen(
    viewModel: InventoryListViewModel,
    onBackClick: () -> Unit,
    onInventoryClick: (Inventory) -> Unit,
    onCreateInventoryClick: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Botón de retroceso
        IconButton(
            onClick = { onBackClick() },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver"
            )
        }

        // Título de la pantalla
        Text(
            text = "Lista de Inventarios",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )

        // Botón para crear un inventario
        Button(onClick = onCreateInventoryClick) {
            Text("Crear Inventario")
        }

        // Lista de inventarios
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(uiState.inventories) { inventory ->
                InventoryCard(inventory = inventory, onClick = { onInventoryClick(inventory) })
            }
        }
    }
}

@Composable
fun InventoryCard(inventory: Inventory, onClick: (Inventory) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(inventory) },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = inventory.name, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = inventory.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInventoryListScreen() {
    // Simulamos los datos para los inventarios
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

    // Simulamos un ViewModel con un StateFlow
    val previewViewModel = object : InventoryListViewModel() {
        override val uiState: StateFlow<InventoryListState> = MutableStateFlow(InventoryListState(inventories = sampleInventories))
    }

    // Pasamos el ViewModel simulado y las funciones necesarias para la vista previa
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
        }
    )
}
