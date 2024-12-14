package app.features.productcreation.ui.creation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun ProductCreationView(viewModel: ProductCreationViewModel = ProductCreationViewModel()){
    ProductCreationContent(
        productState = viewModel.productViewState,
        events = ProductEvents(
            confirmationAboutProduct = viewModel::createProduct,
            leavePage = viewModel::leavePage,
            onNameChange = viewModel::nameChanged
        ), dropDownItems = viewModel.dropDownItems
        )
}

@Composable
fun ProductCreationContent(
    productState: ProductViewState,
    events : ProductEvents,
    dropDownItems: DropDownItems){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Appbar(titleText = stringResource(R.string.titulo_creacion_producto_appbar))
        ProductForm(
            productState,
            events,
            dropDownItems,
            stringResource(R.string.boton_aceptar)
        )
    }

}

