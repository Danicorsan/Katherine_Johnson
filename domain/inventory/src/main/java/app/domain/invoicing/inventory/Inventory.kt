package app.domain.invoicing.inventory

data class Inventory(
    val id: Int,
    val name: String,
    val description: String,
    val items: List<Any>
)
