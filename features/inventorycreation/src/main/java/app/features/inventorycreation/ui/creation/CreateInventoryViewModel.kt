package app.features.inventorycreation.ui.creation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateInventoryViewModel @Inject constructor(
    private val repository: InventoryRepository
) : ViewModel() {
    private val _vmState = mutableStateOf(CreateInventoryState(
        inventoryId = 0,
        inventoryName = "",
        inventoryDescription = "",
        isCreateButtonEnabled = false,
        loading = false,
        error = null,
        inventoryIcon = InventoryIcon.NONE,
    ))
    val vmState: CreateInventoryState get() = _vmState.value

    fun onInventoryNameChange(newName: String) {
        _vmState.value = _vmState.value.copy(
            inventoryName = newName,
            isCreateButtonEnabled = newName.isNotBlank() && _vmState.value.inventoryDescription.isNotBlank()
        )
    }

    fun onInventoryDescriptionChange(newDescription: String) {
        _vmState.value = _vmState.value.copy(
            inventoryDescription = newDescription,
            isCreateButtonEnabled = _vmState.value.inventoryName.isNotBlank() && newDescription.isNotBlank()
        )
    }
    fun onInventoryIconChange(newIcon: InventoryIcon) {
        _vmState.value = _vmState.value.copy(
            inventoryIcon = newIcon
        )
    }

    fun addInventory(inventory: Inventory) {
        _vmState.value = _vmState.value.copy(loading = true) // Mostrar pantalla de carga

        viewModelScope.launch {

            delay(1000) // Retardo de un segundo antes de cualquier operación

            try {
                val newId = repository.addInventory(inventory)
                if (newId > 0) {
                    val updatedInventories = _vmState.value.success + inventory.copy(id = newId)

                    _vmState.value = _vmState.value.copy(
                        success = updatedInventories,
                        loading = false // Ocultar pantalla de carga
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