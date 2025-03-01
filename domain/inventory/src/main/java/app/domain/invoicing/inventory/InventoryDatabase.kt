package app.domain.invoicing.inventory

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.domain.invoicing.converters.DateTimeConverter
import app.domain.invoicing.repository.initialiceInventoryDemo
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

@Database(
    entities = [Inventory::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateTimeConverter::class)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun inventoryDAO(): InventoryDAO

    companion object {
        private val executor = Executors.newSingleThreadExecutor()

        fun prepopulateDatabase(db: InventoryDatabase) {
            executor.execute {
                runBlocking {
                    val dao = db.inventoryDAO()
                    if (dao.getAllInventories().isEmpty()) {
                        initialiceInventoryDemo().forEach { dao.addInventory(it) }
                    }
                }
            }
        }
    }
}