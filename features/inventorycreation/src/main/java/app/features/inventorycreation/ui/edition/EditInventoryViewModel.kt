package app.features.inventorycreation.ui.edition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class EditInventoryViewModel(
    private val inventoryRepository: InventoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditInventoryState())
    open val uiState: StateFlow<EditInventoryState> get() = _uiState

    open fun loadInventory(inventoryId: Int) {
        // Simula cargar el inventario desde el repositorio (reemplazar por lógica real)
        val inventory = inventoryRepository.findInventoryById(inventoryId)
        if (inventory != null) {
            _uiState.value = _uiState.value.copy(
                inventoryId = inventory.id,
                inventoryName = inventory.name,
                inventoryDescription = inventory.description
            )
        }
    }

    open fun onInventoryNameChange(newName: String) {
        _uiState.value = _uiState.value.copy(
            inventoryName = newName,
            isSaveButtonEnabled = newName.isNotBlank() && _uiState.value.inventoryDescription.isNotBlank()
        )
    }

    open fun onInventoryDescriptionChange(newDescription: String) {
        _uiState.value = _uiState.value.copy(
            inventoryDescription = newDescription,
            isSaveButtonEnabled = _uiState.value.inventoryName.isNotBlank() && newDescription.isNotBlank()
        )
    }

    open fun saveChanges() {
        // Lógica para actualizar el inventario en el repositorio
        val updatedInventory = Inventory(
            id = _uiState.value.inventoryId,
            name = _uiState.value.inventoryName,
            description = _uiState.value.inventoryDescription,
            items = emptyList()  // Dejar vacío si no se quieren modificar los artículos
        )
        viewModelScope.launch {
            inventoryRepository.updateInventory(updatedInventory)
        }
    }
}
class FakeInventoryRepository : InventoryRepository() {
    override fun findInventoryById(inventoryId: Int): Inventory? {
        // Simulamos encontrar un inventario
        return Inventory(
            id = inventoryId,
            name = "Inventario $inventoryId",
            description = "Descripción simulada del inventario",
            items = emptyList()  // No se añaden artículos para este ejemplo
        )
    }

    override suspend fun updateInventory(inventory: Inventory) {
        // Simulamos el proceso de actualización
        println("Inventario actualizado: ${inventory.name}")
    }
}

