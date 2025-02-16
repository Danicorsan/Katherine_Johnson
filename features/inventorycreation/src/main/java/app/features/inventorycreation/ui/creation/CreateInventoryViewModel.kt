package app.features.inventorycreation.ui.creation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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
        errorMessage = null,
        inventoryIcon = InventoryIcon.NONE,
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
    fun onInventoryIconChange(newIcon: InventoryIcon) {
        _uiState.value = _uiState.value.copy(
            inventoryIcon = newIcon
        )
    }

    fun addInventory(inventory: Inventory) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val newId = repository.addInventory(inventory)
                if (newId > 0) {
                    val updatedInventories = _uiState.value.inventories + inventory.copy(id = newId)

                    _uiState.value = _uiState.value.copy(
                        inventories = updatedInventories,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = e.message ?: "Error desconocido al agregar el inventario",
                    isLoading = false
                )
            }
        }
    }


}