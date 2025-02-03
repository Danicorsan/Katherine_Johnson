package app.features.inventorycreation.ui.creation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseAlertDialog
import app.domain.invoicing.repository.InventoryRepository
import app.features.inventorycreation.R
import app.features.inventorylist.ui.InventoryListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateInventoryScreen(
    onNavigateToList: () -> Unit,
    onBackClick: () -> Unit,
    inventoryListViewModel: InventoryListViewModel
) {
    val viewModel: CreateInventoryViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value
    var showDialog by remember { mutableStateOf(false) }
    var isInventoryCreated by remember { mutableStateOf(false) }  // Estado para gestionar la creación del inventario

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text = stringResource(R.string.crear_inventario)
                ) },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.volver))
                    }
                }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TextField(
                        value = uiState.inventoryName,
                        onValueChange = { viewModel.onInventoryNameChange(it) },
                        label = { Text(stringResource(R.string.nombre_del_inventario)) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    TextField(
                        value = uiState.inventoryDescription,
                        onValueChange = { viewModel.onInventoryDescriptionChange(it) },
                        label = { Text(stringResource(R.string.descripcion_del_inventario))},
                        modifier = Modifier.fillMaxWidth()
                    )

                    uiState.errorMessage?.let {
                        Text(
                            text = it,
                            color = Color.Red,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Button(
                        onClick = {
                            viewModel.createInventory { newInventory ->
                                // Después de crear el inventario, mostrar el diálogo
                                inventoryListViewModel.addInventory(newInventory)
                                isInventoryCreated = true  // Marcar que el inventario fue creado
                                showDialog = true  // Mostrar el diálogo
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = uiState.isCreateButtonEnabled && !uiState.isLoading
                    ) {
                        Text(stringResource(R.string.crear_inventario))
                    }
                }

                // Mostrar el LoadingUI sobre el contenido si está cargando
                if (uiState.isLoading && !isInventoryCreated) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                            .padding(16.dp)
                    ) {
                        LoadingUi()
                    }
                }
            }
        }
    )

    // Mostrar el BaseAlertDialog después de la creación del inventario
    if (showDialog) {
        BaseAlertDialog(
            title = stringResource(R.string.inventario_creado_exito),
            text = stringResource(R.string.inventario_creado_exito),
            confirmText = stringResource(R.string.aceptar),
            onConfirm = {
                showDialog = false
                isInventoryCreated = false  // Reiniciar el estado de creación del inventario
                onNavigateToList() // Redirigir a la pantalla de lista
            },
            onDismiss = {
                showDialog = false
                isInventoryCreated = false  // Reiniciar el estado de creación del inventario
                onNavigateToList() // Redirigir a la pantalla de lista si se descarta el dialogo
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCreateInventoryScreen() {
    CreateInventoryScreen(
        onNavigateToList = {},
        onBackClick = {},
        inventoryListViewModel = InventoryListViewModel(InventoryRepository)
    )
}
