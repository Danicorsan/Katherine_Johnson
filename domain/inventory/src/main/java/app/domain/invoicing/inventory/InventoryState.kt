package app.domain.invoicing.inventory

data class InventoryState(
    val inventories: List<Inventory> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)