package app.features.inventorycreation.ui.creation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class CreateInventoryViewModel(
    private val inventoryRepository: InventoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateInventoryState())
    open val uiState: StateFlow<CreateInventoryState> get() = _uiState

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
            val newInventory = Inventory(
                id = generateInventoryId(),
                name = _uiState.value.inventoryName,
                description = _uiState.value.inventoryDescription,
                items = emptyList()
            )
            viewModelScope.launch {
                inventoryRepository.addInventory(newInventory)
            }
        }
    }

    private fun generateInventoryId(): Int {
        return (System.currentTimeMillis() % Int.MAX_VALUE).toInt()
    }
}
