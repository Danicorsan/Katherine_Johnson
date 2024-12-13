package app.features.inventorylist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.domain.invoicing.inventory.Inventory
import app.features.inventorylist.R

@Composable
fun InventoryListScreen(
    viewModel: InventoryListViewModel,
    onBackClick: () -> Unit,
    onInventoryClick: (Inventory) -> Unit
) {
    // Obtenemos el estado actual del ViewModel
    val uiState = viewModel.uiState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Icono de retroceso
        IconButton(
            onClick = { onBackClick() },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.volver),
            )
        }

        // Título de la pantalla
        Text(
            text = stringResource(R.string.lista_de_inventarios),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
        )

        // Lista de inventarios
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(uiState.inventories) { inventory ->
                InventoryCard(inventory = inventory, onClick = { onInventoryClick(inventory) })
            }
        }
    }
}

@Composable
fun InventoryCard(inventory: Inventory, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = inventory.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        letterSpacing = 0.5.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.cantidad, inventory.quantity),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        letterSpacing = 0.5.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.valor_acumulado, inventory.price),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InventoryListScreenPreview() {
    // Creamos una lista de inventarios para la vista previa
    val inventories = listOf(
        Inventory(1, "Camisa", "Camisa grande", 45, 15.99, emptyList()),
        Inventory(2, "Pantalón", "Pantalón de mezclilla", 30, 25.99, emptyList()),
        Inventory(3, "Zapatos", "Zapatos deportivos", 20, 35.99, emptyList())
    )

    // Creamos un ViewModel de prueba
    val previewViewModel = object : InventoryListViewModel() {
        override fun loadInventoryList() {
            val sampleItems = listOf(
                Inventory(1, "Camisa", "Camisa grande", 45, 15.99, emptyList()),
                Inventory(2, "Pantalón", "Pantalón de mezclilla", 30, 25.99, emptyList()),
                Inventory(3, "Zapatos", "Zapatos deportivos", 20, 35.99, emptyList())
            )
            _uiState.value = InventoryListState(inventories = sampleItems)
        }
    }

    // Llamamos a la pantalla con los datos de muestra
    InventoryListScreen(viewModel = previewViewModel, onBackClick = {}, onInventoryClick = {})
}
