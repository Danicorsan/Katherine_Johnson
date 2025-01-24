package app.domain.invoicing.repository

import app.domain.invoicing.inventory.Inventory
import java.util.Date

object InventoryRepository {

    // Simulación de un dataset en memoria
    private val dataSet: MutableList<Inventory> = mutableListOf()

    init {
        initialize()
    }

    private fun initialize() {
        // Aquí puedes agregar algunos datos iniciales si lo deseas
        dataSet.add(
            Inventory(
                id = 1,
                name = "Laptop",
                description = "High-performance laptop",
                items = emptyList(),
                createdAt = Date()
            )
        )
        dataSet.add(
            Inventory(
                id = 2,
                name = "Smartphone",
                description = "Latest smartphone model",
                items = emptyList(),
                createdAt = Date()
            )
        )
        dataSet.add(
            Inventory(
                id = 3,
                name = "Headphones",
                description = "Noise-cancelling headphones",
                items = emptyList(),
                createdAt = Date()
            )
        )
    }

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
        dataSet[index] = updatedInventory
        return true
    }

    // Método para eliminar un inventario por ID
    fun deleteInventory(id: Int): Boolean {
        return dataSet.removeIf { it.id == id }
    }
}
