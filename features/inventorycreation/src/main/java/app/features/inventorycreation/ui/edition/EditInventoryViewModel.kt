package app.features.inventorycreation.ui.edition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date

// ViewModel para editar inventarios
class EditInventoryViewModel(
    private val inventoryRepository: InventoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditInventoryState())
    val uiState: StateFlow<EditInventoryState> get() = _uiState

    // Cargar un inventario para su edición
    fun loadInventory(inventoryId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true) // Mostrar que estamos cargando

            // Usamos la función getInventoryById en lugar de findInventoryById
            val inventory = inventoryRepository.getInventoryById(inventoryId)

            if (inventory != null) {
                // Si el inventario es encontrado, actualizamos el estado
                _uiState.value = _uiState.value.copy(
                    inventoryId = inventory.id,
                    inventoryName = inventory.name,
                    inventoryDescription = inventory.description,
                    isLoading = false
                )
            } else {
                // Si no se encuentra el inventario, mostramos un mensaje de error
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Inventario no encontrado",
                    isLoading = false
                )
            }
        }
    }


    // Manejo del cambio en el nombre del inventario
    fun onInventoryNameChange(newName: String) {
        _uiState.value = _uiState.value.copy(
            inventoryName = newName,
            isSaveButtonEnabled = newName.isNotBlank() && _uiState.value.inventoryDescription.isNotBlank()
        )
    }

    // Manejo del cambio en la descripción del inventario
    fun onInventoryDescriptionChange(newDescription: String) {
        _uiState.value = _uiState.value.copy(
            inventoryDescription = newDescription,
            isSaveButtonEnabled = _uiState.value.inventoryName.isNotBlank() && newDescription.isNotBlank()
        )
    }

    // Guardar los cambios en el inventario
    fun saveChanges() {
        val updatedInventory = Inventory(
            id = _uiState.value.inventoryId,
            name = _uiState.value.inventoryName,
            description = _uiState.value.inventoryDescription,
            items = emptyList(),
            createdAt = Date() // Suponemos que los items no se modifican
        )

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true) // Indicamos que estamos guardando
            val success = inventoryRepository.updateInventory(updatedInventory)
            if (success) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = null
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Error al guardar el inventario"
                )
            }
        }
    }
}