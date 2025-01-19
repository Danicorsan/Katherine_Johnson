package app.features.inventorydetail.ui

import app.domain.invoicing.inventory.Inventory

data class InventoryDetailState(
    val inventory: Inventory? = null,
    val items: List<Any> = emptyList()
)