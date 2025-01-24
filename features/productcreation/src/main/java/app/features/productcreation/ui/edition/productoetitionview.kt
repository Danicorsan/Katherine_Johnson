package app.features.productcreation.ui.edition

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.components.LoadingUi
import app.features.productcreation.R
import app.features.productcreation.ui.base.components.ProductForm
import app.features.productcreation.ui.base.ProductViewState
import app.features.productcreation.ui.base.ProductEvents
import app.features.productcreation.ui.base.components.ProductCreationAppbar
import app.features.productcreation.ui.base.components.ProductCreationFloatingActionButton


@Preview(showSystemUi = true)
@Composable
fun ProductEditionScreen(
    viewModel: ProductEditionViewModel = remember { ProductEditionViewModel()}
){
    ProductEditionHost(
        productState = viewModel.productViewState,
        productEvents = ProductEvents.build(viewModel)
    )
}

@Composable
private fun ProductEditionHost(
    productState: ProductViewState,
    productEvents : ProductEvents
){
    Scaffold(
        topBar = { ProductCreationAppbar(
            titleText = stringResource(R.string.creation_product_title_appbar),
            onLeavePage = productEvents.onLeavePage) },
        floatingActionButton = {
            ProductCreationFloatingActionButton(productEvents.onAcceptProduct)
        }
    ) { contentPadding ->
        when {
            productState.isLoading -> LoadingUi()
            else -> ProductEditionContent(
                modifier = Modifier.padding(contentPadding),
                productState = productState,
                productEvents = productEvents
            )
        }
    }
}

@Composable
private fun ProductEditionContent(
    modifier: Modifier,
    productState: ProductViewState,
    productEvents : ProductEvents
    ){
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ProductForm(
            productState,
            productEvents
        )
    }
}
