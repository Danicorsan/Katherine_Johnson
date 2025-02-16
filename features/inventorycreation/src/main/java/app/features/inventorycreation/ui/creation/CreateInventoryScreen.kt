package app.features.inventorycreation.ui.creation

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.repository.InventoryRepository
import app.features.inventorycreation.R
import java.util.Date

@Composable
fun CreateInventoryScreen(
    onNavigateToList: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: CreateInventoryViewModel
) {
    val uiState = viewModel.uiState.collectAsState().value
    var showDialog by remember { mutableStateOf(false) }
    var isInventoryCreated by remember { mutableStateOf(false) }

    val icons = InventoryIcon.entries.toTypedArray()
    var selectedIcon by remember { mutableStateOf(uiState.inventoryIcon) }
    var expanded by remember { mutableStateOf(false) }

    if (uiState.isLoading) {
        LoadingUi()
    }

    Scaffold(
        topBar = {
            BaseAppBar(
                BaseAppBarState(
                    title = stringResource(R.string.crear_inventario),
                    navigationIcon = BaseAppBarIcons.goBackPreviousScreenIcon {
                        onBackClick()
                    }
                )
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
                                imageVector = when (selectedIcon) {
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
                                text = when (selectedIcon) {
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
                                        selectedIcon = icon
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
                            viewModel.addInventory(
                                inventory = Inventory(
                                    id = uiState.inventoryId,
                                    name = uiState.inventoryName,
                                    description = uiState.inventoryDescription,
                                    items = emptyList(),
                                    createdAt = Date(),
                                    updatedAt = Date(),
                                    icon = uiState.inventoryIcon
                                )
                            )
                            isInventoryCreated = true
                            showDialog = true
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = uiState.isCreateButtonEnabled && !uiState.isLoading
                    ) {
                        Text(stringResource(R.string.crear_inventario))
                    }
                }
            }
        }
    )

    if (showDialog) {
        BaseAlertDialog(
            title = stringResource(R.string.inventario_creado_exito),
            text = stringResource(R.string.inventario_creado_exito),
            confirmText = stringResource(R.string.aceptar),
            onConfirm = {
                showDialog = false
                isInventoryCreated = false
                onNavigateToList()
            },
            onDismiss = {
                showDialog = false
                isInventoryCreated = false
                onNavigateToList()
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
        viewModel = CreateInventoryViewModel(InventoryRepository)
    )
}