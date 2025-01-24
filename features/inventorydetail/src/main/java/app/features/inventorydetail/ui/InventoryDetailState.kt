package app.features.inventorydetail.ui

import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.Item

data class InventoryDetailState(
    val inventory: Inventory? = null,
    val items: List<Item> = emptyList()
)