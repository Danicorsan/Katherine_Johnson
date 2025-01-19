package app.features.inventorycreation.ui.creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import app.domain.invoicing.repository.InventoryRepository
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateInventoryScreen(
    onNavigateToList: () -> Unit,
    onBackClick: () -> Unit  // Callback para el botón de retroceso
) {
    // Instanciamos el repositorio
    val inventoryRepository = remember { InventoryRepository() }

    // Pasamos el repositorio al ViewModel
    val viewModel: CreateInventoryViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CreateInventoryViewModel(inventoryRepository) as T
            }
        }
    )

    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear Inventario") },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Campo de nombre del inventario
                TextField(
                    value = uiState.inventoryName,
                    onValueChange = { viewModel.onInventoryNameChange(it) },
                    label = { Text("Nombre del Inventario") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Campo de descripción del inventario
                TextField(
                    value = uiState.inventoryDescription,
                    onValueChange = { viewModel.onInventoryDescriptionChange(it) },
                    label = { Text("Descripción del Inventario") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Botón para crear el inventario
                Button(
                    onClick = {
                        viewModel.createInventory()  // Crear el inventario
                        onNavigateToList()  // Navegar de vuelta a la lista
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = uiState.isCreateButtonEnabled  // Solo habilitar si ambos campos no están vacíos
                ) {
                    Text("Crear Inventario")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateInventoryScreen() {
    // Simulamos un ViewModel con un estado de UI
    val previewViewModel = object : CreateInventoryViewModel(InventoryRepository()) {
        override val uiState: MutableStateFlow<CreateInventoryState> = MutableStateFlow(
            CreateInventoryState(
                inventoryName = "Nuevo Inventario",
                inventoryDescription = "Descripción del inventario",
                isCreateButtonEnabled = true
            )
        )
    }

    // Pasamos el ViewModel simulado y la función necesaria para la vista previa
    CreateInventoryScreen(
        onNavigateToList = {
            // Acción simulada de navegación
            println("Volver a la lista de inventarios")
        },
        onBackClick = {
            // Acción simulada de retroceso
            println("Volver a la pantalla anterior")
        }
    )
}
