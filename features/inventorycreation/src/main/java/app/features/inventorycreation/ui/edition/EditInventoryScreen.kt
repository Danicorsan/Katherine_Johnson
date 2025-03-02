package app.features.inventorycreation.ui.edition

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.inventory.InventoryState
import app.domain.invoicing.inventory.InventoryType
import app.features.inventorycreation.R
import app.features.inventorycreation.ui.components.NotificationHelperEditInventory
import app.features.inventorycreation.ui.composables.CustomDropdownMenu

@Composable
fun EditInventoryScreen(
    viewModel: EditInventoryViewModel,
    inventoryId: Int,
    onNavigateBack: () -> Unit
) {
    val uiState = viewModel.vmState.collectAsState().value

    var selectedIcon by remember { mutableStateOf(uiState.inventoryIcon) }
    var isIconExpanded by remember { mutableStateOf(false) }
    var isTypeExpanded by remember { mutableStateOf(false) }
    var isStateExpanded by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf(uiState.inventoryType) }
    var selectedState by remember { mutableStateOf(uiState.inventoryState) }

    if (uiState.inventoryId != inventoryId) viewModel.loadInventory(inventoryId)
    val context = LocalContext.current

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            NotificationHelperEditInventory.showNotification(context)
        }
    }
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
                    value = uiState.inventoryShortName,
                    onValueChange = { viewModel.onInventoryShortNameChange(it) },
                    label = { Text(stringResource(R.string.nuevo_nombre_corto_del_inventario)) },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = uiState.inventoryDescription,
                    onValueChange = { viewModel.onInventoryDescriptionChange(it) },
                    label = { Text(stringResource(R.string.nueva_descripcion_del_inventario)) },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = uiState.inventoryCode,
                    onValueChange = { viewModel.onInventoryCodeChange(it) },
                    label = { Text(stringResource(R.string.nuevo_codigo_del_inventario)) },
                    modifier = Modifier.fillMaxWidth()
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


                Button(
                    onClick = {
                        viewModel.saveChanges()
                        NotificationHelperEditInventory.createNotificationChannel(context)
                        NotificationHelperEditInventory.askNotificationPermission(context, requestPermissionLauncher)
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