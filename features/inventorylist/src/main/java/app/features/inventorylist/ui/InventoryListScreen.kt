package app.features.inventorylist.ui

import NoDataScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.MediumButton
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import app.features.inventorylist.R
import app.features.inventorylist.ui.base.InventoryCard
import kotlinx.coroutines.delay

@Composable
fun InventoryListScreen(
    viewModel: InventoryListViewModel,
    onBackClick: () -> Unit,
    onInventoryClick: (Inventory) -> Unit,
    onCreateInventoryClick: () -> Unit,
    onEditInventoryClick: (Inventory) -> Unit
) {
    // Listar
    val state = viewModel.uiState
    val success = state.success
    var loading = state.loading
    var noData = success.isEmpty()

    // Eliminar
    var showDialog by remember { mutableStateOf(false) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var selectedInventory: Inventory? by remember { mutableStateOf(null) }
    val onDeleteInventoryClick: (Inventory) -> Unit = { inventory ->
        selectedInventory = inventory
        showDialog = true
    }

    Scaffold(
        topBar = {
            BaseAppBar(
                BaseAppBarState(
                    title = stringResource(R.string.lista_de_inventarios),
                    navigationIcon = BaseAppBarIcons.goBackPreviousScreenIcon {
                        onBackClick()
                    }
                )
            )
        },
        floatingActionButton = {
            MediumButton(
                onClick = onCreateInventoryClick,
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.añadir_inventario)
            )
        },
        content = { paddingValues ->
            if (state.loading) {
                LoadingUi()
            } else {
                if (success.isEmpty()) {
                    NoDataScreen()
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        items(success) { inventory ->
                            InventoryCard(
                                inventory = inventory,
                                onClick = { onInventoryClick(inventory) },
                                onDeleteClick = { onDeleteInventoryClick(inventory) },
                                onEditClick = { onEditInventoryClick(inventory) }
                            )
                        }
                    }
                }
            }
        }
    )
    LaunchedEffect(Unit) {
        loading = true
        delay(1000)
        loading = false
    }
    if (showDialog && !loading) {
        selectedInventory?.let { inventory ->
            BaseAlertDialog(
                title = stringResource(R.string.eliminar_inventario),
                text = stringResource(R.string.seguro_que_quieres_eliminar_el_inventario),
                onDismiss = { showDialog = false },
                onConfirm = {
                    showDialog = false
                    loading = true
                    viewModel.deleteInventory(inventory)
                    showConfirmDialog = true

                },
                confirmText = stringResource(R.string.si_quiero_eliminarlo),
                dismissText = stringResource(R.string.no_no_quiero_eliminarlo)
            )
        }
    }
    if (showConfirmDialog && !loading) {
        BaseAlertDialog(
            title = stringResource(R.string.eliminar_inventario),
            text = stringResource(R.string.inventario_eliminado),
            onConfirm = { showConfirmDialog = false },
            confirmText = stringResource(R.string.aceptar),
            onDismiss = {}
        )
    }
}





@Preview(showSystemUi = true)
@Composable
fun InventoryListPreview() {
    InventoryListScreen(
        viewModel = InventoryListViewModel(InventoryRepository),
        onBackClick = {},
        onInventoryClick = {},
        onCreateInventoryClick = {},
        onEditInventoryClick = {}
    )
}
