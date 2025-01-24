package app.domain.invoicing.inventory

import java.util.Date

data class Inventory(
    val id: Int,
    val name: String,
    val description: String,
    val items: List<Item>,
    val createdAt: Date
)
