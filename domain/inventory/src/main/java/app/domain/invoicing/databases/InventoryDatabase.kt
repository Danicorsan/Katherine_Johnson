package app.domain.invoicing.databases


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import app.domain.invoicing.account.Account
import app.domain.invoicing.account.AccountDao
import app.domain.invoicing.category.Category
import app.domain.invoicing.category.CategoryDao
import app.domain.invoicing.converters.AccountConverters
import app.domain.invoicing.converters.CategoryConverter
import app.domain.invoicing.converters.DateTimeConverter
import app.domain.invoicing.converters.DependencyConverter
import app.domain.invoicing.converters.ProductConveters
import app.domain.invoicing.converters.SectionConverter
import app.domain.invoicing.converters.UriConverter
import app.domain.invoicing.dependency.Dependency
import app.domain.invoicing.dependency.DependencyDao
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryDAO
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.inventory.InventoryState
import app.domain.invoicing.inventory.InventoryType
import app.domain.invoicing.section.Section
import app.domain.invoicing.section.SectionDao
import kotlinx.coroutines.runBlocking
import java.util.Date
import java.util.concurrent.Executors

/**
 * La base de datos de la aplicación inventory.
 *
 */
@Database(
    version = 3, // Incrementa la versión
    entities = [Section::class, Dependency::class, Category::class, Inventory::class, Account::class],
    exportSchema = false
)
@TypeConverters(DateTimeConverter::class, UriConverter::class, DependencyConverter::class,
    SectionConverter::class, CategoryConverter::class, ProductConveters::class,
    AccountConverters::class)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun getSectionDao() : SectionDao
    abstract fun getDependencyDao() : DependencyDao
    //abstract fun getProductDao() : ProductDao
    abstract fun inventoryDao(): InventoryDAO
    abstract fun categoryDao(): CategoryDao
    abstract fun accountDao(): AccountDao

    companion object {
        @Volatile
        private var INSTANCE: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InventoryDatabase::class.java,
                    "inventory_database.db"
                )
                    .fallbackToDestructiveMigration() // Borra la BD si hay cambios en el esquema
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Executors.newSingleThreadExecutor().execute {
                                INSTANCE?.let { database ->
                                    prepopulateDatabase(database)
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private fun prepopulateDatabase(database: InventoryDatabase) {
            runBlocking {
                val categoryDao = database.categoryDao()
                categoryDao.insertAllCategory(
                    Category(
                        name = "Electronics",
                        shortName = "Electronics",
                        description = "Electronic items",
                        image = null,
                        createdAt = Date(),
                        fungible = true
                    )

                )
                val inventoryDao = database.inventoryDao()
                inventoryDao.addInventory(
                    Inventory(
                        name = "Inventory 1",
                        shortName = "Inv 1",
                        code = "INV001",
                        description = "Default inventory",
                        inventoryType = InventoryType.BIANNUAL,
                        inProgressDateAt = Date(),
                        icon = InventoryIcon.MATERIALS,
                        state = InventoryState.IN_PROGRESS,
                        historyDateAt = null,
                        activeDateAt = null,
                    )
                )
            }
        }
    }
}
