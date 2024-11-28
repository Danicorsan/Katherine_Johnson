package com.example.inventory

import app.domain.invoicing.invoice.Invoices
import app.domain.invoicing.item.Items
import app.domain.invoicing.category.CustomerListQuery
import app.domain.invoicing.category.EstimateListQuery
import app.domain.invoicing.category.InvoiceListQuery
import app.domain.invoicing.category.ItemListQuery
import app.infrastructure.firebase.FirebaseBinds
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [FirebaseBinds::class]
)
abstract class FakeDataModule {

    @Binds
    abstract fun items(items: ItemsMemory): Items

    @Binds
    abstract fun customers(customersMemory: CustomersMemory): Customers

    @Binds
    abstract fun estimates(estimates: EstimatesMemory): Estimates

    @Binds
    abstract fun invoices(invoices: InvoicesMemory): Invoices

    @Binds
    abstract fun itemList(query: ItemListMemory): ItemListQuery

    @Binds
    abstract fun customerList(query: CustomerListMemory): CustomerListQuery

    @Binds
    abstract fun estimateList(query: EstimateListMemory): EstimateListQuery

    @Binds
    abstract fun invoicesList(query: InvoiceListMemory): InvoiceListQuery
}