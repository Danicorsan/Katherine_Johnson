package app.features.inventorydetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.InventoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class InventoryDetailViewModel(
    private val inventoryRepository: InventoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(InventoryDetailState())
    val uiState: StateFlow<InventoryDetailState> get() = _uiState

    open fun loadInventoryDetails(inventoryId: Int) {
        if (_uiState.value.inventory?.id == inventoryId) return

        _uiState.value = _uiState.value.copy(inventory = null, items = emptyList())

        viewModelScope.launch {
            val inventory = inventoryRepository.findInventoryById(inventoryId)

            if (inventory != null) {
                _uiState.value = InventoryDetailState(
                    inventory = inventory,
                    items = inventory.items
                )
            } else {
                _uiState.value = InventoryDetailState(
                    inventory = null,
                    items = emptyList()
                )
            }
        }
    }
}
