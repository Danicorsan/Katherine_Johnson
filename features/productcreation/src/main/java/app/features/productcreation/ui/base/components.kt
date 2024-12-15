package app.features.productcreation.ui.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import app.base.ui.composables.NormalButton
import app.base.ui.composables.Separations
import app.base.ui.composables.SmallSpace
import app.features.productcreation.R

@Composable
fun ProductForm(
    productViewState: ProductViewState,
    productEvents : ProductEvents,
    dropDownItemsState: DropDownItemsState,
    dropDownitemsEvents : DropDownItemsEvents,
    textButton : String,
    buttonPressed : () -> Unit = {}
){

    Box(
        modifier = Modifier
            .fillMaxHeight(80/100f)
            .padding(Separations.Medium)
    ){
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OneLineEditText(
                text = productViewState.name,
                change = productEvents.onNameChange,
                tag = stringResource(R.string.nombre_etiqueta)
            )

            OneLineEditText(
                text = productViewState.shortName,
                change = productEvents.onShortNameChange,
                tag = stringResource(R.string.nombre_corto_etiqueta)
            )

            OneLineEditText(
                text = productViewState.code,
                change = productEvents.onCodeChange,
                tag = stringResource(R.string.codigo_etiqueta)
            )

            OneLineEditText(
                text = productViewState.serialNumber,
                change = productEvents.onSerialNumberChange,
                tag = stringResource(R.string.numero_serie_etiqueta)
            )

            OneLineEditText(
                text = productViewState.modelCode,
                change = productEvents.onModelCodeChange,
                tag = stringResource(R.string.codigo_modelo_etiqueta)
            )

            OneLineEditText(
                text = productViewState.productType,
                change = productEvents.onProductTypeChange,
                tag = stringResource(R.string.tipo_producto_etiqueta)
            )

            SmallSpace()
            DropDownMenuForCategory(
                dropDownItemsState.categoriesList,
                dropDownItemsState.selectedCategory,
                dropDownitemsEvents.onNewCategorySelected
            )

            SmallSpace()
            DropDownMenuForSection(
                dropDownItemsState.sectionList,
                dropDownItemsState.selectedSection,
                dropDownitemsEvents.onNewSectionSelected
            )

            OneLineEditText(
                text = productViewState.price,
                change = productEvents.onPriceChange,
                tag = stringResource(R.string.precio_etiqueta),
                opcionesTeclado = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            MultipleLineEditText(
                text = productViewState.description,
                change = productEvents.onDescriptionChange,
                tag = stringResource(R.string.descripcion_etiqueta)
            )

            SmallSpace()
            DatePickerDocked(
                selectedDateText = productViewState.adquisitionDateRepresentation ?: stringResource(R.string.no_selected_option),
                label = stringResource(R.string.fecha_adquisicion_etiqueta),
                onNewDateSelected = productEvents.onNewAcquisitionDateSelected
            )

            SmallSpace()
            DatePickerDocked(
                selectedDateText = productViewState.discontinuationDateRepresentation ?: stringResource(R.string.no_selected_option),
                label = stringResource(R.string.fecha_modificacion_etiqueta),
                onNewDateSelected = productEvents.onNewDiscontinuationDateSelected
            )
        }
    }
    NormalButton(
        text = textButton,
        onClick = buttonPressed
    )
}

