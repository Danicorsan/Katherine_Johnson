package app.features.inventorycreation.ui.edition

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.repository.InventoryRepository
import app.features.inventorycreation.R

@Composable
fun EditInventoryScreen(
    viewModel: EditInventoryViewModel,
    inventoryId: Int,
    onNavigateBack: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    val icons = InventoryIcon.entries.toTypedArray()
    var expanded by remember { mutableStateOf(false) }

    if (uiState.inventoryId != inventoryId) viewModel.loadInventory(inventoryId)

    Scaffold(
        topBar = {
            BaseAppBar(
                BaseAppBarState(
                    title = stringResource(R.string.editar_inventario),
                    navigationIcon = BaseAppBarIcons.goBackPreviousScreenIcon {
                        onNavigateBack()
                    }
                )
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true }
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.onSurface,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = when (uiState.inventoryIcon) {
                                InventoryIcon.ELECTRONICS -> Icons.Filled.Call
                                InventoryIcon.TECHNOLOGY -> Icons.Filled.Settings
                                InventoryIcon.MATERIALS -> Icons.Filled.ShoppingCart
                                InventoryIcon.SERVICES -> Icons.Filled.Notifications
                                InventoryIcon.WAREHOUSE -> Icons.Filled.Home
                                InventoryIcon.NONE -> Icons.Filled.Warning
                            },
                            contentDescription = "Selected Icon",
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = when (uiState.inventoryIcon) {
                                InventoryIcon.NONE -> stringResource(R.string.ninguno)
                                InventoryIcon.ELECTRONICS -> stringResource(R.string.electronica)
                                InventoryIcon.TECHNOLOGY -> stringResource(R.string.tecnologia)
                                InventoryIcon.MATERIALS -> stringResource(R.string.materiales)
                                InventoryIcon.SERVICES -> stringResource(R.string.servicios)
                                InventoryIcon.WAREHOUSE -> stringResource(R.string.almacen)
                            },
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Expand Menu",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        icons.forEach { icon ->
                            DropdownMenuItem(
                                onClick = {
                                    expanded = false
                                    viewModel.onInventoryIconChange(icon)
                                },
                                text = {
                                    Text(
                                        text = when (icon) {
                                            InventoryIcon.NONE -> stringResource(R.string.ninguno)
                                            InventoryIcon.ELECTRONICS -> stringResource(R.string.electronica)
                                            InventoryIcon.TECHNOLOGY -> stringResource(R.string.tecnologia)
                                            InventoryIcon.MATERIALS -> stringResource(R.string.materiales)
                                            InventoryIcon.SERVICES -> stringResource(R.string.servicios)
                                            InventoryIcon.WAREHOUSE -> stringResource(R.string.almacen)
                                        }
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = when (icon) {
                                            InventoryIcon.ELECTRONICS -> Icons.Filled.Call
                                            InventoryIcon.TECHNOLOGY -> Icons.Filled.Settings
                                            InventoryIcon.MATERIALS -> Icons.Filled.ShoppingCart
                                            InventoryIcon.SERVICES -> Icons.Filled.Notifications
                                            InventoryIcon.WAREHOUSE -> Icons.Filled.Home
                                            InventoryIcon.NONE -> Icons.Filled.Warning
                                        },
                                        contentDescription = "Icon"
                                    )
                                }
                            )
                        }
                    }
                }


                Button(
                    onClick = {
                        viewModel.saveChanges()
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
@Preview(showSystemUi = true)
@Composable
fun PreviewEditInventoryScreen() {
    EditInventoryScreen(
        viewModel = EditInventoryViewModel(
            InventoryRepository
        ),
        inventoryId = 1,
        onNavigateBack = {}
    )
}