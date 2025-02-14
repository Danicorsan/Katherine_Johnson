package com.example.inventory.inject

import app.domain.invoicing.repository.AccountRepository
import app.domain.invoicing.repository.CategoryRepository
import app.domain.invoicing.repository.InventoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAccountRepository(): AccountRepository {
        return AccountRepository
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(): CategoryRepository {
        return CategoryRepository
    }

    @Provides
    @Singleton
    fun provideInventoryRepository(): InventoryRepository {
        return InventoryRepository
    }


}