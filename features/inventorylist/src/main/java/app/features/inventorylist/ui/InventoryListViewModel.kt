package app.features.inventorylist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.repository.InventoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InventoryListViewModel(
    private val inventoryRepository: InventoryRepository
) : ViewModel() {

    private val _inventories = mutableListOf<Inventory>() // Lista de inventarios local

    val inventories: List<Inventory> get() = _inventories // Getter para la lista de inventarios

    init {
        loadInventories() // Cargar inventarios cuando se inicie el ViewModel
    }

    // Cargar los inventarios desde el repositorio
    private fun loadInventories() {
        viewModelScope.launch {
            _inventories.clear()
            _inventories.addAll(inventoryRepository.getAllInventories())
        }
    }

    // Función para eliminar un inventario
    fun deleteInventory(inventory: Inventory) {
        viewModelScope.launch {
            val success = inventoryRepository.deleteInventory(inventory.id)
            if (success) {
                _inventories.remove(inventory) // Remover de la lista local
            }
        }
    }

    // Función para agregar un inventario
    fun addInventory(inventory: Inventory) {
        viewModelScope.launch {
            val success = inventoryRepository.addInventory(inventory)
            if (success) {
                _inventories.add(inventory) // Agregar a la lista local
            }
        }
    }
}

