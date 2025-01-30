package app.domain.invoicing.repository

import app.domain.invoicing.inventory.Inventory
import java.util.Date

fun initialiceInventoryDemo () : MutableList<Inventory>{
    val productlist = initializeProductsDemo().values.toList()
    return mutableListOf(
        Inventory(
            id = 1,
            name = "Electrónica",
            description = "Objetos de electrónica",
            items = listOf(productlist[1]),
            createdAt = Date(),
            updatedAt = Date()
        ),
        Inventory(
            id = 2,
            name = "Tecnología",
            description = "Objetos de tecnología",
            items = listOf(productlist[0]),
            createdAt = Date(),
            updatedAt = Date()
        )
    )
}