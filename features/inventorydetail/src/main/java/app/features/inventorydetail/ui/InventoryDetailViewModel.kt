package app.features.inventorydetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.InventoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InventoryDetailViewModel(
    private val inventoryRepository: InventoryRepository = InventoryRepository // Uso directo de la implementación
) : ViewModel() {

    // Estado que contiene la información del inventario
    private val _uiState = MutableStateFlow(InventoryDetailState())
    val uiState: StateFlow<InventoryDetailState> get() = _uiState

    // Función para cargar los detalles del inventario
    fun loadInventoryDetails(inventoryId: Int) {
        viewModelScope.launch {
            val inventory = inventoryRepository.getInventoryById(inventoryId) // Aquí se usa el repositorio
            if (inventory != null) {
                _uiState.value = _uiState.value.copy(
                    inventory = inventory,
                    items = inventory.items // Cargar los items asociados
                )
            }
        }
    }
}
