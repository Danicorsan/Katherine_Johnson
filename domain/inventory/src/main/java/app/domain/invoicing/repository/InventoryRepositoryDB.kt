package app.domain.invoicing.repository

import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryDAO

class InventoryRepositoryDB(private val inventoryDAO: InventoryDAO) {
    suspend fun getAllInventories(): List<Inventory> = inventoryDAO.getAllInventories()

    suspend fun getInventoryById(id: Int): Inventory? = inventoryDAO.getInventoryById(id)

    suspend fun addInventory(inventory: Inventory): Int {
        if (inventoryDAO.getInventoryById(inventory.id) != null) throw Exception("Inventory with same ID already exists")
        inventoryDAO.addInventory(inventory)
        return inventory.id
    }

    suspend fun updateInventory(updatedInventory: Inventory): Boolean {
        val index = inventoryDAO.getInventoryById(updatedInventory.id) ?: return false
        inventoryDAO.updateInventory(
            id = updatedInventory.id,
            name = updatedInventory.name,
            description = updatedInventory.description,
            icon = updatedInventory.icon,
            type = updatedInventory.inventoryType
        )
        return true
    }

    suspend fun deleteInventory(id: Int): Boolean {
        val index = inventoryDAO.getInventoryById(id) ?: return false
        inventoryDAO.deleteInventoryById(id)
        return true
    }
}