package app.domain.invoicing.databases


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import app.domain.invoicing.converters.DateTimeConverter
import app.domain.invoicing.converters.DependencyConverter
import app.domain.invoicing.converters.UriConverter
import app.domain.invoicing.dependency.Dependency
import app.domain.invoicing.dependency.DependencyDao
import app.domain.invoicing.section.Section
import app.domain.invoicing.section.SectionDao
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

/**
 * La base de datos de la aplicación inventory.
 *
 */
@Database(
    version = 1,
    entities = [Section::class, Dependency::class],
    exportSchema = false
)
@TypeConverters(DateTimeConverter::class, UriConverter::class, DependencyConverter::class)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun getSectionDao() : SectionDao
    abstract fun getDependencyDao() : DependencyDao

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

            }
        }
    }
}