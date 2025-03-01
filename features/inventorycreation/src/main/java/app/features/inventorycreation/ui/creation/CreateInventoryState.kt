package app.features.inventorycreation.ui.creation

import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.inventory.InventoryType

data class CreateInventoryState(
    val inventoryId: Int,
    val inventoryName: String = "",
    val inventoryIcon: InventoryIcon = InventoryIcon.NONE,
    val inventoryDescription: String = "",
    val inventoryItemsCount: Int?,
    val inventoryType: InventoryType,
    val isCreateButtonEnabled: Boolean = false,
    var loading: Boolean = false,
    val error: String? = null,
    val success: List<Inventory> = emptyList(),
    val nameErrorMessage: String? = null
)