package app.features.inventorycreation.ui.creation

import app.domain.invoicing.inventory.Inventory

data class CreateInventoryState(
    val inventoryId: Int,
    val inventoryName: String = "",
    val inventoryDescription: String = "",
    val isCreateButtonEnabled: Boolean = false,
    var isLoading: Boolean = false,
    val errorMessage: String? = null,
    val inventories: List<Inventory> = emptyList(),
)