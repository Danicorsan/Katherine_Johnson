package app.features.inventorycreation.ui.creation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.inventory.InventoryType
import app.domain.invoicing.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateInventoryViewModel @Inject constructor(
    private val repository: InventoryRepository
) : ViewModel() {
    private val _vmState = mutableStateOf(
        CreateInventoryState(
            inventoryId = 0,
            inventoryName = "",
            inventoryDescription = "",
            isCreateButtonEnabled = false,
            loading = false,
            error = null,
            inventoryIcon = InventoryIcon.NONE,
            inventoryItemsCount = 0,
            inventoryType = InventoryType.PERMANENT,
            success = emptyList(),
            nameErrorMessage = null
        )
    )
    val vmState: CreateInventoryState get() = _vmState.value

    fun onInventoryNameChange(newName: String) {
        val nameValidationResult = validateInventoryName(newName)

        _vmState.value = vmState.copy(
            inventoryName = newName,
            isCreateButtonEnabled = nameValidationResult == null,
            nameErrorMessage = nameValidationResult
        )
    }
    private fun validateInventoryName(name: String): String? {
        if (name.isBlank()) {
            return "El nombre del inventario no puede estar vacío."
        }
        if (name.length < 3) {
            return "El nombre del inventario no puede tener menos de 3 caracteres."
        }
        return null
    }

    fun onInventoryDescriptionChange(newDescription: String) {
        _vmState.value = vmState.copy(
            inventoryDescription = newDescription,
            isCreateButtonEnabled = vmState.inventoryName.isNotBlank() && vmState.nameErrorMessage == null
        )
    }

    fun onInventoryIconChange(newIcon: InventoryIcon) {
        _vmState.value = _vmState.value.copy(
            inventoryIcon = newIcon,
            isCreateButtonEnabled = vmState.inventoryName.isNotBlank() && vmState.nameErrorMessage == null

        )
    }

    fun onInventoryTypeChange(newType: InventoryType) {
        _vmState.value = vmState.copy(
            inventoryType = newType,
            isCreateButtonEnabled = vmState.inventoryName.isNotBlank() && vmState.nameErrorMessage == null
        )
    }

    fun addInventory(inventory: Inventory) {
        _vmState.value = vmState.copy(loading = true)

        viewModelScope.launch {

            delay(1000)

            try {
                val newId = repository.addInventory(inventory)
                if (newId > 0) {
                    val updatedInventories = _vmState.value.success + inventory.copy(id = newId)

                    _vmState.value = vmState.copy(
                        success = updatedInventories,
                        loading = false
                    )
                } else {
                    _vmState.value = vmState.copy(
                        error = "Error al crear el inventario",
                        loading = false
                    )
                }
            } catch (e: Exception) {
                _vmState.value = vmState.copy(
                    error = e.message ?: "Error desconocido",
                    loading = false
                )
            }
        }
    }
}
