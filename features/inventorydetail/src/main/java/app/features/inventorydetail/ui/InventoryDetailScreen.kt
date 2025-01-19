package app.features.inventorydetail.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.domain.invoicing.inventory.Item
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun InventoryDetailScreen(
    viewModel: ViewModel,
    inventoryId: Int,
    onEditClick: () -> Unit,  // Callback cuando se haga clic en editar
    onNavigateBack: () -> Unit // Callback para navegar hacia atrás
) {
    // Obtén el ViewModel
    val viewModel: InventoryDetailViewModel = viewModel(factory = InventoryDetailViewModelFactory(InventoryRepository()))

    // Obtén el estado del ViewModel
    val uiState by viewModel.uiState.collectAsState()

    // Llamamos a loadInventoryDetails solo si el inventario no está cargado o es diferente al id que se está solicitando
    LaunchedEffect(inventoryId) {
        viewModel.loadInventoryDetails(inventoryId)
    }

    // Layout de la pantalla
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // Botón de retroceso con ícono de flecha
        IconButton(
            onClick = { onNavigateBack() },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = Color.Black
            )
        }

        // Título de la pantalla
        Text(
            text = "Detalle del Inventario",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        // Verificamos si estamos cargando los datos
        if (uiState.inventory == null) {
            // Si no se tiene el inventario, mostrar un mensaje de carga o error
            Text(text = "Cargando inventario...")
        } else {
            // Si el inventario está disponible, mostramos sus detalles
            uiState.inventory?.let { inventory ->
                Text(text = "Nombre: ${inventory.name}")
                Text(text = "Descripción: ${inventory.description}")

                // Botón para editar el inventario
                Button(
                    onClick = onEditClick,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Editar Inventario")
                }

                // Mostramos los artículos dentro del inventario
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(uiState.items) { item ->
                        ItemCard(item = item as Item)
                    }
                }
            }
        }
    }
}

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

// Factory para la creación del ViewModel (si es necesario)
class InventoryDetailViewModelFactory(
    private val inventoryRepository: InventoryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InventoryDetailViewModel(inventoryRepository) as T
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInventoryDetailScreen() {
    // Llamamos a la pantalla con los datos de muestra
    InventoryDetailScreen(
        inventoryId = 1,
        onEditClick = {
            println("Editar inventario con ID: 1")
        },
        onNavigateBack = {
            println("Volver a la lista de inventarios")
        },
        viewModel = viewModel()
    )
}
