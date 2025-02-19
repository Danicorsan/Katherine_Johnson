package app.features.inventorylist.ui

import app.domain.invoicing.inventory.Inventory

data class InventoryListState(
    val success: List<Inventory> = emptyList(),
    var loading: Boolean,
    val error: String?,
    val noData: Boolean
)
