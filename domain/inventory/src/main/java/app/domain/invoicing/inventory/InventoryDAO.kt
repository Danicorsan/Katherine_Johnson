package app.domain.invoicing.inventory

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface InventoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInventory(inventory: Inventory)

    @Query("SELECT * FROM inventories")
    suspend fun getAllInventories(): List<Inventory>

    @Query("SELECT * FROM inventories WHERE id = :id")
    suspend fun getInventoryById(id: Int): Inventory?

    @Query("DELETE FROM inventories WHERE id = :id")
    suspend fun deleteInventoryById(id: Int)

    @Query("UPDATE inventories SET name = :name, description = :description, icon = :icon, inventory_type = :type WHERE id = :id")
    suspend fun updateInventory(
        id: Int,
        name: String,
        description: String,
        icon: InventoryIcon,
        type: InventoryType
    )

}