package app.features.productcreation.ui.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.base.ui.composables.MediumSpace
import app.base.ui.composables.NormalButton
import app.features.productcreation.R

@Composable
fun ProductForm(
    ProductViewState: ProductViewState,
    events : ProductEvents,
    dropDownItems : DropDownItems,
    textButton : String,
    buttonPressed : () -> Unit = {}
){
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OneLineEditText(
            text = ProductViewState.name,
            change = events.onNameChange,
            tag = stringResource(R.string.nombre_etiqueta)
        )

        OneLineEditText(
            text = ProductViewState.shortName,
            change = events.onNameChange,
            tag = stringResource(R.string.nombre_corto_etiqueta)
        )

        OneLineEditText(
            text = ProductViewState.code,
            change = events.onNameChange,
            tag = stringResource(R.string.codigo_etiqueta)
        )

        OneLineEditText(
            text = ProductViewState.serialNumber,
            change = events.onNameChange,
            tag = stringResource(R.string.numero_serie_etiqueta)
        )

        OneLineEditText(
            text = ProductViewState.modelCode,
            change = events.onNameChange,
            tag = stringResource(R.string.codigo_modelo_etiqueta)
        )

        OneLineEditText(
            text = ProductViewState.productType,
            change = events.onNameChange,
            tag = stringResource(R.string.tipo_producto_etiqueta)
        )

        DropDownMenuTemplate(
            expanded = dropDownItems.categoryExpanded ,
            onDismiss = {},
            elementsList = dropDownItems.categories,
            touchedItem = {}
        ) {
            Text(it)
        }

        OneLineEditText(
            text = ProductViewState.price,
            change = events.onNameChange,
            tag = stringResource(R.string.precio_etiqueta)
        )

        MultipleLineEditText(
            text = ProductViewState.description,
            change = events.onNameChange,
            tag = stringResource(R.string.descripcion_etiqueta)
        )
        MediumSpace()
        NormalButton(
            text = textButton,
            onClick = buttonPressed
        )
    }
}