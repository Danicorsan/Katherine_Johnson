package app.domain.invoicing.repository

import app.domain.invoicing.inventory.Inventory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object InventoryRepository {

    // Simulación de un dataset en memoria
    private val dataSet: MutableList<Inventory> = initialiceInventoryDemo()
    private val _inventoryListState = MutableStateFlow<List<Inventory>>(emptyList())
    val inventoryListState: StateFlow<List<Inventory>> = _inventoryListState

    // Método para obtener todos los inventarios
    fun getAllInventories(): List<Inventory> = dataSet

    // Método para obtener un inventario por ID
    fun getInventoryById(id: Int): Inventory? {
        return dataSet.find { it.id == id }
    }

    // Método para agregar un nuevo inventario
    fun addInventory(inventory: Inventory): Boolean {
        if (dataSet.any { it.id == inventory.id }) return false // ID duplicado
        dataSet.add(inventory)
        return true
    }

    // Método para actualizar un inventario existente
    fun updateInventory(updatedInventory: Inventory): Boolean {
        val index = dataSet.indexOfFirst { it.id == updatedInventory.id }
        if (index == -1) return false
        dataSet[index] = updatedInventory.copy(items = dataSet[index].items)
        return true
    }

    // Método para eliminar un inventario por ID
    // Método para eliminar un inventario por ID
    fun deleteInventory(id: Int): Boolean {
        val index = dataSet.indexOfFirst { it.id == id }
        if (index == -1) {
            // Si no se encuentra el inventario con ese ID
            return false
        }

        // Si se encuentra, se elimina y se retorna true
        dataSet.removeAt(index)
        return true
    }
}
