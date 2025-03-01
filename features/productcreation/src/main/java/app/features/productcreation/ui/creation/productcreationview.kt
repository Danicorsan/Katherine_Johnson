package app.features.productcreation.ui.creation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseAlertDialog
import app.features.productcreation.R
import app.features.productcreation.ui.base.components.ProductForm
import app.features.productcreation.ui.base.ProductViewState
import app.features.productcreation.ui.base.ProductEvents
import app.features.productcreation.ui.base.components.ProductCreationAppbar
import app.features.productcreation.ui.base.components.ProductCreationFloatingActionButton


@Preview(showBackground = true)
@Composable
fun ProductCreationScreen(
    viewModel: ProductCreationViewModel = hiltViewModel<ProductCreationViewModel>() ,
    onGoBackNav : () -> Unit = {}
){
    LaunchedEffect(Unit) {
        viewModel.stablishNavigationEvent(onGoBackNav)
        viewModel.loadScreenData()
    }
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
        topBar = { ProductCreationAppbar(stringResource(R.string.title_appbar_product_creation) , productEvents.onLeavePage) },
        floatingActionButton = {
            ProductCreationFloatingActionButton(productEvents.onAcceptProduct)
        },
        snackbarHost = {
            SnackbarHost(hostState = productState.snackbarHostState)
        }
    ) { contentPadding ->
        when {
            productState.isLoading -> LoadingUi()
            productState.cantRegisterProduct -> BaseAlertDialog(
                title = stringResource(R.string.cant_register_alert_dialog_title),
                text = stringResource(R.string.cant_register_alert_dialog_message),
                confirmText = stringResource(R.string.confirm_button_alert_dialog),
                onConfirm = productEvents.onDismissCantRegisterProductAlertDialog,
                onDismiss = productEvents.onDismissCantRegisterProductAlertDialog
            )
            productState.errorDataState.emptyFields -> BaseAlertDialog(
                title = stringResource(R.string.empty_fields_alert_dialog_title),
                text = stringResource(R.string.empty_fields_alert_dialog_message),
                confirmText = stringResource(R.string.confirm_button_alert_dialog),
                onConfirm = productEvents.onDismissEmptyFieldsAlertDialog,
                onDismiss = productEvents.onDismissEmptyFieldsAlertDialog
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

