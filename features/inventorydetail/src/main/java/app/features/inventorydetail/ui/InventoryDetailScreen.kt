package app.features.inventorydetail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.domain.invoicing.repository.InventoryRepository
import app.features.inventorydetail.R
import app.features.inventorydetail.ui.base.TableRow

@Composable
fun InventoryDetailScreen(
    inventoryId: Int,
    onNavigateBack: () -> Unit,
) {
    val viewModel = remember { InventoryDetailViewModel(
        repository = InventoryRepository
    ) }

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(inventoryId) {
        viewModel.loadInventoryDetails(inventoryId)
    }

    Scaffold(
        topBar = {
            BaseAppBar(
                BaseAppBarState(
                    title = stringResource(R.string.detalle_del_inventario),
                    navigationIcon = BaseAppBarIcons.goBackPreviousScreenIcon {
                        onNavigateBack()
                    }
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (uiState.inventory == null) {
                Text(
                    text = stringResource(R.string.cargando_inventario),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                val inventory = uiState.inventory!!

                Text(
                    text = inventory.name,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = inventory.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(8.dp))

                TableRow(label = stringResource(R.string.id), value = inventory.id.toString())
                TableRow(label = stringResource(R.string.fecha_creacion), value = inventory.createdAt.toString())
                TableRow(label = stringResource(R.string.fecha_actualizacion), value = inventory.updatedAt.toString())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInventoryDetailScreen() {
    InventoryDetailScreen(
        inventoryId = 1,
        onNavigateBack = {
            println("Volver a la lista de inventarios")
        }
    )
}
