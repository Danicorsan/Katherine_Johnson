package app.features.inventorylist.ui

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import app.domain.invoicing.inventory.Inventory

data class InventoryListState(
    val success: List<Inventory> = emptyList(),
    var loading: Boolean,
    val error: String?,
    val noData: Boolean,
    val drawerState : DrawerState = DrawerState(initialValue = DrawerValue.Closed)
)
