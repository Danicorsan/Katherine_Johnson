package app.domain.invoicing.repository

import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryDAO

class InventoryRepositoryDB(private val inventoryDAO: InventoryDAO) {

    suspend fun getAllInventories(): List<Inventory> = inventoryDAO.getAllInventories()

    suspend fun getInventoryById(id: Int): Inventory? = inventoryDAO.getInventoryById(id)

    suspend fun addInventory(inventory: Inventory): Int {
        inventoryDAO.addInventory(inventory)
        return inventory.id
    }

    suspend fun updateInventory(updatedInventory: Inventory): Boolean {
        val existingInventory = inventoryDAO.getInventoryById(updatedInventory.id)
        return if (existingInventory != null) {
            inventoryDAO.updateInventory(
                updatedInventory.id,
                updatedInventory.name,
                updatedInventory.shortName,
                updatedInventory.code,
                updatedInventory.description,
                updatedInventory.inventoryType,
                updatedInventory.historyDateAt,
                updatedInventory.inProgressDateAt,
                updatedInventory.activeDateAt,
                updatedInventory.icon,
                updatedInventory.state
            )
            true
        } else {
            false
        }
    }

    suspend fun deleteInventory(id: Int): Boolean {
        return try {
            inventoryDAO.deleteInventoryById(id)
            true
        } catch (e: Exception) {
            false
        }
    }
}
