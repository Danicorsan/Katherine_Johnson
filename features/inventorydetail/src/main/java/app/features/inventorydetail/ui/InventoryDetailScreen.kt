package app.features.inventorydetail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.detalle_del_inventario),
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
                actions = {
                    IconButton(onClick = { viewModel.onInfoClick() }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = stringResource(R.string.info_inventario)
                        )
                    }
                },

            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            if (uiState.showInfoDialog) {
                InfoInventoryAlertDialog(
                    uiState = uiState,
                    onDismiss = { viewModel.onInfoDialogDismiss() }
                )
            }
            if (uiState.inventory == null || uiState.items.isEmpty()) {
                Text(text = stringResource(R.string.cargando_inventario))
            } else {
                uiState.inventory?.let { inventory ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.nombre) + " " + inventory.name,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = stringResource(R.string.descripcion) + " " + inventory.description,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    ) {
                        Text(
                            text = "ID: " + " " + inventory.id,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = stringResource(R.string.fecha_creacion) + " " + inventory.createdAt,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = stringResource(R.string.fecha_actualizacion) + " " + inventory.updatedAt,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(uiState.items) { item ->
                            ItemCard(item = item)
                        }
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
@Composable
fun InfoInventoryAlertDialog(
    uiState: InventoryDetailState,
    onDismiss: () -> Unit
) {
    val inventory = uiState.inventory
    if (inventory != null) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = stringResource(id = R.string.info_inventario),
                    style = MaterialTheme.typography.displaySmall, // Estilo de título más grande y claro
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(top = 12.dp)
                ) {
                    // Sección ID
                    InfoRow(label = stringResource(id = R.string.id), value = inventory.id.toString())

                    // Sección Nombre
                    InfoRow(label = stringResource(id = R.string.nombre), value = inventory.name)

                    // Sección Descripción
                    InfoRow(label = stringResource(id = R.string.descripcion), value = inventory.description)
                }
            },
            confirmButton = {
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .height(50.dp), // Botón más grande
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary, // Color de texto en el botón
                    )
                ) {
                    Text(
                        text = stringResource(id = android.R.string.ok),
                        style = MaterialTheme.typography.labelSmall, // Estilo más prominente para el botón
                        fontSize = 18.sp
                    )
                }
            },
            shape = MaterialTheme.shapes.medium // Bordes redondeados
        )
    }
}

// Composable reutilizable para filas con datos
@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp) // Espaciado más grande entre filas
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold), // Etiquetas más destacadas
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall, // Valor con un estilo más suave
            modifier = Modifier.weight(2f) // Mayor espacio para el valor
        )
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