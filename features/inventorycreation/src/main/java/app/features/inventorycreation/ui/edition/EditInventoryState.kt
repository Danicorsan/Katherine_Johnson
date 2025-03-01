package app.features.inventorycreation.ui.edition

import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.inventory.InventoryType

data class EditInventoryState(
    val inventoryId: Int = 0,
    val inventoryName: String = "",
    val inventoryDescription: String = "",
    val inventoryIcon: InventoryIcon = InventoryIcon.NONE,
    val inventoryItemsCount: Int = 0,
    val inventoryType: InventoryType = InventoryType.PERMANENT,
    val isSaveButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val nameErrorMessage: String? = null,
    val originalName: String = "",
    val originalDescription: String = "",
    val originalIcon: InventoryIcon = InventoryIcon.NONE,
    val originalType: InventoryType = InventoryType.PERMANENT,
    val noChangesMessage: String? = null
)
