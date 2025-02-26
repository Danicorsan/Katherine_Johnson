package app.features.inventorydetail.ui

import app.domain.invoicing.inventory.Inventory

data class InventoryDetailState(
    val success: Inventory? = null,
    val loading: Boolean = false,
    val error: String? = null
)
