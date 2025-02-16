package app.features.inventorylist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class InventoryListViewModel(
    private val repository: InventoryRepository
) : ViewModel() {

    private val _inventories = mutableListOf<Inventory>()
    private val _uiState = mutableStateOf(InventoryListState(
        success = _inventories,
        loading = false,
        error = null
    ))
    val uiState: InventoryListState get() = _uiState.value
    private var state: InventoryListState by mutableStateOf(InventoryListState(
        success = _inventories,
        loading = false,
        error = null
    ))

    init {
        loadInventoriesWithDelay()
    }

    private fun loadInventoriesWithDelay() {
        viewModelScope.launch {
            _uiState.value = uiState.copy(loading = true)

            delay(1500)

            loadInventories()

            _uiState.value = uiState.copy(loading = false)
        }
    }

    private fun loadInventories() {
        viewModelScope.launch {
            _inventories.clear()
            _inventories.addAll(repository.getAllInventories())
            _uiState.value = uiState.copy(success = _inventories, loading = false)
        }
    }

    fun deleteInventory(inventory: Inventory) {
        viewModelScope.launch {
            _uiState.value = uiState.copy(loading = true)
            val success = repository.deleteInventory(inventory.id)
            if (success) {
                state.loading = true
                delay(2000)
                state.loading = false
                loadInventories()
            } else {
                _uiState.value = uiState.copy(error = "Error al eliminar el inventario", loading = false)
            }
        }
    }
}
