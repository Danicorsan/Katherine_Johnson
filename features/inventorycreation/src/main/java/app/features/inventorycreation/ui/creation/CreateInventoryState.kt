package app.features.inventorycreation.ui.creation

import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon

data class CreateInventoryState(
    val inventoryId: Int,
    val inventoryName: String = "",
    val inventoryIcon: InventoryIcon = InventoryIcon.NONE,
    val inventoryDescription: String = "",
    val isCreateButtonEnabled: Boolean = false,
    var loading: Boolean = false,
    val error: String? = null,
    val success: List<Inventory> = emptyList(),
)