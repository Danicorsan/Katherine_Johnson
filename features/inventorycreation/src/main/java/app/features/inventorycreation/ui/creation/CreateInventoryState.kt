package app.features.inventorycreation.ui.creation

import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.inventory.InventoryState
import app.domain.invoicing.inventory.InventoryType
import java.time.LocalDateTime

data class CreateInventoryState(
    val inventoryId: Int,
    val inventoryName: String = "",
    val inventoryIcon: InventoryIcon = InventoryIcon.NONE,
    val inventoryDescription: String = "",
    val inventoryItemsCount: Int?,
    val inventoryType: InventoryType = InventoryType.PERMANENT,
    val inventoryShortName: String = "",
    val inventoryState: InventoryState = InventoryState.IN_PROGRESS,
    val inventoryCode: String = "",
    val isCreateButtonEnabled: Boolean = false,
    var loading: Boolean = false,
    val error: String? = null,
    val success: List<Inventory> = emptyList(),
    val nameErrorMessage: String? = null,
    val descriptionErrorMessage: String? = null,
    val shortNameErrorMessage: String? = null,

    val inventoryHistoryDateTime: LocalDateTime?,
    val inventoryInProgressDateTime: LocalDateTime?,
    val inventoryActiveDateTime: LocalDateTime?
)