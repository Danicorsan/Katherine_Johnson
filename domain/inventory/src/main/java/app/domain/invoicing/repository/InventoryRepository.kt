package app.domain.invoicing.repository

import app.domain.invoicing.inventory.Inventory

object InventoryRepository {

    private val dataSet: MutableList<Inventory> = initialiceInventoryDemo()

    fun getAllInventories(): List<Inventory> = dataSet

    fun getInventoryById(id: Int): Inventory? =
        dataSet.find { it.id == id }

    private var nextId = dataSet.maxByOrNull { it.id }?.id ?: 0

    fun addInventory(inventory: Inventory): Int {
        if (dataSet.any { it.id == inventory.id }) throw Exception("Inventory with same ID already exists")
        nextId++
        dataSet.add(inventory.copy(id = nextId))
        return nextId
    }

    fun updateInventory(updatedInventory: Inventory): Boolean {
        val index = dataSet.indexOfFirst { it.id == updatedInventory.id }
        if (index == -1) return false
        dataSet[index] = updatedInventory.copy(/*items = dataSet[index].items*/)
        return true
    }

    fun deleteInventory(id: Int): Boolean {
        val index = dataSet.indexOfFirst { it.id == id }
        if (index == -1) {
            return false
        }
        dataSet.removeAt(index)
        return true
    }
}