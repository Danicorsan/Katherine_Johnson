package com.example.inventory.inject

import android.content.Context
import android.content.res.Resources
import app.domain.invoicing.databases.InventoryDatabase
import app.domain.invoicing.dependency.DependencyDao
import app.domain.invoicing.product.Product
import app.domain.invoicing.product.ProductDao
import app.domain.invoicing.repository.AccountRepository
import app.domain.invoicing.repository.CategoryRepository
import app.domain.invoicing.repository.InventoryRepository
import app.domain.invoicing.section.Section
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
    fun provideAccountRepository(): AccountRepository {
        return AccountRepository
    }

    /**
     * Provide category repository
     *
     * @return
     */
    @Provides
    @Singleton
    fun provideCategoryRepository(): CategoryRepository {
        return CategoryRepository
    }

    /**
     * Provide inventory repository
     *
     * @return
     */
    @Provides
    @Singleton
    fun provideInventoryRepository(): InventoryRepository {
        return InventoryRepository
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

    @Provides
    @Singleton
    fun provideProductDao(inventoryDatabase: InventoryDatabase) : ProductDao {
        return inventoryDatabase.getProductDao()
    }
}