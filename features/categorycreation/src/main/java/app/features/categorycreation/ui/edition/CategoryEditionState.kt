package app.features.categorycreation.ui.edition

import app.domain.invoicing.category.Category
import app.domain.invoicing.category.TypeCategory
import java.util.Date

data class CategoryEditionState(
    val category: Category? = null,
    val name: String = category?.name ?: "",
    val description: String = category?.description ?: "",
    val shortName: String = category?.shortName ?: "",
    val image: String? = category?.image,
    val createdAt: Date = category?.createdAt ?: Date(),
    val typeCategory: TypeCategory = category?.typeCategory ?: TypeCategory.BASICOS,
    val fungible: Boolean = category?.fungible ?: false,
    val isError: Boolean = false,
    val isNameError: Boolean = false,
    val isDescriptionError: Boolean = false,
    val isShortNameError: Boolean = false,
    val shortNameErrorMessage: String? = null,
    val showDialog: Boolean = false,
    val notFoundError: Boolean = false
)
