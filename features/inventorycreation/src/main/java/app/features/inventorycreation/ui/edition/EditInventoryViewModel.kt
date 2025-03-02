package app.features.inventorycreation.ui.edition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.inventory.InventoryState
import app.domain.invoicing.inventory.InventoryType
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

    private val _vmState = MutableStateFlow(
        EditInventoryState(
            inventoryId = 0,
            inventoryName = "",
            inventoryDescription = "",
            inventoryIcon = InventoryIcon.NONE,
            inventoryItemsCount = 0,
            inventoryType = InventoryType.WEEKLY,
            inventoryState = InventoryState.ACTIVE,
            inventoryShortName = "",
            inventoryCode = "",
            isSaveButtonEnabled = false,
            isLoading = false,
            errorMessage = null,
            nameErrorMessage = null,
            originalName = "",
            originalDescription = "",
            originalIcon = InventoryIcon.NONE,
            originalType = InventoryType.WEEKLY,
            originalShortName = "",
            originalState = InventoryState.IN_PROGRESS,
            originalCode = "",
            noChangesMessage = null,
            inventoryInProgressDateAt = Date(),
            inventoryActiveDateAt = null,
            inventoryHistoryDateAt = null,
            originalInProgressDateAt = Date(),
            originalActiveDateAt = null,
            originalHistoryDateAt = null
        )
    )
    val vmState: StateFlow<EditInventoryState> get() = _vmState

    fun loadInventory(inventoryId: Int) {
        viewModelScope.launch {
            _vmState.value = _vmState.value.copy(isLoading = true)
            try {
                val inventory = repository.getInventoryById(inventoryId)
                if (inventory != null) {
                    _vmState.value = _vmState.value.copy(
                        inventoryId = inventory.id,
                        inventoryName = inventory.name,
                        inventoryDescription = inventory.description,
                        inventoryIcon = inventory.icon,
                        inventoryType = inventory.inventoryType,
                        inventoryShortName = inventory.shortName,
                        inventoryCode = inventory.code,
                        inventoryState = inventory.state,
                        inventoryInProgressDateAt = inventory.inProgressDateAt,
                        inventoryActiveDateAt = inventory.activeDateAt,
                        inventoryHistoryDateAt = inventory.historyDateAt,
                        originalName = inventory.name,
                        originalDescription = inventory.description,
                        originalIcon = inventory.icon,
                        originalType = inventory.inventoryType,
                        originalShortName = inventory.shortName,
                        originalCode = inventory.code,
                        originalState = inventory.state,
                        originalInProgressDateAt = inventory.inProgressDateAt,
                        originalActiveDateAt = inventory.activeDateAt,
                        originalHistoryDateAt = inventory.historyDateAt,
                        isLoading = false,
                        errorMessage = null
                    )
                } else {
                    _vmState.value = _vmState.value.copy(
                        errorMessage = "Inventario no encontrado",
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _vmState.value = _vmState.value.copy(
                    errorMessage = "Error al cargar el inventario: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    fun onInventoryNameChange(newName: String) {
        if (vmState.value.originalState != InventoryState.IN_PROGRESS) {
            _vmState.value = vmState.value.copy(
                errorMessage = "No se puede editar el inventario si no está en estado INPROGRESS"
            )
            return
        }
        val nameErrorMessage = validateInventoryName(newName)
        val isDifferent = newName != vmState.value.originalName
        _vmState.value = vmState.value.copy(
            inventoryName = newName,
            nameErrorMessage = nameErrorMessage,
            isSaveButtonEnabled = isDifferent ||
                    vmState.value.inventoryDescription != vmState.value.originalDescription ||
                    vmState.value.inventoryIcon != vmState.value.originalIcon ||
                    vmState.value.inventoryType != vmState.value.originalType ||
                    vmState.value.inventoryShortName != vmState.value.originalShortName ||
                    vmState.value.inventoryState != vmState.value.originalState ||
                    vmState.value.inventoryCode != vmState.value.originalCode,
            noChangesMessage = if (!isDifferent) "El nombre no ha cambiado" else null,
            errorMessage = null
        )
    }

    fun onInventoryDescriptionChange(newDescription: String) {
        if (vmState.value.originalState != InventoryState.IN_PROGRESS) {
            _vmState.value = vmState.value.copy(
                errorMessage = "No se puede editar el inventario si no está en estado INPROGRESS"
            )
            return
        }
        val isDifferent = newDescription != vmState.value.originalDescription
        _vmState.value = vmState.value.copy(
            inventoryDescription = newDescription,
            isSaveButtonEnabled = isDifferent ||
                    vmState.value.inventoryName != vmState.value.originalName ||
                    vmState.value.inventoryIcon != vmState.value.originalIcon ||
                    vmState.value.inventoryType != vmState.value.originalType ||
                    vmState.value.inventoryShortName != vmState.value.originalShortName ||
                    vmState.value.inventoryState != vmState.value.originalState ||
                    vmState.value.inventoryCode != vmState.value.originalCode,
            noChangesMessage = if (!isDifferent) "La descripción no ha cambiado" else null,
            errorMessage = null
        )
    }

    fun onInventoryIconChange(newIcon: InventoryIcon) {
        if (vmState.value.originalState != InventoryState.IN_PROGRESS) {
            _vmState.value = vmState.value.copy(
                errorMessage = "No se puede editar el inventario si no está en estado INPROGRESS"
            )
            return
        }
        val isDifferent = newIcon != vmState.value.originalIcon
        _vmState.value = vmState.value.copy(
            inventoryIcon = newIcon,
            isSaveButtonEnabled = isDifferent ||
                    vmState.value.inventoryName != vmState.value.originalName ||
                    vmState.value.inventoryDescription != vmState.value.originalDescription ||
                    vmState.value.inventoryType != vmState.value.originalType ||
                    vmState.value.inventoryShortName != vmState.value.originalShortName ||
                    vmState.value.inventoryState != vmState.value.originalState ||
                    vmState.value.inventoryCode != vmState.value.originalCode,
            noChangesMessage = if (!isDifferent) "El icono no ha cambiado" else null,
            errorMessage = null
        )
    }

    fun onInventoryTypeChange(newType: InventoryType) {
        if (vmState.value.originalState != InventoryState.IN_PROGRESS) {
            _vmState.value = vmState.value.copy(
                errorMessage = "No se puede editar el inventario si no está en estado INPROGRESS"
            )
            return
        }
        val isDifferent = newType != vmState.value.originalType
        _vmState.value = vmState.value.copy(
            inventoryType = newType,
            isSaveButtonEnabled = isDifferent ||
                    vmState.value.inventoryName != vmState.value.originalName ||
                    vmState.value.inventoryDescription != vmState.value.originalDescription ||
                    vmState.value.inventoryIcon != vmState.value.originalIcon ||
                    vmState.value.inventoryShortName != vmState.value.originalShortName ||
                    vmState.value.inventoryState != vmState.value.originalState ||
                    vmState.value.inventoryCode != vmState.value.originalCode,
            noChangesMessage = if (!isDifferent) "El tipo de inventario no ha cambiado" else null,
            errorMessage = null
        )
    }

    fun onInventoryShortNameChange(newShortName: String) {
        if (vmState.value.originalState != InventoryState.IN_PROGRESS) {
            _vmState.value = vmState.value.copy(
                errorMessage = "No se puede editar el inventario si no está en estado INPROGRESS"
            )
            return
        }
        val isDifferent = newShortName != vmState.value.originalShortName
        _vmState.value = vmState.value.copy(
            inventoryShortName = newShortName,
            isSaveButtonEnabled = isDifferent ||
                    vmState.value.inventoryName != vmState.value.originalName ||
                    vmState.value.inventoryDescription != vmState.value.originalDescription ||
                    vmState.value.inventoryIcon != vmState.value.originalIcon ||
                    vmState.value.inventoryType != vmState.value.originalType ||
                    vmState.value.inventoryState != vmState.value.originalState ||
                    vmState.value.inventoryCode != vmState.value.originalCode,
            noChangesMessage = if (!isDifferent) "El nombre corto no ha cambiado" else null,
            errorMessage = null
        )
    }

    fun onInventoryStateChange(newState: InventoryState) {
        if (vmState.value.originalState != InventoryState.IN_PROGRESS) {
            _vmState.value = vmState.value.copy(
                errorMessage = "No se puede editar el inventario si no está en estado INPROGRESS"
            )
            return
        }

        val isDifferent = newState != vmState.value.originalState
        var inProgressDate = vmState.value.inventoryInProgressDateAt
        var activeDate = vmState.value.inventoryActiveDateAt
        var historyDate = vmState.value.inventoryHistoryDateAt
        var originalInProgressDate = vmState.value.originalInProgressDateAt
        var originalActiveDate = vmState.value.originalActiveDateAt
        var originalHistoryDate = vmState.value.originalHistoryDateAt

        when (newState) {
            InventoryState.IN_PROGRESS -> {
                if (vmState.value.originalState != InventoryState.IN_PROGRESS) {
                    inProgressDate = Date()
                    activeDate = vmState.value.inventoryActiveDateAt // Reset active date if going to IN_PROGRESS
                    historyDate = vmState.value.inventoryHistoryDateAt // Reset history date if going to IN_PROGRESS
                    originalInProgressDate = inProgressDate // Update original date
                    originalActiveDate = vmState.value.originalActiveDateAt // Reset original active date
                    originalHistoryDate = vmState.value.originalHistoryDateAt // Reset original history date
                }
            }

            InventoryState.ACTIVE -> {
                if (vmState.value.originalState != InventoryState.ACTIVE) {
                    activeDate = Date()
                    inProgressDate = vmState.value.inventoryInProgressDateAt // Reset inProgress date if going to ACTIVE
                    historyDate = vmState.value.inventoryHistoryDateAt // Reset history date if going to ACTIVE
                    originalActiveDate = activeDate // Update original date
                    originalInProgressDate = vmState.value.originalInProgressDateAt // Reset original inProgress date
                    originalHistoryDate = vmState.value.originalHistoryDateAt // Reset original history date
                }
            }

            InventoryState.HISTORY -> {
                if (vmState.value.originalState != InventoryState.HISTORY) {
                    historyDate = Date()
                    inProgressDate = vmState.value.inventoryInProgressDateAt // Reset inProgress date if going to HISTORY
                    activeDate = vmState.value.inventoryActiveDateAt // Reset active date if going to HISTORY
                    originalHistoryDate = historyDate // Update original date
                    originalInProgressDate = vmState.value.originalInProgressDateAt // Reset original inProgress date
                    originalActiveDate = vmState.value.originalActiveDateAt // Reset original active date
                }
            }

            else -> {
                // No changes to dates for other states
            }
        }

        _vmState.value = vmState.value.copy(
            inventoryState = newState,
            inventoryInProgressDateAt = inProgressDate,
            inventoryActiveDateAt = activeDate,
            inventoryHistoryDateAt = historyDate,
            originalInProgressDateAt = originalInProgressDate,
            originalActiveDateAt = originalActiveDate,
            originalHistoryDateAt = originalHistoryDate,
            isSaveButtonEnabled = isDifferent ||
                    vmState.value.inventoryName != vmState.value.originalName ||
                    vmState.value.inventoryDescription != vmState.value.originalDescription ||
                    vmState.value.inventoryIcon != vmState.value.originalIcon ||
                    vmState.value.inventoryType != vmState.value.originalType ||
                    vmState.value.inventoryShortName != vmState.value.originalShortName ||
                    vmState.value.inventoryCode != vmState.value.originalCode,
            noChangesMessage = if (!isDifferent) "El estado no ha cambiado" else null,
            errorMessage = null
        )
    }

    fun onInventoryCodeChange(newCode: String) {
        if (vmState.value.originalState != InventoryState.IN_PROGRESS) {
            _vmState.value = vmState.value.copy(
                errorMessage = "No se puede editar el inventario si no está en estado INPROGRESS"
            )
            return
        }
        val isDifferent = newCode != vmState.value.originalCode
        _vmState.value = vmState.value.copy(
            inventoryCode = newCode,
            isSaveButtonEnabled = isDifferent ||
                    vmState.value.inventoryName != vmState.value.originalName ||
                    vmState.value.inventoryDescription != vmState.value.originalDescription ||
                    vmState.value.inventoryIcon != vmState.value.originalIcon ||
                    vmState.value.inventoryType != vmState.value.originalType ||
                    vmState.value.inventoryShortName != vmState.value.originalShortName ||
                    vmState.value.inventoryState != vmState.value.originalState,
            noChangesMessage = if (!isDifferent) "El codigo no ha cambiado" else null,
            errorMessage = null
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
        if (vmState.value.originalState != InventoryState.IN_PROGRESS) {
            _vmState.value = vmState.value.copy(
                errorMessage = "No se puede guardar el inventario si no está en estado INPROGRESS"
            )
            return
        }

        viewModelScope.launch {
            _vmState.value = _vmState.value.copy(isLoading = true, errorMessage = null)

            try {
                val updatedInventory = Inventory(
                    id = vmState.value.inventoryId,
                    name = vmState.value.inventoryName,
                    description = vmState.value.inventoryDescription,
                    icon = vmState.value.inventoryIcon,
                    inventoryType = vmState.value.inventoryType,
                    shortName = vmState.value.inventoryShortName,
                    state = vmState.value.inventoryState,
                    code = vmState.value.inventoryCode,
                    inProgressDateAt = vmState.value.inventoryInProgressDateAt,
                    activeDateAt = vmState.value.inventoryActiveDateAt,
                    historyDateAt = vmState.value.inventoryHistoryDateAt
                )

                val success = repository.updateInventory(updatedInventory)

                if (success) {
                    _vmState.value = _vmState.value.copy(
                        isLoading = false,
                        errorMessage = null,
                        originalName = vmState.value.inventoryName,
                        originalDescription = vmState.value.inventoryDescription,
                        originalIcon = vmState.value.inventoryIcon,
                        originalType = vmState.value.inventoryType,
                        originalShortName = vmState.value.inventoryShortName,
                        originalCode = vmState.value.inventoryCode,
                        originalState = vmState.value.inventoryState,
                        originalInProgressDateAt = vmState.value.inventoryInProgressDateAt,
                        originalActiveDateAt = vmState.value.inventoryActiveDateAt,
                        originalHistoryDateAt = vmState.value.inventoryHistoryDateAt,
                        noChangesMessage = "Cambios guardados correctamente"
                    )
                } else {
                    _vmState.value = _vmState.value.copy(
                        isLoading = false,
                        errorMessage = "Error al guardar los cambios",
                        noChangesMessage = null
                    )
                }
            } catch (e: Exception) {
                _vmState.value = _vmState.value.copy(
                    isLoading = false,
                    errorMessage = "Error al guardar los cambios: ${e.message}",
                    noChangesMessage = null
                )
            }
        }
    }
}
