package app.features.inventorycreation.ui.edition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.inventory.InventoryType
import app.domain.invoicing.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class EditInventoryViewModel @Inject constructor(
    private val repository: InventoryRepository
) : ViewModel() {

    private val _vmState = MutableStateFlow(EditInventoryState(
        inventoryId = 0,
        inventoryName = "",
        inventoryDescription = "",
        inventoryIcon = InventoryIcon.NONE,
        isSaveButtonEnabled = false,
        isLoading = false,
        errorMessage = "",
        inventoryItemsCount = 0,
        inventoryType = InventoryType.PERMANENT
    ))
    val vmState: StateFlow<EditInventoryState> get() = _vmState

    fun loadInventory(inventoryId: Int) {
        viewModelScope.launch {
            _vmState.value = vmState.value.copy(isLoading = true)

            val inventory = repository.getInventoryById(inventoryId)

            if (inventory != null) {
                _vmState.value = vmState.value.copy(
                    inventoryId = inventory.id,
                    inventoryName = inventory.name,
                    inventoryDescription = inventory.description,
                    isLoading = false
                )
            } else {
                _vmState.value = vmState.value.copy(
                    errorMessage = "Inventario no encontrado",
                    isLoading = false
                )
            }
        }
    }

    fun onInventoryNameChange(newName: String) {
        _vmState.value = vmState.value.copy(
            inventoryName = newName,
            isSaveButtonEnabled = newName.isNotBlank() && vmState.value.inventoryDescription.isNotBlank()
        )
    }

    fun onInventoryDescriptionChange(newDescription: String) {
        _vmState.value = vmState.value.copy(
            inventoryDescription = newDescription,
            isSaveButtonEnabled = _vmState.value.inventoryName.isNotBlank() && newDescription.isNotBlank()
        )
    }
    fun onInventoryIconChange(newIcon: InventoryIcon) {
        _vmState.value = vmState.value.copy(
            inventoryIcon = newIcon,
            isSaveButtonEnabled = _vmState.value.inventoryName.isNotBlank() && newIcon.compareTo(
                other = vmState.value.inventoryIcon
            ) != 0
        )
    }
    fun onInventoryTypeChange(newType: InventoryType) {
        _vmState.value = vmState.value.copy(
            inventoryType = newType,
            isSaveButtonEnabled = _vmState.value.inventoryType.compareTo(
                other = vmState.value.inventoryType
            ) != 0
        )
    }

    fun saveChanges() {
        val updatedInventory = Inventory(
            id = vmState.value.inventoryId,
            name = vmState.value.inventoryName,
            description = vmState.value.inventoryDescription,
            updatedAt = LocalDate.now(),
            icon = vmState.value.inventoryIcon,
            itemsCount = vmState.value.inventoryItemsCount,
            inventoryType = vmState.value.inventoryType,
            createdAt = LocalDate.now(),
        )

        viewModelScope.launch {
            _vmState.value = vmState.value.copy(isLoading = true)
            val success = repository.updateInventory(updatedInventory)
            if (success) {
                _vmState.value = _vmState.value.copy(
                    isLoading = false,
                    errorMessage = null
                )
            } else {
                _vmState.value = vmState.value.copy(
                    isLoading = false,
                    errorMessage = "Error al guardar el inventario"
                )
            }
        }
    }
}