package app.features.inventorylist.ui

import app.domain.invoicing.inventory.Inventory

data class InventoryListState(
    val inventories: List<Inventory> = emptyList(),
    var isLoading: Boolean,
    val errorMessage: String?
)
