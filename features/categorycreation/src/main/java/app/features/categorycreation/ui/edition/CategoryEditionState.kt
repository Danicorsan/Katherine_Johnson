package app.features.categorycreation.ui.edition

import app.domain.invoicing.category.Category
import app.domain.invoicing.category.TypeCategory

/**
 * Category edition state
 *
 * @property name
 * @property shortName
 * @property description
 * @property typeCategory
 * @property isNameError
 * @property isShortNameError
 * @property isDescriptionError
 * @property isError
 * @property showDialog
 * @property category
 * @property notFoundError
 * @property isLoading
 * @constructor Create empty Category edition state
 */
data class CategoryEditionState(
    val name: String = "",
    val shortName: String = "",
    val description: String = "",
    val typeCategory: TypeCategory = TypeCategory.BASICOS,
    val isNameError: Boolean = false,
    val isShortNameError: Boolean = false,
    val isDescriptionError: Boolean = false,
    val isError: Boolean = false,
    val showDialog: Boolean = false,
    val category: Category? = null,
    val notFoundError: Boolean = false,
    val isLoading: Boolean = false,
) {
    val itsAllOk: Boolean
        get() = !isNameError && !isShortNameError && !isDescriptionError
}
