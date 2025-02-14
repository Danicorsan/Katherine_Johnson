package app.features.inventorycreation.ui.creation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CreateInventoryViewModel @Inject constructor(
    private val repository: InventoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateInventoryState(
        inventoryId = 0,
        inventoryName = "",
        inventoryDescription = "",
        isCreateButtonEnabled = false,
        isLoading = false,
        errorMessage = null
    ))
    val uiState: StateFlow<CreateInventoryState> get() = _uiState

    fun onInventoryNameChange(newName: String) {
        _uiState.value = _uiState.value.copy(
            inventoryName = newName,
            isCreateButtonEnabled = newName.isNotBlank() && _uiState.value.inventoryDescription.isNotBlank()
        )
    }

    fun onInventoryDescriptionChange(newDescription: String) {
        _uiState.value = _uiState.value.copy(
            inventoryDescription = newDescription,
            isCreateButtonEnabled = _uiState.value.inventoryName.isNotBlank() && newDescription.isNotBlank()
        )
    }

    fun createInventory(onInventoryCreated: (Boolean) -> Unit) {
        if (_uiState.value.isCreateButtonEnabled) {
            // Validación básica de datos
            if (_uiState.value.inventoryName.isEmpty() || _uiState.value.inventoryDescription.isEmpty()) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Por favor, complete todos los campos"
                )
                return
            }

            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            viewModelScope.launch {
                try {
                    val newInventory = Inventory(
                        id = 0,
                        name = _uiState.value.inventoryName,
                        description = _uiState.value.inventoryDescription,
                        items = emptyList(),
                        createdAt = Date(),
                        updatedAt = Date()
                    )

                    val success = repository.addInventory(newInventory)

                    // Actualizar el estado y llamar al callback
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    onInventoryCreated(success)
                } catch (e: Exception) {
                    // Manejo más específico de excepciones si es necesario
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Error al guardar el inventario: ${e.message}"
                    )
                    onInventoryCreated(false) // Notificar que la operación falló
                }
            }
        }
    }

}