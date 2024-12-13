package app.features.inventorydetail.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.domain.invoicing.inventory.Item

@Composable
fun ItemCard(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun InventoryDetailScreen(viewModel: InventoryDetailViewModel) {
    // Obtenemos el estado actualizado usando collectAsState
    val uiState = viewModel.uiState.collectAsState().value

    // Cargamos los detalles solo si los artículos están vacíos
    if (uiState.items.isEmpty()) {
        viewModel.loadInventoryDetails()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // Título de la pantalla
        Text(
            text = "Detalle del Inventario",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        // Lista de artículos dentro del inventario
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(uiState.items) { item ->
                ItemCard(item = item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInventoryDetailScreen() {
    // Simulación del ViewModel para la vista previa
    val previewViewModel = object : InventoryDetailViewModel() {
        override fun loadInventoryDetails() {
            val sampleItems = listOf(
                Item(1, "Camiseta Roja", "Camiseta de algodón, talla M"),
                Item(2, "Pantalones Azules", "Pantalones de mezclilla, talla 32"),
                Item(3, "Zapatos Negros", "Zapatos de cuero, talla 42")
            )
            _uiState.value = InventoryDetailState(items = sampleItems)
        }
    }

    // Llamamos a la pantalla con los datos de muestra
    InventoryDetailScreen(viewModel = previewViewModel)
}
