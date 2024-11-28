package com.example.inventory

import app.domain.invoicing.category.CustomerListQuery
import app.domain.invoicing.category.EstimateListQuery
import app.domain.invoicing.category.InvoiceListQuery
import app.domain.invoicing.category.ItemListQuery
import javax.inject.Inject

class CustomerListMemory @Inject constructor() : CustomerListQuery
class ItemListMemory @Inject constructor() : ItemListQuery
class InvoiceListMemory @Inject constructor() : InvoiceListQuery
class EstimateListMemory @Inject constructor() : EstimateListQuery