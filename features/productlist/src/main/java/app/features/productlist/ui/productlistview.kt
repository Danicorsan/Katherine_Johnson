package app.features.productlist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.base.ui.components.LoadingUi
import app.base.ui.composables.AppDrawer
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.MediumButton
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.features.productlist.R
import app.features.productlist.ui.base.ProductListEvents
import app.features.productlist.ui.base.ProductListNavigationEvents
import app.features.productlist.ui.base.ProductListState
import app.features.productlist.ui.base.components.ListProducts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = remember { ProductListViewModel()},
    navigationEvents: ProductListNavigationEvents = ProductListNavigationEvents(),
    onNavigateProducts: () -> Unit,
    onNavigateCategories: () -> Unit,
    onNavigateInventory: () -> Unit,
){
    // Definir el drawerState y el scope
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getAllProductList()
        viewModel.stablishNavigationEvents(navigationEvents)
    }

    AppDrawer(
        drawerState = drawerState,
        onNavigateProducts = { onNavigateProducts() },
        onNavigateCategories = { onNavigateCategories() },
        onNavigateInventory = { onNavigateInventory() },
        content = {
            ProductListHost(
                productListState = viewModel.productListState,
                productListEvents = ProductListEvents.build(viewModel),
                drawerState = drawerState,
                scope = scope
            )
        }
    )
}

@Composable
private fun ProductListHost(
    productListState: ProductListState,
    productListEvents: ProductListEvents,
    drawerState: DrawerState,
    scope: CoroutineScope,
) {
    Scaffold(
        topBar = {
            BaseAppBar(
                BaseAppBarState(
                    title = stringResource(R.string.title_appbar_product_list),
                    navigationIcon = BaseAppBarIcons.drawerMenuIcon(
                        onClick = { scope.launch { drawerState.open() } }
                    )
                )
            )
        },
        floatingActionButton = {
            MediumButton(
                onClick = productListEvents.onAddProduct,
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_product_floatingbutton_description)
            )
        }
    ) { contentPadding ->
        when {
            productListState.isLoading -> LoadingUi()
            productListState.productIsBeingDeleted -> BaseAlertDialog(
                title = stringResource(R.string.delete_alert_dialog_title),
                text = stringResource(R.string.delete_alert_dialog_message),
                confirmText = stringResource(R.string.delete_alert_dialog_confirmation_text),
                dismissText = stringResource(R.string.delete_alert_dialog_dismiss_text),
                onConfirm = productListEvents.onConfirmDeleteProduct,
                onDismiss = productListEvents.onDissmissDeleteProduct
            )

            else -> ProductListContent(
                modifier = Modifier.padding(contentPadding),
                productListState = productListState,
                productListEvents = productListEvents
            )
        }
    }
}

@Composable
private fun ProductListContent(
    modifier: Modifier,
    productListState: ProductListState,
    productListEvents: ProductListEvents,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        ListProducts(productListState.productList, productListEvents)
    }
}