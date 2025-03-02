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

    private val _vmState = MutableStateFlow(EditInventoryState())
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
                    inventoryIcon = inventory.icon,
                    inventoryType = inventory.inventoryType,
                    inventoryItemsCount = 0,
                    originalName = inventory.name,
                    originalDescription = inventory.description,
                    originalIcon = inventory.icon,
                    originalType = inventory.inventoryType,
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
        val nameErrorMessage = validateInventoryName(newName)
        val isDifferent = newName != vmState.value.originalName
        _vmState.value = vmState.value.copy(
            inventoryName = newName,
            nameErrorMessage = nameErrorMessage,
            isSaveButtonEnabled = isDifferent ||
                    vmState.value.inventoryDescription != vmState.value.originalDescription ||
                    vmState.value.inventoryIcon != vmState.value.originalIcon ||
                    vmState.value.inventoryType != vmState.value.originalType,
            noChangesMessage = if (!isDifferent) "El nombre no ha cambiado" else null
        )
    }

    fun onInventoryDescriptionChange(newDescription: String) {
        val isDifferent = newDescription != vmState.value.originalDescription
        _vmState.value = vmState.value.copy(
            inventoryDescription = newDescription,
            isSaveButtonEnabled = isDifferent ||
                    vmState.value.inventoryName != vmState.value.originalName ||
                    vmState.value.inventoryIcon != vmState.value.originalIcon ||
                    vmState.value.inventoryType != vmState.value.originalType,
            noChangesMessage = if (!isDifferent) "La descripción no ha cambiado" else null
        )
    }

    fun onInventoryIconChange(newIcon: InventoryIcon) {
        val isDifferent = newIcon != vmState.value.originalIcon
        _vmState.value = vmState.value.copy(
            inventoryIcon = newIcon,
            isSaveButtonEnabled = isDifferent ||
                    vmState.value.inventoryName != vmState.value.originalName ||
                    vmState.value.inventoryDescription != vmState.value.originalDescription ||
                    vmState.value.inventoryType != vmState.value.originalType,
            noChangesMessage = if (!isDifferent) "El icono no ha cambiado" else null
        )
    }

    fun onInventoryTypeChange(newType: InventoryType) {
        val isDifferent = newType != vmState.value.originalType
        _vmState.value = vmState.value.copy(
            inventoryType = newType,
            isSaveButtonEnabled = isDifferent ||
                    vmState.value.inventoryName != vmState.value.originalName ||
                    vmState.value.inventoryDescription != vmState.value.originalDescription ||
                    vmState.value.inventoryIcon != vmState.value.originalIcon,
            noChangesMessage = if (!isDifferent) "El tipo de inventario no ha cambiado" else null
        )
    }

    private fun validateInventoryName(name: String): String? {
        return when {
            name.isBlank() -> "El nombre del inventario no puede estar vacío."
            name.length < 3 -> "El nombre del inventario debe tener al menos 3 caracteres."
            else -> null
        }
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
            createdAt = LocalDate.now()
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
