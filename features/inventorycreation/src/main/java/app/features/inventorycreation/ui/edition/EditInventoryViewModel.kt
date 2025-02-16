package app.features.inventorycreation.ui.edition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class EditInventoryViewModel @Inject constructor(
    private val repository: InventoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditInventoryState(
        inventoryId = 0,
        inventoryName = "",
        inventoryDescription = "",
        inventoryIcon = InventoryIcon.NONE,
        isSaveButtonEnabled = false,
        isLoading = false,
        errorMessage = ""
    ))
    val uiState: StateFlow<EditInventoryState> get() = _uiState

    fun loadInventory(inventoryId: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val inventory = repository.getInventoryById(inventoryId)

            if (inventory != null) {
                _uiState.value = _uiState.value.copy(
                    inventoryId = inventory.id,
                    inventoryName = inventory.name,
                    inventoryDescription = inventory.description,
                    isLoading = false
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Inventario no encontrado",
                    isLoading = false
                )
            }
        }
    }

    fun onInventoryNameChange(newName: String) {
        _uiState.value = _uiState.value.copy(
            inventoryName = newName,
            isSaveButtonEnabled = newName.isNotBlank() && _uiState.value.inventoryDescription.isNotBlank()
        )
    }

    fun onInventoryDescriptionChange(newDescription: String) {
        _uiState.value = _uiState.value.copy(
            inventoryDescription = newDescription,
            isSaveButtonEnabled = _uiState.value.inventoryName.isNotBlank() && newDescription.isNotBlank()
        )
    }
    fun onInventoryIconChange(newIcon: InventoryIcon) {
        _uiState.value = _uiState.value.copy(
            inventoryIcon = newIcon,
        )
    }

    fun saveChanges() {
        val updatedInventory = Inventory(
            id = _uiState.value.inventoryId,
            name = _uiState.value.inventoryName,
            description = _uiState.value.inventoryDescription,
            items = emptyList(),
            createdAt = Date(),
            updatedAt = Date(),
            icon = _uiState.value.inventoryIcon
        )

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val success = repository.updateInventory(updatedInventory)
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