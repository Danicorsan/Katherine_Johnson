package app.features.inventorydetail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.domain.invoicing.product.Product
import app.features.inventorydetail.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryDetailScreen(
    inventoryId: Int,
    onNavigateBack: () -> Unit,
) {
    val viewModel: InventoryDetailViewModel = viewModel()

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(inventoryId) {
        viewModel.loadInventoryDetails(inventoryId)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.detalle_del_inventario),
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            navigationIcon = {
                IconButton(onClick = { onNavigateBack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.volver)
                    )
                }
            },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (uiState.inventory == null) {
            Text(text = stringResource(R.string.cargando_inventario))
        } else {
            uiState.inventory?.let { inventory ->
                Text(
                    text = "ID:" + " " + inventory.id,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = stringResource(R.string.nombre) + " " + inventory.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = stringResource(R.string.descripcion) + " " + inventory.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = stringResource(R.string.fecha_creacion) + " " + inventory.createdAt,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = stringResource(R.string.fecha_actualizacion) + " " + inventory.updatedAt,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(uiState.items) { item ->
                        ItemCard(item = item)
                    }
                }
            }
        }
    }
}


@Composable
fun ItemCard(item: Product) {
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
            Text(
                text = item.code,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
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