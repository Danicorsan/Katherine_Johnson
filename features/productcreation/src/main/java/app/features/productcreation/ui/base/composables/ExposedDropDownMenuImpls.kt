package app.features.productcreation.ui.base.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.base.ui.composables.ExposedDropDownMenuTemplate
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
        modifier = Modifier.fillMaxWidth(Specification.EDITTEXTMAXWIDTHFRACTION),
        showSelectedValueInTextField = {
            categorySelected?.name ?: noItemSelectedMessage
        },
        elementList = categories,
        onNewItemSelected = onNewCategorySelected,
        howShowEachItemInMenu = {
            Text(it.name)
        },
        label = stringResource(R.string.category_label) + Specification.OBLIGATORYFIELDSMARK
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
        modifier = Modifier.fillMaxWidth(Specification.EDITTEXTMAXWIDTHFRACTION),
        showSelectedValueInTextField = {
            sectionSelected ?: noItemSelectedMessage
        },
        elementList = sections,
        onNewItemSelected = onNewSectionSelected,
        howShowEachItemInMenu = {
            Text(it)
        },
        label = stringResource(R.string.section_label) + Specification.OBLIGATORYFIELDSMARK
    )
}


