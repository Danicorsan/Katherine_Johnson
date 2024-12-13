import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.features.inventorycreation.R
import app.features.inventorycreation.ui.edition.EditInventoryState
import app.features.inventorycreation.ui.edition.EditStateViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditInventoryScreen(viewModel: EditStateViewModel = viewModel()) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.editar_inventario)) })
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
                    label = { Text(stringResource(R.string.nuevo_nombre_de_inventario)) },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = uiState.value.inventoryDescription,
                    onValueChange = { viewModel.onInventoryDescriptionChange(it) },
                    label = { Text(stringResource(R.string.nueva_descripci_n_del_inventario)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = { viewModel.saveChanges() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = uiState.value.isSaveButtonEnabled
                ) {
                    Text(stringResource(R.string.guardar_cambios))
                }
            }
        }
    )
}

@Preview(showBackground = true, name = "Edit Inventory Preview")
@Composable
fun PreviewEditInventoryScreen() {
    val mockViewModel = object : EditStateViewModel() {
        override val uiState: StateFlow<EditInventoryState> = MutableStateFlow(
            EditInventoryState(
                inventoryName = "Sample Inventory",
                inventoryDescription = "Description of the inventory.",
                isSaveButtonEnabled = true
            )
        )
    }
    EditInventoryScreen(viewModel = mockViewModel)
}
