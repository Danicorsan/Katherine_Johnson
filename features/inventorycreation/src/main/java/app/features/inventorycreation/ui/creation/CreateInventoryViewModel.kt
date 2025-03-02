package app.features.inventorycreation.ui.creation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.inventory.InventoryState
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
            inventoryShortName = "",
            inventoryDescription = "",
            isCreateButtonEnabled = false,
            loading = false,
            error = null,
            inventoryIcon = InventoryIcon.NONE,
            inventoryItemsCount = 0,
            inventoryType = InventoryType.PERMANENT,
            success = emptyList(),
            nameErrorMessage = null,
            shortNameErrorMessage = null,
            descriptionErrorMessage = null
        )
    )
    val vmState: CreateInventoryState get() = _vmState.value

    fun onInventoryNameChange(newName: String) {
        val nameValidationResult = validateInventoryName(newName)
        updateState(nameErrorMessage = nameValidationResult, inventoryName = newName)
    }

    fun onInventoryShortNameChange(newShortName: String) {
        val shortNameValidationResult = validateInventoryShortName(newShortName)
        updateState(shortNameErrorMessage = shortNameValidationResult, inventoryShortName = newShortName)
    }

    fun onInventoryDescriptionChange(newDescription: String) {
        val descriptionValidationResult = validateInventoryDescription(newDescription)
        updateState(descriptionErrorMessage = descriptionValidationResult, inventoryDescription = newDescription)
    }

    private fun validateInventoryName(name: String): String? {
        return when {
            name.isBlank() -> "El nombre del inventario no puede estar vacío."
            else -> null
        }
    }

    private fun validateInventoryShortName(shortName: String): String? {
        return when {
            shortName.isBlank() -> "El nombre corto del inventario no puede estar vacío."
            shortName.length < 3 -> "El nombre corto debe tener al menos tres caracteres."
            !shortName.matches(Regex("^[a-zA-Z0-9]*$")) -> "El nombre corto no debe contener caracteres especiales."
            else -> null
        }
    }

    private fun validateInventoryDescription(description: String): String? {
        return when {
            description.isBlank() -> "La descripción del inventario no puede estar vacía."
            else -> null
        }
    }

    private fun updateState(
        nameErrorMessage: String? = _vmState.value.nameErrorMessage,
        shortNameErrorMessage: String? = _vmState.value.shortNameErrorMessage,
        descriptionErrorMessage: String? = _vmState.value.descriptionErrorMessage,
        inventoryName: String = _vmState.value.inventoryName,
        inventoryShortName: String = _vmState.value.inventoryShortName,
        inventoryDescription: String = _vmState.value.inventoryDescription
    ) {
        _vmState.value = _vmState.value.copy(
            inventoryName = inventoryName,
            inventoryShortName = inventoryShortName,
            inventoryDescription = inventoryDescription,
            nameErrorMessage = nameErrorMessage,
            shortNameErrorMessage = shortNameErrorMessage,
            descriptionErrorMessage = descriptionErrorMessage,
            isCreateButtonEnabled = nameErrorMessage == null && shortNameErrorMessage == null && descriptionErrorMessage == null &&
                    inventoryName.isNotBlank() && inventoryShortName.isNotBlank() && inventoryDescription.isNotBlank()
        )
    }

    fun onInventoryIconChange(newIcon: InventoryIcon) {
        _vmState.value = _vmState.value.copy(inventoryIcon = newIcon)
    }

    fun onInventoryTypeChange(newType: InventoryType) {
        _vmState.value = _vmState.value.copy(inventoryType = newType)
    }

    fun onInventoryStateChange(newState: InventoryState) {
        _vmState.value = _vmState.value.copy(inventoryState = newState)
    }
    fun onInventoryCodeChange(newCode: String) {
        _vmState.value = _vmState.value.copy(inventoryCode = newCode)
    }

    fun addInventory(inventory: Inventory) {
        _vmState.value = _vmState.value.copy(loading = true)

        viewModelScope.launch {
            delay(1000)

            try {
                val newId = repository.addInventory(inventory)
                if (newId > 0) {
                    val updatedInventories = _vmState.value.success + inventory.copy(id = newId)
                    _vmState.value = _vmState.value.copy(
                        success = updatedInventories,
                        loading = false
                    )
                } else {
                    _vmState.value = _vmState.value.copy(
                        error = "Error al crear el inventario",
                        loading = false
                    )
                }
            } catch (e: Exception) {
                _vmState.value = _vmState.value.copy(
                    error = e.message ?: "Error desconocido",
                    loading = false
                )
            }
        }
    }
}
