package app.domain.invoicing.inventory

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.Date

@Dao
interface InventoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInventory(inventory: Inventory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllInventories(inventory: Inventory)

    @Query("SELECT * FROM inventories")
    suspend fun getAllInventories(): List<Inventory>

    @Query("SELECT * FROM inventories WHERE id = :id")
    suspend fun getInventoryById(id: Int): Inventory?

    @Query("DELETE FROM inventories WHERE id = :id")
    suspend fun deleteInventoryById(id: Int)

    @Query("UPDATE inventories SET name = :name, short_name = :shortName, code = :code, description = :description, inventory_type = :type, history_date_at = :historyDateAt, in_progress_date_at = :inProgressDateAt, active_date_at = :activeDateAt, icon = :icon, state = :state WHERE id = :id")
    suspend fun updateInventory(
        id: Int,
        name: String,
        shortName: String,
        code: String,
        description: String,
        type: InventoryType?,
        historyDateAt: Date?,
        inProgressDateAt: Date,
        activeDateAt: Date?,
        icon: InventoryIcon?,
        state: InventoryState?
    )
}