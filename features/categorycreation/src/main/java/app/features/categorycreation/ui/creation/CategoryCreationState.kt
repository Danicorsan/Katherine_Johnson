package app.features.categorycreation.ui.creation

import android.net.Uri
import app.domain.invoicing.category.TypeCategory
import java.util.Date

/**
 * Category creation state
 *
 * @property name
 * @property description
 * @property shortName
 * @property image
 * @property createdAt
 * @property typeCategory
 * @property fungible
 * @property isError
 * @property isNameError
 * @property isDescriptionError
 * @property isShortNameError
 * @property shortNameErrorMessage
 * @property showDialog
 * @property isLoading
 * @constructor Create empty Category creation state
 */
data class CategoryCreationState(
    var name: String = "",
    var description: String = "",
    val shortName: String = "",
    val image: Uri? = null,
    val createdAt: Date = Date(),
    val typeCategory: TypeCategory = TypeCategory.BASICOS,
    val fungible: Boolean = false,
    var isError: Boolean = true,
    val isNameError: Boolean = false,
    val isDescriptionError: Boolean = false,
    val isShortNameError: Boolean = false,
    val shortNameErrorMessage: String? = null,
    val showDialog: Boolean = false,
    val isLoading: Boolean = false,
)
