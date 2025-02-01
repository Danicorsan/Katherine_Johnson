package app.features.productcreation.ui.creation

import androidx.compose.foundation.layout.Box
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
import app.features.productcreation.ui.base.components.AlertDialogOK
import app.features.productcreation.ui.base.components.ProductForm
import app.features.productcreation.ui.base.ProductViewState
import app.features.productcreation.ui.base.ProductEvents
import app.features.productcreation.ui.base.components.ProductCreationAppbar
import app.features.productcreation.ui.base.components.ProductCreationFloatingActionButton


@Preview(showBackground = true)
@Composable
fun ProductCreationScreen(
    viewModel: ProductCreationViewModel = remember{ ProductCreationViewModel() }
){
    ProductCreationHost(
        productState = viewModel.productViewState,
        productEvents = ProductEvents.build(viewModel)
    )
}

@Composable
fun ProductCreationHost(
    productState: ProductViewState,
    productEvents : ProductEvents
){
    Scaffold(
        topBar = { ProductCreationAppbar(stringResource(R.string.creation_product_title_appbar) , productEvents.onLeavePage) },
        floatingActionButton = {
            ProductCreationFloatingActionButton(productEvents.onAcceptProduct)
        }
    ) { contentPadding ->
        when {
            productState.isLoading -> LoadingUi()
            productState.cantRegisterProduct -> AlertDialogOK(
                title = stringResource(R.string.cant_register_alert_dialog_title),
                message = stringResource(R.string.cant_register_alert_dialog_message),
                onDismiss = productEvents.onDismissCantRegisterProductAlertDialog
            )
            productState.errorDataState.emptyFields -> AlertDialogOK(
                title = stringResource(R.string.empty_fields_alert_dialog_title),
                message = stringResource(R.string.empty_fields_alert_dialog_message),
                onDismiss = productEvents.onDismissEmptyFieldsAlertDialog
            )
            productState.productRegisterSuccessful -> AlertDialogOK(
                title = stringResource(R.string.product_registered_successful_alert_dialog_title),
                message = stringResource(R.string.product_registered_successful_alert_dialog_message),
                onDismiss = productEvents.onDismissProductHasBeenRegisteredAlertDialog
            )
            else -> ProductCreationContent(
                modifier = Modifier.padding(contentPadding),
                productState = productState,
                productEvents = productEvents
            )
        }
    }
}

@Composable
fun ProductCreationContent(
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

