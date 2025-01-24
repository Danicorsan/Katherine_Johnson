package app.features.inventorycreation.ui.creation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class CreateInventoryViewModel(
    private val inventoryRepository: InventoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateInventoryState())
    val uiState: StateFlow<CreateInventoryState> get() = _uiState

    // Lógica para cambiar el nombre del inventario
    fun onInventoryNameChange(newName: String) {
        _uiState.value = _uiState.value.copy(
            inventoryName = newName,
            isCreateButtonEnabled = newName.isNotBlank() && _uiState.value.inventoryDescription.isNotBlank()
        )
    }

    // Lógica para cambiar la descripción del inventario
    fun onInventoryDescriptionChange(newDescription: String) {
        _uiState.value = _uiState.value.copy(
            inventoryDescription = newDescription,
            isCreateButtonEnabled = _uiState.value.inventoryName.isNotBlank() && newDescription.isNotBlank()
        )
    }

    // Función para crear el inventario
    fun createInventory() {
        if (_uiState.value.isCreateButtonEnabled) {
            val newInventory = Inventory(
                id = generateInventoryId(),
                name = _uiState.value.inventoryName,
                description = _uiState.value.inventoryDescription,
                items = emptyList(),
                createdAt = Date() // Asumimos que al principio no hay items
            )

            // Cambiar el estado a 'cargando' mientras se procesa la creación
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            viewModelScope.launch {
                // Intentar agregar el inventario al repositorio
                val success = inventoryRepository.addInventory(newInventory)

                if (success) {
                    // Limpiar el estado de la UI
                    _uiState.value = CreateInventoryState() // Reinicia los campos
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Hubo un error al crear el inventario."
                    )
                }
            }
        }
    }

    // Generación de ID único para el inventario
    private fun generateInventoryId(): Int {
        return (System.currentTimeMillis() % Int.MAX_VALUE).toInt()
    }
}
