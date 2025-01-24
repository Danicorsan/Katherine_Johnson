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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.domain.invoicing.inventory.Inventory
import app.features.inventorycreation.R
import app.features.inventorylist.ui.InventoryListViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateInventoryScreen(
    onNavigateToList: () -> Unit,
    onBackClick: () -> Unit,
    inventoryListViewModel: InventoryListViewModel
) {
    val viewModel: CreateInventoryViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.crear_inventario)) },
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
                TextField(
                    value = uiState.inventoryName,
                    onValueChange = { viewModel.onInventoryNameChange(it) },
                    label = { Text(stringResource(R.string.nombre_del_inventario)) },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = uiState.inventoryDescription,
                    onValueChange = { viewModel.onInventoryDescriptionChange(it) },
                    label = { Text(stringResource(R.string.descripcion_del_inventario)) },
                    modifier = Modifier.fillMaxWidth()
                )

                uiState.errorMessage?.let {
                    Text(
                        text = it,
                        color = androidx.compose.ui.graphics.Color.Red,
                        modifier = Modifier.fillMaxWidth(),
                        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                    )
                }

                Button(
                    onClick = {
                        viewModel.createInventory()
                        inventoryListViewModel.addInventory(
                            Inventory(
                                id = System.currentTimeMillis().toInt(),
                                name = uiState.inventoryName,
                                description = uiState.inventoryDescription,
                                items = emptyList(),
                                createdAt = Date()
                            )
                        )
                        onNavigateToList()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = uiState.isCreateButtonEnabled && !uiState.isLoading
                ) {
                    Text(stringResource(R.string.crear_inventario))
                }
            }
        }
    )
}
