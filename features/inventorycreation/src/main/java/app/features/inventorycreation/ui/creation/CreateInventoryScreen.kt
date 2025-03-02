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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import app.domain.invoicing.inventory.InventoryState
import app.domain.invoicing.inventory.InventoryType
import app.domain.invoicing.repository.InventoryRepository
import app.features.inventorycreation.R
import app.features.inventorycreation.ui.composables.CustomDropdownMenu
import java.time.LocalDate

@Composable
fun CreateInventoryScreen(
    onNavigateToList: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: CreateInventoryViewModel
) {
    val uiState = viewModel.vmState
    var showSuccessDialog by remember { mutableStateOf(false) }
    var isInventoryCreated by remember { mutableStateOf(false) }
    val loading = uiState.loading

    var selectedType by remember { mutableStateOf(uiState.inventoryType) }
    var selectedIcon by remember { mutableStateOf(uiState.inventoryIcon) }
    var selectedState by remember { mutableStateOf(uiState.inventoryState) }
    var isIconExpanded by remember { mutableStateOf(false) }
    var isTypeExpanded by remember { mutableStateOf(false) }
    var isStateExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            BaseAppBar(
                BaseAppBarState(
                    title = stringResource(R.string.crear_inventario),
                    navigationIcon = BaseAppBarIcons.goBackPreviousScreenIcon {
                        onBackClick()
                    },
            ))
        },
        content = { paddingValues ->
            if (loading) {
                LoadingUi()
            }
            else{
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
                        modifier = Modifier.fillMaxWidth(),
                        isError = uiState.nameErrorMessage != null
                    )
                    if (uiState.nameErrorMessage != null) {
                        Text(
                            text = uiState.nameErrorMessage,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 8.dp) // Add some padding
                        )
                    }
                    TextField(
                        value = uiState.inventoryShortName,
                        onValueChange = { viewModel.onInventoryShortNameChange(it) },
                        label = { Text(stringResource(R.string.nombre_corto_del_inventario)) },
                        modifier = Modifier.fillMaxWidth(),
                        isError = uiState.shortNameErrorMessage != null
                    )
                    if (uiState.shortNameErrorMessage != null) {
                        Text(
                            text = uiState.shortNameErrorMessage,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 8.dp) // Add some padding
                        )
                    }

                    TextField(
                        value = uiState.inventoryDescription,
                        onValueChange = { viewModel.onInventoryDescriptionChange(it) },
                        label = { Text(stringResource(R.string.descripcion_del_inventario)) },
                        modifier = Modifier.fillMaxWidth(),
                        isError = uiState.descriptionErrorMessage != null
                    )
                    if (uiState.descriptionErrorMessage != null) {
                        Text(
                            text = uiState.descriptionErrorMessage,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 8.dp) // Add some padding
                        )
                    }

                    TextField(
                        value = uiState.inventoryCode,
                        onValueChange = { viewModel.onInventoryCodeChange(it) },
                        label = { Text(stringResource(R.string.codigo_del_inventario)) },
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isIconExpanded = true }
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

                        CustomDropdownMenu(
                            expanded = isIconExpanded,
                            onDismissRequest = { isIconExpanded = false },
                            items = InventoryIcon.entries,
                            onItemSelected = { icon ->
                                selectedIcon = icon
                                viewModel.onInventoryIconChange(icon)
                            },
                            itemText = { icon ->
                                when (icon) {
                                    InventoryIcon.NONE -> stringResource(R.string.ninguno)
                                    InventoryIcon.ELECTRONICS -> stringResource(R.string.electronica)
                                    InventoryIcon.TECHNOLOGY -> stringResource(R.string.tecnologia)
                                    InventoryIcon.MATERIALS -> stringResource(R.string.materiales)
                                    InventoryIcon.SERVICES -> stringResource(R.string.servicios)
                                    InventoryIcon.WAREHOUSE -> stringResource(R.string.almacen)
                                }
                            },
                            itemIcon = { icon ->
                                when (icon) {
                                    InventoryIcon.ELECTRONICS -> Icons.Filled.Call
                                    InventoryIcon.TECHNOLOGY -> Icons.Filled.Settings
                                    InventoryIcon.MATERIALS -> Icons.Filled.ShoppingCart
                                    InventoryIcon.SERVICES -> Icons.Filled.Notifications
                                    InventoryIcon.WAREHOUSE -> Icons.Filled.Home
                                    InventoryIcon.NONE -> Icons.Filled.Warning
                                }
                            }
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isTypeExpanded = true }
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
                            Text(
                                text = when (selectedType) {
                                    InventoryType.WEEKLY -> stringResource(R.string.semanal)
                                    InventoryType.MONTHLY -> stringResource(R.string.mensual)
                                    InventoryType.TRIMESTRAL -> stringResource(R.string.trimestral)
                                    InventoryType.SEMESTRAL -> stringResource(R.string.semestral)
                                    InventoryType.ANNUAL -> stringResource(R.string.anual)
                                    InventoryType.BIANNUAL -> stringResource(R.string.bianual)
                                    InventoryType.PERMANENT -> stringResource(R.string.permanente)
                                },
                                modifier = Modifier.weight(1f),
                                style = MaterialTheme.typography.bodyLarge
                            )

                            Icon(
                                imageVector = Icons.Filled.ArrowDropDown,
                                contentDescription = "Expand Menu",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            CustomDropdownMenu(
                                expanded = isTypeExpanded,
                                onDismissRequest = { isTypeExpanded = false },
                                items = InventoryType.entries,
                                onItemSelected = { type ->
                                    selectedType = type
                                    viewModel.onInventoryTypeChange(type)
                                },
                                itemText = { type ->
                                    when (type) {
                                        InventoryType.WEEKLY -> stringResource(R.string.semanal)
                                        InventoryType.MONTHLY -> stringResource(R.string.mensual)
                                        InventoryType.TRIMESTRAL -> stringResource(R.string.trimestral)
                                        InventoryType.SEMESTRAL -> stringResource(R.string.semestral)
                                        InventoryType.ANNUAL -> stringResource(R.string.anual)
                                        InventoryType.BIANNUAL -> stringResource(R.string.bianual)
                                        InventoryType.PERMANENT -> stringResource(R.string.permanente)
                                    }
                                }
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isStateExpanded = true }
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
                            Text(
                                text = when (selectedState) {
                                    InventoryState.HISTORY -> stringResource(R.string.historico)
                                    InventoryState.ACTIVE -> stringResource(R.string.activo)
                                    InventoryState.IN_PROGRESS -> stringResource(R.string.en_proceso)
                                },
                                modifier = Modifier.weight(1f),
                                style = MaterialTheme.typography.bodyLarge
                            )

                            Icon(
                                imageVector = Icons.Filled.ArrowDropDown,
                                contentDescription = "Expand Menu",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            CustomDropdownMenu(
                                expanded = isStateExpanded,
                                onDismissRequest = { isStateExpanded = false },
                                items = InventoryState.entries,
                                onItemSelected = { state ->
                                    selectedState = state
                                    viewModel.onInventoryStateChange(state)
                                },
                                itemText = { state ->
                                    when (state) {
                                        InventoryState.HISTORY -> stringResource(R.string.historico)
                                        InventoryState.ACTIVE -> stringResource(R.string.activo)
                                        InventoryState.IN_PROGRESS -> stringResource(R.string.en_proceso)
                                    }
                                }
                            )
                        }
                    }

                    uiState.error?.let {
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
                                Inventory(
                                    id = uiState.inventoryId,
                                    name = uiState.inventoryName,
                                    description = uiState.inventoryDescription,
                                    createdAt = LocalDate.now(),
                                    updatedAt = LocalDate.now(),
                                    icon = uiState.inventoryIcon,
                                    itemsCount = uiState.inventoryItemsCount,
                                    inventoryType = uiState.inventoryType,
                                    shortName = uiState.inventoryShortName,
                                    state = uiState.inventoryState,
                                    code = uiState.inventoryCode
                                )
                            )
                            showSuccessDialog = true
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = uiState.isCreateButtonEnabled && !uiState.loading
                    ) {
                        Text(stringResource(R.string.crear_inventario))
                    }


                }
            }
        }

    if (showSuccessDialog && !uiState.loading) {
        BaseAlertDialog(
            title = stringResource(R.string.inventario_creado_exito),
            text = stringResource(R.string.inventario_creado_exito),
            confirmText = stringResource(R.string.aceptar),
            onConfirm = {
                showSuccessDialog = false
                isInventoryCreated = false
                onNavigateToList()
            },
            onDismiss = {
                showSuccessDialog = false
                isInventoryCreated = false
                onNavigateToList()
            }
        )
    }
})}

@Preview(showBackground = true)
@Composable
fun PreviewCreateScreen() {
    CreateInventoryScreen(
        onNavigateToList = {},
        onBackClick = {},
        viewModel = CreateInventoryViewModel(
            repository = InventoryRepository
        )
    )
}