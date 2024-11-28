package com.example.inventory

import app.domain.invoicing.invoice.Invoices
import app.domain.invoicing.item.Items
import javax.inject.Inject


class CustomersMemory @Inject constructor() : Customers

class EstimatesMemory @Inject constructor() : Estimates

class InvoicesMemory @Inject constructor() : Invoices

class ItemsMemory @Inject constructor() : Items