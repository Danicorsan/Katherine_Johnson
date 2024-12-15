package app.domain.invoicing.category

import java.util.Date

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