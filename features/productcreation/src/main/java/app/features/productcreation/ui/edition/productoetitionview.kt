package app.features.productcreation.ui.edition

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseAlertDialog
import app.features.productcreation.R
import app.features.productcreation.ui.base.components.ProductForm
import app.features.productcreation.ui.base.ProductViewState
import app.features.productcreation.ui.base.ProductEvents
import app.features.productcreation.ui.base.components.ProductCreationAppbar
import app.features.productcreation.ui.base.components.ProductCreationFloatingActionButton


@Composable
fun ProductEditionScreen(
    viewModel: ProductEditionViewModel,
    productToEditId : Int,
    onGoBackNav : () -> Unit
){
    LaunchedEffect(Unit) {
        viewModel.stablishNavigationEvent(onGoBackNav)
        viewModel.loadScreenData(productToEditId)
    }
    ProductEditionHost(
        productEditionState = viewModel.productViewState,
        productEvents = ProductEvents.build(viewModel)
    )
}

@Composable
private fun ProductEditionHost(
    productEditionState: ProductViewState,
    productEvents : ProductEvents
){
    Scaffold(
        topBar = { ProductCreationAppbar(
            titleText = stringResource(R.string.title_appbar_product_edition),
            onLeavePage = productEvents.onLeavePage) },
        floatingActionButton = {
            ProductCreationFloatingActionButton(productEvents.onAcceptProduct)
        }
    ) { contentPadding ->
        when {
            productEditionState.isLoading -> LoadingUi()
            productEditionState.cantRegisterProduct -> BaseAlertDialog(
                title = stringResource(R.string.cant_register_alert_dialog_title),
                text = stringResource(R.string.cant_update_alert_dialog_message),
                confirmText = stringResource(R.string.confirm_button_alert_dialog),
                onConfirm = productEvents.onDismissCantRegisterProductAlertDialog,
                onDismiss = productEvents.onDismissCantRegisterProductAlertDialog
            )
            productEditionState.errorDataState.emptyFields -> BaseAlertDialog(
                title = stringResource(R.string.empty_fields_alert_dialog_title),
                text = stringResource(R.string.empty_fields_alert_dialog_message),
                confirmText = stringResource(R.string.confirm_button_alert_dialog),
                onConfirm = productEvents.onDismissEmptyFieldsAlertDialog,
                onDismiss = productEvents.onDismissEmptyFieldsAlertDialog
            )
            productEditionState.productRegisterSuccessful -> BaseAlertDialog(
                title = stringResource(R.string.product_registered_successful_alert_dialog_title),
                text = stringResource(R.string.product_updated_successful_alert_dialog_message),
                confirmText = stringResource(R.string.confirm_button_alert_dialog),
                onConfirm = productEvents.onDismissProductHasBeenRegisteredAlertDialog,
                onDismiss = productEvents.onDismissProductHasBeenRegisteredAlertDialog
            )
            else -> ProductEditionContent(
                modifier = Modifier.padding(contentPadding),
                baseProductState = productEditionState,
                productEvents = productEvents
            )
        }
    }
}

@Composable
private fun ProductEditionContent(
    modifier: Modifier,
    baseProductState: ProductViewState,
    productEvents : ProductEvents
    ){
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ProductForm(
            baseProductState,
            productEvents
        )
    }
}
