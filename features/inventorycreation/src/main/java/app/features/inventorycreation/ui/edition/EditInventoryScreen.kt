package app.features.inventorycreation.ui.edition

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.features.inventorycreation.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditInventoryScreen(
    viewModel: EditInventoryViewModel,
    inventoryId: Int,
    onInventoryEdited: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    if (uiState.inventoryId != inventoryId) {
        viewModel.loadInventory(inventoryId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.editar_inventario)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
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
                TextField(
                    value = uiState.inventoryName,
                    onValueChange = { viewModel.onInventoryNameChange(it) },
                    label = { Text(stringResource(R.string.nuevo_nombre_del_inventario)) },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = uiState.inventoryDescription,
                    onValueChange = { viewModel.onInventoryDescriptionChange(it) },
                    label = { Text(stringResource(R.string.nueva_descripcion_del_inventario)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        viewModel.saveChanges()
                        onInventoryEdited()
                        onNavigateBack()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = uiState.isSaveButtonEnabled
                ) {
                    Text(stringResource(R.string.guardar_cambios))
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewEditInventoryScreen() {
    // Simulamos un estado de UI para la pantalla de edición
    val uiState = remember {
        MutableStateFlow(
            EditInventoryState(
                inventoryId = 1,
                inventoryName = "Inventario Editado",
                inventoryDescription = "Descripción editada del inventario",
                isSaveButtonEnabled = true
            )
        )
    }

    // Usamos el repositorio simulado para la vista previa
    val previewViewModel = object : EditInventoryViewModel(FakeInventoryRepository()) {
        override val uiState: StateFlow<EditInventoryState> = uiState

        // Sobrescribimos las funciones necesarias para la vista previa
        override fun loadInventory(inventoryId: Int) {
            uiState.value = EditInventoryState(
                inventoryId = inventoryId,
                inventoryName = "Inventario Editado",
                inventoryDescription = "Descripción editada del inventario",
                isSaveButtonEnabled = true
            )
        }

        override fun onInventoryNameChange(newName: String) {
            uiState.value = uiState.value.copy(inventoryName = newName)
        }

        override fun onInventoryDescriptionChange(newDescription: String) {
            uiState.value = uiState.value.copy(inventoryDescription = newDescription)
        }

        override fun saveChanges() {
            // Simulamos el guardado
            println("Cambios guardados para el inventario ${uiState.value.inventoryId}")
        }
    }

    // Pasamos el ViewModel simulado y las funciones necesarias para la vista previa
    EditInventoryScreen(
        viewModel = previewViewModel,
        inventoryId = 1,
        onInventoryEdited = {
            // Acción simulada de inventario editado
            println("Inventario editado")
        },
        onNavigateBack = {
            // Acción simulada de retroceso
            println("Volver a la lista de inventarios")
        }
    )
}
