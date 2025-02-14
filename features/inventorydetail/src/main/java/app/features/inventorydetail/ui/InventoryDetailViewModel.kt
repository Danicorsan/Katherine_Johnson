package app.features.inventorydetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryDetailViewModel @Inject constructor(
    private val repository: InventoryRepository// Uso directo de la implementación
) : ViewModel() {

    // Estado que contiene la información del inventario
    private val _uiState = MutableStateFlow(InventoryDetailState())
    val uiState: StateFlow<InventoryDetailState> get() = _uiState

    // Función para cargar los detalles del inventario
    fun loadInventoryDetails(inventoryId: Int) {
        viewModelScope.launch {
            val inventory = repository.getInventoryById(inventoryId) // Aquí se usa el repositorio
            if (inventory != null) {
                _uiState.value = _uiState.value.copy(
                    inventory = inventory,
                    items = inventory.items // Cargar los items asociados
                )
            }
        }
    }

    fun onInfoClick() {
        viewModelScope.launch {
            val currentState = _uiState.value
            _uiState.value = currentState.copy(
                showInfoDialog = true
            )
        }
    }

    fun onInfoDialogDismiss() {
        viewModelScope.launch {
            val currentState = _uiState.value
            _uiState.value = currentState.copy(
                showInfoDialog = false
            )
        }
    }
}
