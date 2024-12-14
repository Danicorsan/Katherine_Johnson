package app.features.productcreation.ui.edition

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.features.productcreation.R
import app.features.productcreation.ui.base.Appbar
import app.features.productcreation.ui.base.DropDownItems
import app.features.productcreation.ui.base.ProductForm
import app.features.productcreation.ui.base.ProductViewState
import app.features.productcreation.ui.base.ProductEvents

@Preview(showBackground = true)
@Composable
fun ProductEtitionScreen(viewModel: ProductEditionViewModel = ProductEditionViewModel()){
    ProductEditionContent(
        productState = viewModel.estadoProducto,
        events = ProductEvents(
            confirmationAboutProduct = viewModel::acceptProductEdition,
            leavePage = viewModel::leavePage,
            onNameChange = viewModel::nameChanged
        ),
        dropDownItems = viewModel.desplegable
    )
}

@Composable
private fun ProductEditionContent(
    productState: ProductViewState,
    events : ProductEvents,
    dropDownItems: DropDownItems
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Appbar(titleText = stringResource(R.string.titulo_edicion_producto_appbar))
        ProductForm(
            productState,
            events,
            dropDownItems,
            stringResource(R.string.boton_aceptar)
        )
    }
}
