package app.features.productcreation.ui.edition

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.components.LoadingUi
import app.features.productcreation.R
import app.features.productcreation.ui.base.Appbar
import app.features.productcreation.ui.base.DropDownItemsEvents
import app.features.productcreation.ui.base.DropDownItemsState
import app.features.productcreation.ui.base.ProductForm
import app.features.productcreation.ui.base.ProductViewState
import app.features.productcreation.ui.base.ProductEvents


@Preview
@Composable
fun ProductEditionScreen(viewModel: ProductEditionViewModel = remember { ProductEditionViewModel()}){
    val productState = viewModel.productViewState
    ProductEditionHost(
        productState = productState,
        productEvents = ProductEvents.build(viewModel),
        dropDownItemsState = viewModel.dropDownItemsState,
        dropDownItemsEvents = DropDownItemsEvents.build(viewModel)
    )
}

@Composable
private fun ProductEditionHost(
    productState: ProductViewState,
    productEvents : ProductEvents,
    dropDownItemsState: DropDownItemsState,
    dropDownItemsEvents: DropDownItemsEvents
){
    when {
        productState.isLoading -> LoadingUi()
        else -> ProductEditionContent(
            productState = productState,
            productEvents = productEvents,
            dropDownItemsState = dropDownItemsState,
            dropDownItemsEvents = dropDownItemsEvents
        )
    }
}

@Composable
private fun ProductEditionContent(
    productState: ProductViewState,
    productEvents : ProductEvents,
    dropDownItemsState: DropDownItemsState,
    dropDownItemsEvents: DropDownItemsEvents
    ){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Appbar(titleText = stringResource(R.string.product_edition_title_appbar))
        ProductForm(
            productState,
            productEvents,
            dropDownItemsState,
            dropDownItemsEvents,
            stringResource(R.string.accept_button_label)
        )
    }
}
