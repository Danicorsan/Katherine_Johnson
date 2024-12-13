package app.domain.invoicing.repository

import app.domain.invoicing.inventory.Inventory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InventoryRepository {

    private val _inventories = MutableStateFlow<List<Inventory>>(emptyList())
    val inventories: Flow<List<Inventory>> = _inventories.asStateFlow()

    /**
     * Añade un nuevo inventario.
     * @param inventory Inventario a añadir.
     */
    fun addInventory(inventory: Inventory) {
        _inventories.value += inventory
    }

    /**
     * Actualiza un inventario existente.
     * @param updatedInventory Inventario actualizado.
     */
    fun updateInventory(updatedInventory: Inventory) {
        _inventories.value = _inventories.value.map { inventory ->
            if (inventory.name == updatedInventory.name) updatedInventory else inventory
        }
    }

    /**
     * Elimina un inventario.
     * @param inventory Inventario a eliminar.
     */
    fun deleteInventory(inventory: Inventory) {
        _inventories.value = _inventories.value.filter { it.name != inventory.name }
    }

    /**
     * Busca un inventario por su nombre.
     * @param name Nombre del inventario.
     * @return Inventario encontrado o null si no existe.
     */
    fun findInventoryByName(name: String): Inventory? {
        return _inventories.value.find { it.name == name }
    }
}
