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
import androidx.compose.ui.text.style.TextAlign
import app.base.ui.composables.MediumSpace
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
import app.features.productcreation.ui.base.composables.ExposedDropDownMenuForDependencies
import app.features.productcreation.ui.base.composables.ProductImagePicker

/**
 * Permite crear un formularios para un producto que puede ser usado
 * tanto para la edición como creación de los productos.
 *
 * @param productViewState El estado de la vista ya que contiene los valores
 * de cada campo del formulario
 *
 * @param productEvents Los eventos necesarios para la actualización correcta
 * de la vista.
 */
@Composable
fun ProductForm(
    productViewState: ProductViewState,
    productEvents: ProductEvents
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = Specification.TEXTFIELDSCOLUMNBOTTOMPADDING),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MessageOfObligatoryTextFields()

        MediumSpace()
        ProductImagePicker(onUriImageSelected = productEvents.onNewImageSelected)
        MediumSpace()

        OneLineEditText(
            text = productViewState.inputDataState.name,
            onValueChange = productEvents.onNameChange,
            label = stringResource(R.string.name_label),
            obligatoryField = true
        )

        OneLineEditText(
            text = productViewState.inputDataState.shortName,
            onValueChange = productEvents.onShortNameChange,
            label = stringResource(R.string.short_name_label),
            error = productViewState.errorDataState.shortNameError,
            obligatoryField = true
        )

        OneLineEditText(
            text = productViewState.inputDataState.code,
            onValueChange = productEvents.onCodeChange,
            label = stringResource(R.string.code_label),
            obligatoryField = true
        )

        OneLineEditText(
            text = productViewState.inputDataState.serieNumber,
            onValueChange = productEvents.onSerialNumberChange,
            label = stringResource(R.string.serial_number_label),
            obligatoryField = true
        )

        OneLineEditText(
            text = productViewState.inputDataState.modelCode,
            onValueChange = productEvents.onModelCodeChange,
            label = stringResource(R.string.model_code_label),
            obligatoryField = true
        )

        OneLineEditText(
            text = productViewState.inputDataState.productType,
            onValueChange = productEvents.onProductTypeChange,
            label = stringResource(R.string.product_type_label),
            obligatoryField = true
        )

        SmallSpace()
        ExposedDropDownMenuForCategory(
            categories = productViewState.categoriesList,
            categorySelected = productViewState.inputDataState.selectedCategory,
            onNewCategorySelected = productEvents.onNewCategorySelected,
        )

        SmallSpace()
        ExposedDropDownMenuForDependencies(
            dependencies = productViewState.dependenciesList,
            dependencySelected = productViewState.inputDataState.selectedDependency,
            onNewDependencySelected = productEvents.onNewDependencySelected
        )

        SmallSpace()
        ExposedDropDownMenuForSection(
            sections = productViewState.sectionsList,
            sectionSelected = productViewState.inputDataState.selectedSection,
            onNewSectionSelected = productEvents.onNewSectionSelected
        )

        OneLineEditText(
            text = productViewState.inputDataState.price,
            onValueChange = productEvents.onPriceChange,
            label = stringResource(R.string.price_label),
            error = productViewState.errorDataState.priceError,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            obligatoryField = true
        )

        OneLineEditText(
            text = productViewState.inputDataState.stock,
            onValueChange = productEvents.onStockChange,
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
            onValueChange = productEvents.onDescriptionChange,
            label = stringResource(R.string.description_label),
            obligatoryField = true
        )

        MultipleLineEditText(
            text = productViewState.inputDataState.notes,
            onValueChange = productEvents.onNotesChanged,
            label = stringResource(R.string.notes_label)
        )

        OneLineEditText(
            text = productViewState.inputDataState.tags,
            onValueChange = productEvents.onTagsChanged,
            label = stringResource(R.string.tags_label)
        )
    }
}

@Composable
private fun MessageOfObligatoryTextFields() {
    Text(stringResource(R.string.message_for_obligatory_fields, Specification.OBLIGATORYFIELDSMARK),
    textAlign = TextAlign.Center
    )
}