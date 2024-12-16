package app.features.productcreation.ui.creation

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
import app.features.productcreation.ui.base.DropDownItemsEvents
import app.features.productcreation.ui.base.DropDownItemsState
import app.features.productcreation.ui.base.ProductForm
import app.features.productcreation.ui.base.ProductViewState
import app.features.productcreation.ui.base.ProductEvents


@Preview(showBackground = true)
@Composable
fun ProductCreationScreen(
    viewModel: ProductCreationViewModel = remember{ ProductCreationViewModel() },
    onGoBack : () -> Unit = {}
){
    viewModel.getReady(onGoBack)
    ProductCreationHost(
        productState = viewModel.productViewState,
        productEvents = ProductEvents.build(viewModel),
        dropDownItemsState = viewModel.dropDownItemsState,
        dropDownItemsEvents = DropDownItemsEvents.build(viewModel)
    )
}

@Composable
fun ProductCreationHost(
    productState: ProductViewState,
    productEvents : ProductEvents,
    dropDownItemsState: DropDownItemsState,
    dropDownItemsEvents: DropDownItemsEvents
){
    Scaffold(
        topBar = @Composable { Appbar(
            titleText = stringResource(R.string.creation_product_title_appbar),
            onGoBack = productEvents.onLeavePage
        )}
    ) { contentPadding ->
        when {
            productState.isLoading -> LoadingUi()
            else -> ProductCreationContent(
                modifier = Modifier.padding(contentPadding),
                productState = productState,
                productEvents = productEvents,
                dropDownItemsState = dropDownItemsState,
                dropDownItemsEvents = dropDownItemsEvents
            )
        }
    }
}

@Composable
fun ProductCreationContent(
    modifier: Modifier,
    productState: ProductViewState,
    productEvents : ProductEvents,
    dropDownItemsState: DropDownItemsState,
    dropDownItemsEvents: DropDownItemsEvents
){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProductForm(
            productState,
            productEvents,
            dropDownItemsState,
            dropDownItemsEvents,
            stringResource(R.string.accept_button_label)
        )
    }

}

