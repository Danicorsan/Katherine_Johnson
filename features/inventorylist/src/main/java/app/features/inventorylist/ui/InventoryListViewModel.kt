package app.features.inventorylist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import javax.inject.Inject

class InventoryListViewModel(
    private val repository: InventoryRepository
) : ViewModel() {

    private val _inventories = mutableListOf<Inventory>()

    private val _uiState = mutableStateOf(InventoryListState(
        inventories = _inventories,
        isLoading = false,
        errorMessage = null
    ))
    val uiState: InventoryListState get() = _uiState.value

    private var state: InventoryListState by mutableStateOf(InventoryListState(
        inventories = _inventories,
        isLoading = false,
        errorMessage = null
    ))

    init {
        loadInventoriesWithDelay()
    }

    private fun loadInventoriesWithDelay() {
        viewModelScope.launch {
            _uiState.value = uiState.copy(isLoading = true)

            delay(3000)

            loadInventories()

            _uiState.value = uiState.copy(isLoading = false)
        }
    }

    private fun loadInventories() {
        viewModelScope.launch {
            _inventories.clear()
            _inventories.addAll(repository.getAllInventories())
            _uiState.value = uiState.copy(inventories = _inventories, isLoading = false)
        }
    }

    fun deleteInventory(inventory: Inventory) {
        viewModelScope.launch {
            _uiState.value = uiState.copy(isLoading = true)
            val success = repository.deleteInventory(inventory.id)
            if (success) {
                state.isLoading = true
                delay(2000)
                state.isLoading = false
                loadInventories()
            } else {
                _uiState.value = uiState.copy(errorMessage = "Error al eliminar el inventario", isLoading = false)
            }
        }
    }

    fun addInventory(inventory: Inventory) {
        viewModelScope.launch {
            _uiState.value = uiState.copy(isLoading = true)
            val success = repository.addInventory(inventory)
            if (success) {
                _inventories.add(inventory)
                _uiState.value = uiState.copy(inventories = _inventories, isLoading = false)
            } else {
                _uiState.value = uiState.copy(errorMessage = "Error al agregar el inventario", isLoading = false)
            }
        }
    }
}
