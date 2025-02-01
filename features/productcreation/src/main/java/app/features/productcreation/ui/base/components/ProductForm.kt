package app.features.productcreation.ui.base.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import app.base.ui.composables.SmallSpace
import app.features.productcreation.R
import app.features.productcreation.ui.base.composables.DatePickerDocked
import app.features.productcreation.ui.base.composables.ExposedDropDownMenuForCategory
import app.features.productcreation.ui.base.composables.ExposedDropDownMenuForSection
import app.features.productcreation.ui.base.composables.MultipleLineEditText
import app.features.productcreation.ui.base.composables.OneLineEditText
import app.features.productcreation.ui.base.ProductEvents
import app.features.productcreation.ui.base.ProductViewState
import app.features.productcreation.ui.base.Specification

@Composable
fun ProductForm(
    productViewState: ProductViewState,
    productEvents: ProductEvents
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = Specification.TEXTFIELDSCOLUMNBOTTOMPADDING ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MessageOfObligatoryTextFields()

        OneLineEditText(
            text = productViewState.inputDataState.name,
            change = productEvents.onNameChange,
            label = stringResource(R.string.name_label),
            obligatoryField = true
        )

        OneLineEditText(
            text = productViewState.inputDataState.shortName,
            change = productEvents.onShortNameChange,
            label = stringResource(R.string.short_name_label),
            error = productViewState.errorDataState.shortNameError,
            obligatoryField = true
        )

        OneLineEditText(
            text = productViewState.inputDataState.code,
            change = productEvents.onCodeChange,
            label = stringResource(R.string.code_label),
            obligatoryField = true
        )

        OneLineEditText(
            text = productViewState.inputDataState.serieNumber,
            change = productEvents.onSerialNumberChange,
            label = stringResource(R.string.serial_number_label),
            obligatoryField = true
        )

        OneLineEditText(
            text = productViewState.inputDataState.modelCode,
            change = productEvents.onModelCodeChange,
            label = stringResource(R.string.model_code_label),
            obligatoryField = true
        )

        OneLineEditText(
            text = productViewState.inputDataState.productType,
            change = productEvents.onProductTypeChange,
            label = stringResource(R.string.product_type_label),
            obligatoryField = true
        )

        SmallSpace()
        ExposedDropDownMenuForCategory(
            productViewState.categoriesList,
            productViewState.inputDataState.selectedCategory,
            productEvents.onNewCategorySelected,

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
            label = stringResource(R.string.price_label),
            error = productViewState.errorDataState.priceError,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            obligatoryField = true
        )

        OneLineEditText(
            text = productViewState.inputDataState.stock,
            change = productEvents.onStockChange,
            label = stringResource(R.string.stock_label),
            error = productViewState.errorDataState.stockError,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            obligatoryField = true
        )

        SmallSpace()
        DatePickerDocked(
            selectedDateText = productViewState.inputDataState.adquisitionDateRepresentation
                ?: stringResource(R.string.no_selected_option),
            label = stringResource(R.string.acquisition_date_label) + Specification.OBLIGATORYFIELDSMARK,
            onNewDateSelected = productEvents.onNewAcquisitionDateSelected
        )

        SmallSpace()
        DatePickerDocked(
            selectedDateText = productViewState.inputDataState.discontinuationDateRepresentation
                ?: stringResource(R.string.no_selected_option),
            label = stringResource(R.string.discontinuation_date_label),
            onNewDateSelected = productEvents.onNewDiscontinuationDateSelected
        )

        MultipleLineEditText(
            text = productViewState.inputDataState.description,
            change = productEvents.onDescriptionChange,
            label = stringResource(R.string.description_label),
            obligatoryField = true
        )

        MultipleLineEditText(
            text = productViewState.inputDataState.notes,
            change = productEvents.onNotesChanged,
            label = stringResource(R.string.notes_label)
        )

        OneLineEditText(
            text = productViewState.inputDataState.tags,
            change = productEvents.onTagsChanged,
            label = stringResource(R.string.tags_label)
        )
    }
}

@Composable
private fun MessageOfObligatoryTextFields() {
    Text(stringResource(R.string.message_for_obligatory_fields, Specification.OBLIGATORYFIELDSMARK))
}