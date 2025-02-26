package app.features.productcreation.ui.base.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.base.ui.composables.ExposedDropDownMenuTemplate
import app.domain.invoicing.category.Category
import app.domain.invoicing.section.Section
import app.features.productcreation.R
import app.features.productcreation.ui.base.Specification

/**
 * Una implementación del elemento [ExposedDropDownMenuTemplate]
 * para escoger categorias.
 *
 * @param categories La lista de [Category] ha escoger.
 *
 * @param categorySelected La categoria actualmente seleccionada,
 * si el parametro es nulo, se mostraŕa un "No seleccionado".
 *
 * @param onNewCategorySelected El evento ha lanzar cuando
 * el usuario escoge una categoria del menú. Pasando
 * por parametro la categoría escogida.
 * @receiver
 */
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

/**
 * Una implementación del elemento [ExposedDropDownMenuTemplate]
 * para escoger secciones.
 *
 * @param sections La lista de secciones (representados como [String]) ha escoger.
 *
 * @param sectionSelected La sección actualmente seleccionada,
 *  si el parametro es nulo, se mostraŕa un "No seleccionado".
 *
 * @param onNewSectionSelected El evento ha lanzar cuando
 *  el usuario escoge una sección del menú. Pasando
 *  por parametro la seccion escogida.
 * @receiver
 */
@Composable
fun ExposedDropDownMenuForSection(
    sections : Iterable<Section>,
    sectionSelected: Section?,
    onNewSectionSelected: (Section) -> Unit
){
    val noItemSelectedMessage = stringResource(R.string.no_selected_option)

    ExposedDropDownMenuTemplate(
        modifier = Modifier.fillMaxWidth(Specification.EDITTEXTMAXWIDTHFRACTION),
        showSelectedValueInTextField = {
            sectionSelected?.name ?: noItemSelectedMessage
        },
        elementList = sections,
        onNewItemSelected = onNewSectionSelected,
        howShowEachItemInMenu = {
            Text(it.name)
        },
        label = stringResource(R.string.section_label) + Specification.OBLIGATORYFIELDSMARK
    )
}


