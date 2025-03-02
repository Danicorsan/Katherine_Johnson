package app.features.inventorydetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryDetailViewModel @Inject constructor(
    private val repository: InventoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(InventoryDetailState())
    val uiState: StateFlow<InventoryDetailState> get() = _uiState

    fun loadInventoryDetails(inventoryId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            try {
                val inventory = repository.getInventoryById(inventoryId)
                if (inventory != null) {
                    _uiState.value = _uiState.value.copy(
                        success = inventory,
                        loading = false,
                        error = null
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        error = "Inventario no encontrado",
                        loading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error al cargar los detalles del inventario: ${e.message}",
                    loading = false
                )
            }
        }
    }

    fun deleteInventory(inventory: Inventory) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            try {
                val success = repository.deleteInventory(inventory.id)
                if (success) {
                    _uiState.value = _uiState.value.copy(
                        success = null,
                        loading = false,
                        error = null
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        error = "Error al eliminar el inventario",
                        loading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error al eliminar el inventario: ${e.message}",
                    loading = false
                )
            }
        }
    }
}
