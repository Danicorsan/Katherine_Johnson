package app.features.productcreation.ui.edition

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
import app.features.productcreation.ui.base.Appbar
import app.features.productcreation.ui.base.ProductForm
import app.features.productcreation.ui.base.ProductViewState
import app.features.productcreation.ui.base.ProductEvents


@Preview(showSystemUi = true)
@Composable
fun ProductEditionScreen(
    viewModel: ProductEditionViewModel = remember { ProductEditionViewModel()},
    onGoBack : () -> Unit = {}
){
    viewModel.getReady(onGoBack)
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
        topBar = @Composable { Appbar(
            titleText = stringResource(R.string.product_edition_title_appbar),
            onGoBack = productEvents.onLeavePage
        ) }
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
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProductForm(
            productState,
            productEvents,
            stringResource(R.string.accept_button_label),
            productEvents.onAcceptProduct
        )
    }
}
