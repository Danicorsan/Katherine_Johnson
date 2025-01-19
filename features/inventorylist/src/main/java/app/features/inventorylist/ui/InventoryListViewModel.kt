package app.features.inventorylist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.InventoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class InventoryListViewModel(
    private val inventoryRepository: InventoryRepository = InventoryRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(InventoryListState())
    open val uiState: StateFlow<InventoryListState> = _uiState

    init {
        loadInventories()
    }

    private fun loadInventories() {
        // Simula la carga de inventarios desde el repositorio
        viewModelScope.launch {
            inventoryRepository.inventories.collect { inventories ->
                _uiState.value = InventoryListState(inventories = inventories)
            }
        }
    }
}
