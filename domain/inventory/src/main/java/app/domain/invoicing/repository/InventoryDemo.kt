package app.domain.invoicing.repository

import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon
import java.util.Date

fun initialiceInventoryDemo(): MutableList<Inventory> {
    val productlist = initializeProductsDemo().values.toList()
    return mutableListOf(
        Inventory(
            id = 1,
            name = "Electrónica",
            description = "Objetos de electrónica",
            items = listOf(productlist[1]),
            createdAt = Date(),
            updatedAt = Date(),
            icon = InventoryIcon.ELECTRONICS
        ),
        Inventory(
            id = 2,
            name = "Tecnología",
            description = "Objetos de tecnología",
            items = listOf(productlist[0]),
            createdAt = Date(),
            updatedAt = Date(),
            icon = InventoryIcon.TECHNOLOGY
        ),
        Inventory(
            id = 3,
            name = "Materiales",
            description = "Objetos de materiales",
            items = listOf(productlist[2]),
            createdAt = Date(),
            updatedAt = Date(),
            icon = InventoryIcon.MATERIALS
        ),
        Inventory(
            id = 4,
            name = "Servicios",
            description = "Objetos de servicios",
            items = listOf(productlist[3]),
            createdAt = Date(),
            updatedAt = Date(),
            icon = InventoryIcon.SERVICES
        ),
        Inventory(
            id = 5,
            name = "Muebles",
            description = "Objetos de muebles",
            items = listOf(productlist[4]),
            createdAt = Date(),
            updatedAt = Date(),
            icon = InventoryIcon.WAREHOUSE
        ),
        Inventory(
            id = 6,
            name = "Otros",
            description = "Objetos de otros",
            items = listOf(productlist[5]),
            createdAt = Date(),
            updatedAt = Date(),
            icon = InventoryIcon.NONE
        ),
        Inventory(
            id = 7,
            name = "Oficina",
            description = "Objetos de oficina",
            items = listOf(productlist[6]),
            createdAt = Date(),
            updatedAt = Date(),
            icon = InventoryIcon.TECHNOLOGY
        ),
        Inventory(
            id = 8,
            name = "Almacenamiento",
            description = "Objetos de almacenamiento",
            items = listOf(productlist[6]),
            createdAt = Date(),
            updatedAt = Date(),
            icon = InventoryIcon.MATERIALS
        )
    )
}