package app.features.inventorycreation.ui.creation

import androidx.lifecycle.ViewModel
import app.domain.invoicing.inventory.Inventory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    fun createInventory() {
        if (_uiState.value.isCreateButtonEnabled) {
            Inventory(
                id = generateInventoryId(),
                name = _uiState.value.inventoryName,
                description = _uiState.value.inventoryDescription,
                items = emptyList(),
                createdAt = Date(),
                updatedAt = Date()
            )

            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
        }
    }

    private fun generateInventoryId(): Int {
        return (System.currentTimeMillis() % Int.MAX_VALUE).toInt()
    }
}
