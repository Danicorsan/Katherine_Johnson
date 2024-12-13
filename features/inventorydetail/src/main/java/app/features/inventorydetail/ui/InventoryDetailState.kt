package app.features.inventorydetail.ui

import app.domain.invoicing.inventory.Item

data class InventoryDetailState(
    val items: List<Item> = emptyList()
)
