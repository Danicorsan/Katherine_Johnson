package app.features.productcreation.ui.base.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.domain.invoicing.category.Category
import app.features.productcreation.R
import app.features.productcreation.ui.base.Specification

@Composable
fun ExposedDropDownMenuForCategory(
    categories : Iterable<Category>,
    categorySelected: Category?,
    onNewCategorySelected: (Category) -> Unit
){
    val noItemSelectedMessage = stringResource(R.string.no_selected_option)

    ExposedDropDownMenuTemplate(
        modifier = Modifier.fillMaxWidth(Specification.editTextMaxWithdFraction),
        showSelectedValueInTextField = {
            categorySelected?.name ?: noItemSelectedMessage
        },
        elementsList = categories,
        onNewItemSelected = onNewCategorySelected,
        howShowItem = {
            Text(it.name)
        },
        label = stringResource(R.string.category_label)
    )
}

@Composable
fun ExposedDropDownMenuForSection(
    sections : Iterable<String>,
    sectionSelected: String?,
    onNewSectionSelected: (String) -> Unit
){
    val noItemSelectedMessage = stringResource(R.string.no_selected_option)

    ExposedDropDownMenuTemplate(
        modifier = Modifier.fillMaxWidth(Specification.editTextMaxWithdFraction),
        showSelectedValueInTextField = {
            sectionSelected ?: noItemSelectedMessage
        },
        elementsList = sections,
        onNewItemSelected = onNewSectionSelected,
        howShowItem = {
            Text(it)
        },
        label = stringResource(R.string.section_label)
    )
}


