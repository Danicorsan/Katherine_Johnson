package app.domain.invoicing.repository

import app.domain.invoicing.inventory.Inventory

object InventoryRepository {

    private val dataSet: MutableList<Inventory> = initialiceInventoryDemo()

    fun getAllInventories(): List<Inventory> = dataSet

    fun getInventoryById(id: Int): Inventory? =
        dataSet.find { it.id == id }

    fun addInventory(inventory: Inventory): Boolean {
        if (dataSet.any { it.id == inventory.id }) return false
        dataSet.add(inventory)
        return true
    }

    fun updateInventory(updatedInventory: Inventory): Boolean {
        val index = dataSet.indexOfFirst { it.id == updatedInventory.id }
        if (index == -1) return false
        dataSet[index] = updatedInventory.copy(items = dataSet[index].items)
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