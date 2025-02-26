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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.components.LoadingUi
import app.base.ui.composables.MediumButton
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import app.features.inventorylist.R
import app.features.inventorylist.ui.base.InventoryCard

@Composable
fun InventoryListScreen(
    viewModel: InventoryListViewModel,
    onBackClick: () -> Unit,
    onInventoryClick: (Inventory) -> Unit,
    onCreateInventoryClick: () -> Unit,
) {
    // Listar
    val state = viewModel.uiState
    val success = state.success
    val noData = success.isEmpty()

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
                if (noData) {
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
                            )
                        }
                    }
                }
            }
        }
    )
}





@Preview(showSystemUi = true)
@Composable
fun InventoryListPreview() {
    InventoryListScreen(
        viewModel = InventoryListViewModel(InventoryRepository),
        onBackClick = {},
        onInventoryClick = {},
        onCreateInventoryClick = {},
    )
}
