package app.features.productlist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.components.LoadingUi
import app.base.ui.composables.NormalButton
import app.features.productlist.R
import app.features.productlist.ui.base.AddProductFloatingActionButton
import app.features.productlist.ui.base.Appbar
import app.features.productlist.ui.base.ListProducts
import app.features.productlist.ui.base.ProductListEvents
import app.features.productlist.ui.base.ProductListState


@Preview(showSystemUi = true)
@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = remember { ProductListViewModel()}
){
    viewModel.getReady()
    ProductListHost(
        productListState = viewModel.productListState ,
        productListEvents = ProductListEvents.build(viewModel)
    )
}

@Composable
private fun ProductListHost(
    productListState : ProductListState,
    productListEvents : ProductListEvents
){
    Scaffold(
        topBar = @Composable{ Appbar(stringResource(R.string.title_appbar), productListEvents)},
        floatingActionButton = { AddProductFloatingActionButton(productListEvents) }
    ) { contentPadding ->
        when {
            productListState.isLoading -> LoadingUi()
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