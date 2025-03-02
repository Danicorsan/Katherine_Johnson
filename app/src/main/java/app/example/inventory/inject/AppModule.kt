package app.example.inventory.inject

import android.content.Context
import android.content.res.Resources
import app.domain.invoicing.category.CategoryDao
import app.domain.invoicing.databases.InventoryDatabase
import app.domain.invoicing.repository.AccountRepository
import app.domain.invoicing.repository.CategoryRepository
import app.domain.invoicing.repository.InventoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
    fun provideDatabase(@ApplicationContext context: Context): InventoryDatabase {
        return InventoryDatabase.getDatabase(context)
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


}