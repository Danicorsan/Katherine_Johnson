package app.features.inventorycreation.ui.creation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class CreateInventoryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CreateInventoryState())
    open val uiState: StateFlow<CreateInventoryState> = _uiState

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
        // Logic to create the inventory (e.g., save to a database)
    }
}