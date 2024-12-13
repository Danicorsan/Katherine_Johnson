    package app.domain.invoicing.inventory

    data class Inventory(
        val id: Int,
        val name: String,
        val description: String,
        val quantity: Int,
        val price: Double,
        val items: List<Item>
    )

    data class Item(
        val id: Int,
        val name: String,
        val description: String
    )
