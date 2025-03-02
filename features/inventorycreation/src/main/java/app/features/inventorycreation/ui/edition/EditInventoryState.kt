package app.features.inventorycreation.ui.edition

import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.inventory.InventoryState
import app.domain.invoicing.inventory.InventoryType
import java.time.LocalDateTime

data class EditInventoryState(
    val inventoryId: Int = 0,
    val inventoryName: String = "",
    val inventoryDescription: String = "",
    val inventoryIcon: InventoryIcon = InventoryIcon.NONE,
    val inventoryItemsCount: Int = 0,
    val inventoryType: InventoryType = InventoryType.PERMANENT,
    val inventoryState: InventoryState = InventoryState.IN_PROGRESS,
    val inventoryShortName: String = "",
    val inventoryCode: String = "",
    val isSaveButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val nameErrorMessage: String? = null,
    val originalName: String = "",
    val originalDescription: String = "",
    val originalIcon: InventoryIcon = InventoryIcon.NONE,
    val originalType: InventoryType = InventoryType.PERMANENT,
    val originalShortName: String = "",
    val originalState: InventoryState = InventoryState.IN_PROGRESS,
    val originalCode: String = "",
    val noChangesMessage: String? = null,

    val inventoryInProgressDateAt: LocalDateTime?,
    val inventoryActiveDateAt: LocalDateTime?,
    val inventoryHistoryDateAt: LocalDateTime?,

    val  originalInProgressDateAt: LocalDateTime?,
    val  originalActiveDateAt: LocalDateTime?,
    val  originalHistoryDateAt: LocalDateTime?
)
