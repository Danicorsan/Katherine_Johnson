package app.features.inventorycreation.ui.creation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class CreateInventoryViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CreateInventoryState())
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

    fun createInventory(onInventoryCreated: (Inventory) -> Unit) {
        if (_uiState.value.isCreateButtonEnabled) {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            // Llamar a la corutina para simular el delay de 3 segundos
            viewModelScope.launch {
                delay(3000) // Esperar 3 segundos

                // Crear el inventario después del retraso
                val newInventory = Inventory(
                    id = generateInventoryId(),
                    name = _uiState.value.inventoryName,
                    description = _uiState.value.inventoryDescription,
                    items = emptyList(),
                    createdAt = Date(),
                    updatedAt = Date()
                )

                // Actualizar el estado y llamar al callback
                _uiState.value = _uiState.value.copy(isLoading = false)
                onInventoryCreated(newInventory) // Pasar el inventario creado al callback
            }
        }
    }

    private fun generateInventoryId(): Int {
        return (System.currentTimeMillis() % Int.MAX_VALUE).toInt()
    }
}
