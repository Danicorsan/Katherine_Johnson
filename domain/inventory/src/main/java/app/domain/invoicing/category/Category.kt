package app.domain.invoicing.category

import java.util.Date

/**
 * Category
 *
 * @property id
 * @property name
 * @property shortName
 * @property description
 * @property image
 * @property createdAt
 * @property typeCategory
 * @property fungible
 * @constructor Create empty Category
 */
data class Category(
    val id: Int,
    val name: String,
    val shortName: String,
    val description: String,
    val image: String?,
    val createdAt: Date,
    val typeCategory: TypeCategory = TypeCategory.BASICOS,
    val fungible: Boolean
)