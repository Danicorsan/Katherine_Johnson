package app.features.categorycreation.ui.base

import app.domain.invoicing.category.TypeCategory
import java.util.Date

data class CategoryCreationState(
    var name: String = "",
    var description: String = "",
    val shortName: String = "",
    val image: String? = null,
    val createdAt: Date = Date(),
    val typeCategory: TypeCategory = TypeCategory.BASICOS,
    val fungible: Boolean = false,
    var isError: Boolean = true,
    val isNameError: Boolean = false,
    val isDescriptionError: Boolean = false,
    val isShortNameError: Boolean = false,
    val shortNameErrorMessage: String? = null,
    val showDialog: Boolean = false
)
