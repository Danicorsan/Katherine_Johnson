package app.features.inventorycreation.ui.edition

import app.domain.invoicing.inventory.InventoryIcon

data class EditInventoryState(
    val inventoryId: Int = 0,
    val inventoryName: String = "",
    val inventoryDescription: String = "",
    val inventoryIcon: InventoryIcon,
    val isSaveButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
