package app.domain.invoicing.inventory

import app.domain.invoicing.product.Product
import java.util.Date

data class Inventory(
    val id: Int,
    val name: String,
    val description: String,
    val items: List<Product>,
    val createdAt: Date,
    val updatedAt: Date,
)
