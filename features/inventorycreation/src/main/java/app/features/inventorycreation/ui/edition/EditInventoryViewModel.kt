package app.features.inventorycreation.ui.edition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class EditStateViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(EditInventoryState())
    open val uiState: StateFlow<EditInventoryState> get() = _uiState

    fun onInventoryNameChange(newName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                inventoryName = newName,
                isSaveButtonEnabled = newName.isNotBlank() && currentState.inventoryDescription.isNotBlank()
            )
        }
    }

    fun onInventoryDescriptionChange(newDescription: String) {
        _uiState.update { currentState ->
            currentState.copy(
                inventoryDescription = newDescription,
                isSaveButtonEnabled = currentState.inventoryName.isNotBlank() && newDescription.isNotBlank()
            )
        }
    }

    fun saveChanges() {
        viewModelScope.launch {
        }
    }
}
