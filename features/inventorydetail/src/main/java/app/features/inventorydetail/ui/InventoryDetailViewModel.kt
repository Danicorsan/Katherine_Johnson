package app.features.inventorydetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.InventoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel para gestionar el estado del inventario
open class InventoryDetailViewModel(
    private val inventoryRepository: InventoryRepository
) : ViewModel() {

    val _uiState = MutableStateFlow(InventoryDetailState())
    val uiState: StateFlow<InventoryDetailState> get() = _uiState

    // Cargar los detalles del inventario
    open fun loadInventoryDetails(inventoryId: Int) {
        // Evitar recargar si ya está cargado el inventario con el mismo ID
        if (_uiState.value.inventory?.id == inventoryId) return

        // Marcar que estamos cargando el inventario (estado inicial)
        _uiState.value = _uiState.value.copy(inventory = null, items = emptyList())

        viewModelScope.launch {
            // Intentamos cargar el inventario desde el repositorio
            val inventory = inventoryRepository.findInventoryById(inventoryId)

            if (inventory != null) {
                // Si se encuentra el inventario, actualizamos el estado
                _uiState.value = InventoryDetailState(
                    inventory = inventory,
                    items = inventory.items
                )
            } else {
                // Si no se encuentra el inventario, podemos manejar el caso de error o vacío
                _uiState.value = InventoryDetailState(
                    inventory = null,
                    items = emptyList()
                )
                // Aquí también podrías añadir algún manejo de error (mensaje de no encontrado, etc.)
            }
        }
    }
}
