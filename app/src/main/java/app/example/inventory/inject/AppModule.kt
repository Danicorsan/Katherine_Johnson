package app.example.inventory.inject

import android.content.Context
import android.content.res.Resources
import app.domain.invoicing.account.AccountDao
import app.domain.invoicing.dependency.DependencyDao
import app.domain.invoicing.category.CategoryDao
import app.domain.invoicing.databases.InventoryDatabase
import app.domain.invoicing.inventory.InventoryDAO
import app.domain.invoicing.repository.AccountRepository
import app.domain.invoicing.repository.CategoryRepository
import app.domain.invoicing.repository.InventoryRepository
import app.domain.invoicing.section.SectionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context : Context) : Context{
        return context
    }

    /**
     * Provide resources
     *
     * @param context
     * @return
     */
    @Provides
    @Singleton
    fun provideResources(@ApplicationContext context: Context): Resources {
        return context.resources
    }

    /**
     * Provide account repository
     *
     * @return
     */
    @Provides
    @Singleton
    fun provideAccountRepository(dao: AccountDao): AccountRepository {
        return AccountRepository(dao)
    }

    /**
     * Provide category repository
     *
     * @return
     */
    @Provides
    @Singleton
    fun provideCategoryRepository(dao: CategoryDao): CategoryRepository {
        return CategoryRepository(dao)
    }

    @Provides
    @Singleton
    fun provideCategoryDao(database: InventoryDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    @Singleton
    fun provideAccountDao(database: InventoryDatabase): AccountDao {
        return database.accountDao()
    }

    /**
     * Provide inventory repository
     *
     * @return
     */
    @Provides
    @Singleton
    fun provideInventoryRepository(dao: InventoryDAO): InventoryRepository {
        return InventoryRepository(dao)
    }
    @Provides
    @Singleton
    fun provideInventoryDao(database: InventoryDatabase): InventoryDAO {
        return database.inventoryDao()
    }

    @Provides
    @Singleton
    fun provideInventoryDatabase(context: Context) : InventoryDatabase{
        return InventoryDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideSectionDao(inventoryDatabase: InventoryDatabase) : SectionDao{
        return inventoryDatabase.getSectionDao()
    }

    @Provides
    @Singleton
    fun provideDependencyDao(inventoryDatabase: InventoryDatabase) : DependencyDao {
        return inventoryDatabase.getDependencyDao()
    }
/*
    @Provides
    @Singleton
    fun provideProductDao(inventoryDatabase: InventoryDatabase) : ProductDao {
        return inventoryDatabase.getProductDao()
    }*/
}