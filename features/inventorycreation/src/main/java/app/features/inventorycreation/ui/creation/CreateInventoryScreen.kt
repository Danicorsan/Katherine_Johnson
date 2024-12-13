package app.features.inventorycreation.ui.creation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.features.inventorycreation.R
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateInventoryScreen(viewModel: CreateInventoryViewModel = viewModel()) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.crear_inventario)) })
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
                    value = uiState.value.inventoryName,
                    onValueChange = { viewModel.onInventoryNameChange(it) },
                    label = { Text(stringResource(R.string.nombre_del_inventario)) },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = uiState.value.inventoryDescription,
                    onValueChange = { viewModel.onInventoryDescriptionChange(it) },
                    label = { Text(stringResource(R.string.descripci_n_del_inventario)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = { viewModel.createInventory() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = uiState.value.isCreateButtonEnabled
                ) {
                    Text(stringResource(R.string.crear_inventario))
                }
            }
        }
    )
}

@Preview(showBackground = true, name = "Create Inventory Preview")
@Composable
fun PreviewCreateInventoryScreen() {
    val mockViewModel = object : CreateInventoryViewModel() {
        override val uiState = MutableStateFlow(
            CreateInventoryState(
                inventoryName = "Sample Inventory",
                inventoryDescription = "This is a sample description.",
                isCreateButtonEnabled = true
            )
        )
    }
    CreateInventoryScreen(viewModel = mockViewModel)
}
