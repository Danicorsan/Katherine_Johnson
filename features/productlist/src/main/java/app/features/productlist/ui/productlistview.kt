package app.features.productlist.ui

import NoDataAnimatedScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
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
import app.base.ui.composables.baseappbar.Action
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.features.productlist.R
import app.features.productlist.ui.base.OrdenationState
import app.features.productlist.ui.base.ProductListEvents
import app.features.productlist.ui.base.ProductListNavigationEvents
import app.features.productlist.ui.base.ProductListState
import app.features.productlist.ui.base.components.ListProducts
import app.features.productlist.ui.base.composeicons.AscendSortIcon
import app.features.productlist.ui.base.composeicons.DescendSortIcon


@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = remember { ProductListViewModel() },
    navigationEvents: ProductListNavigationEvents = ProductListNavigationEvents(),
) {
    LaunchedEffect(Unit) {
        viewModel.getAllProductList()
        viewModel.stablishNavigationEvents(navigationEvents)
    }
    ProductListHost(
        productListState = viewModel.productListState,
        productListEvents = ProductListEvents(
            viewModel = viewModel,
            navigationEvents = navigationEvents
        ),
    )
}

@Composable
private fun ProductListHost(
    productListState: ProductListState,
    productListEvents: ProductListEvents,
) {
    val scopeCoroutine = rememberCoroutineScope()
    AppDrawer(
        drawerState = productListState.drawerState,
        onNavigateProducts = productListEvents.onNavigateProducts,
        onNavigateCategories = productListEvents.onNavigateCategories,
        onNavigateInventory = productListEvents.onNavigateInventory,
        content = {
            Scaffold(
                topBar = {
                    BaseAppBar(
                        BaseAppBarState(
                            title = stringResource(R.string.title_appbar_product_list),
                            navigationIcon = BaseAppBarIcons.drawerMenuIcon(
                                onClick = { productListEvents.onOpenDrawer(scopeCoroutine) }
                            ),
                            listOf(
                                getSortingAction(
                                    ordenationState = productListState.ordenationState,
                                    onSort = productListEvents.onSortList
                                )
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
                    productListState.productList.isEmpty() -> NoDataAnimatedScreen()
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
    )
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

@Composable
private fun getSortingAction(ordenationState: OrdenationState, onSort : () -> Unit) : Action{
    return if (ordenationState == OrdenationState.DESCENDING)
        Action(
            icon = DescendSortIcon,
            onClick = onSort,
            contentDescription = stringResource(R.string.sorting_icon_product_descending)
        )
    else
        Action(
            icon = AscendSortIcon,
            onClick = onSort,
            contentDescription = stringResource(R.string.sorting_icon_product_ascending)
        )
}