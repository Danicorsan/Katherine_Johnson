package app.features.productlist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.MediumButton
import app.features.productlist.R
import app.features.productlist.ui.base.components.ListProducts
import app.features.productlist.ui.base.ProductListEvents
import app.features.productlist.ui.base.ProductListNavigationEvents
import app.features.productlist.ui.base.ProductListState
import app.features.productlist.ui.base.components.ProductListAppBar


@Preview(showSystemUi = true)
@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = remember { ProductListViewModel(ProductListNavigationEvents())},
){
    ProductListHost(
        productListState = viewModel.productListState,
        productListEvents = ProductListEvents.build(viewModel)
    )
}

@Composable
private fun ProductListHost(
    productListState : ProductListState,
    productListEvents : ProductListEvents
){
    Scaffold(
        topBar = { ProductListAppBar(
            titleText = stringResource(R.string.title_appbar),
            onLeavePage = productListEvents.onGoBack
        ) },
        floatingActionButton = { MediumButton(
            onClick = productListEvents.onAddProduct,
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.add_product_floatingbutton_description)
        ) }
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
    productListEvents: ProductListEvents){
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        ListProducts(productListState.productList, productListEvents)
    }
}