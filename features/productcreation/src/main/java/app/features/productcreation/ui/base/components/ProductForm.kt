package app.features.productcreation.ui.base.components

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
import app.features.productcreation.ui.base.composables.DatePickerDocked
import app.features.productcreation.ui.base.composables.ExposedDropDownMenuForCategory
import app.features.productcreation.ui.base.composables.ExposedDropDownMenuForSection
import app.features.productcreation.ui.base.composables.MultipleLineEditText
import app.features.productcreation.ui.base.composables.OneLineEditText
import app.features.productcreation.ui.base.ProductEvents
import app.features.productcreation.ui.base.ProductViewState

@Composable
fun ProductForm(
    productViewState: ProductViewState,
    productEvents : ProductEvents,
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
                text = productViewState.inputDataState.name,
                change = productEvents.onNameChange,
                tag = stringResource(R.string.name_label)
            )

            OneLineEditText(
                text = productViewState.inputDataState.shortName,
                change = productEvents.onShortNameChange,
                tag = stringResource(R.string.short_name_label),
                error = productViewState.errorDataState.shortNameError
            )

            OneLineEditText(
                text = productViewState.inputDataState.code,
                change = productEvents.onCodeChange,
                tag = stringResource(R.string.code_label)
            )

            OneLineEditText(
                text = productViewState.inputDataState.serieNumber,
                change = productEvents.onSerialNumberChange,
                tag = stringResource(R.string.serial_number_label)
            )

            OneLineEditText(
                text = productViewState.inputDataState.modelCode,
                change = productEvents.onModelCodeChange,
                tag = stringResource(R.string.model_code_label)
            )

            OneLineEditText(
                text = productViewState.inputDataState.productType,
                change = productEvents.onProductTypeChange,
                tag = stringResource(R.string.product_type_label)
            )

            SmallSpace()
            ExposedDropDownMenuForCategory(
                productViewState.categoriesList,
                productViewState.inputDataState.selectedCategory,
                productEvents.onNewCategorySelected
            )

            SmallSpace()
            ExposedDropDownMenuForSection(
                productViewState.sectionsList,
                productViewState.inputDataState.selectedSection,
                productEvents.onNewSectionSelected
            )

            OneLineEditText(
                text = productViewState.inputDataState.price,
                change = productEvents.onPriceChange,
                tag = stringResource(R.string.price_label),
                error = productViewState.errorDataState.priceError,
                opcionesTeclado = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            OneLineEditText(
                text = productViewState.inputDataState.stock,
                change = productEvents.onStockChange,
                tag = stringResource(R.string.stock_label),
                error = productViewState.errorDataState.stockError,
                opcionesTeclado = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            SmallSpace()
            DatePickerDocked(
                selectedDateText = productViewState.inputDataState.adquisitionDateRepresentation ?: stringResource(R.string.no_selected_option),
                label = stringResource(R.string.acquisition_date_label),
                onNewDateSelected = productEvents.onNewAcquisitionDateSelected
            )

            SmallSpace()
            DatePickerDocked(
                selectedDateText = productViewState.inputDataState.discontinuationDateRepresentation ?: stringResource(R.string.no_selected_option),
                label = stringResource(R.string.discontinuation_date_label),
                onNewDateSelected = productEvents.onNewDiscontinuationDateSelected
            )

            MultipleLineEditText(
                text = productViewState.inputDataState.description,
                change = productEvents.onDescriptionChange,
                tag = stringResource(R.string.description_label)
            )

            MultipleLineEditText(
                text = productViewState.inputDataState.notes,
                change = productEvents.onNotesChanged,
                tag = stringResource(R.string.notes_label)
            )

            MultipleLineEditText(
                text = productViewState.inputDataState.tags,
                change = productEvents.onTagsChanged,
                tag = stringResource(R.string.tags_label)
            )
        }
    }
    NormalButton(
        text = textButton,
        onClick = buttonPressed
    )
}

