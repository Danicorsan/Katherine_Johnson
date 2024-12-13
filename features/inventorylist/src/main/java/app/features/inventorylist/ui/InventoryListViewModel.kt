package app.features.inventorylist.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import app.domain.invoicing.inventory.Inventory

open class InventoryListViewModel : ViewModel() {

    val _uiState = MutableStateFlow(InventoryListState())
    val uiState: StateFlow<InventoryListState> = _uiState

    open fun loadInventoryList() {
        // Aquí pondríamos la lógica para cargar los inventarios desde una base de datos o una API
        val sampleItems = listOf(
            Inventory(1, "Camisa", "Camisa grande", 45, 15.99, emptyList()),
            Inventory(2, "Pantalón", "Pantalón de mezclilla", 30, 25.99, emptyList()),
            Inventory(3, "Zapatos", "Zapatos deportivos", 20, 35.99, emptyList())
        )

        // Actualizamos el estado con los inventarios simulados
        _uiState.value = InventoryListState(inventories = sampleItems)
    }
}
