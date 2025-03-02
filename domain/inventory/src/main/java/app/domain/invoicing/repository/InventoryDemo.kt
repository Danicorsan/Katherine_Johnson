package app.domain.invoicing.repository

import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.inventory.InventoryType
import java.time.LocalDate

fun initialiceInventoryDemo(): MutableList<Inventory> {
    initializeProductsDemo().values.toList()
    return mutableListOf(
        Inventory(
            id = 1,
            name = "Electrónica",
            description = "Objetos de electrónica",
            createdAt = LocalDate.of(2022, 1, 1),
            updatedAt = LocalDate.of(2022, 1, 1),
            icon = InventoryIcon.ELECTRONICS,
            itemsCount = 1,
            inventoryType = InventoryType.SEMESTRAL
        ),
        Inventory(
            id = 2,
            name = "Tecnología",
            description = "Objetos de tecnología",
            createdAt = LocalDate.now(),
            updatedAt = LocalDate.now(),
            icon = InventoryIcon.TECHNOLOGY,
            itemsCount = 1,
            inventoryType = InventoryType.TRIMESTRAL
        ),
        Inventory(
            id = 3,
            name = "Materiales",
            description = "Objetos de materiales",
            createdAt = LocalDate.now(),
            updatedAt = LocalDate.now(),
            icon = InventoryIcon.MATERIALS,
            itemsCount = 1,
            inventoryType = InventoryType.PERMANENT
        ),
        Inventory(
            id = 4,
            name = "Servicios",
            description = "Objetos de servicios",
            createdAt = LocalDate.now(),
            updatedAt = LocalDate.now(),
            icon = InventoryIcon.SERVICES,
            itemsCount = 1,
            inventoryType = InventoryType.ANNUAL
        ),
        Inventory(
            id = 5,
            name = "Muebles",
            description = "Objetos de muebles",
            createdAt = LocalDate.now(),
            updatedAt = LocalDate.now(),
            icon = InventoryIcon.WAREHOUSE,
            itemsCount = 1,
            inventoryType = InventoryType.PERMANENT
        ),
        Inventory(
            id = 6,
            name = "Otros",
            description = "Objetos de otros",
            createdAt = LocalDate.now(),
            updatedAt = LocalDate.now(),
            icon = InventoryIcon.NONE,
            itemsCount = 1,
            inventoryType = InventoryType.WEEKLY
        ),
        Inventory(
            id = 7,
            name = "Oficina",
            description = "Objetos de oficina",
            createdAt = LocalDate.now(),
            updatedAt = LocalDate.now(),
            icon = InventoryIcon.TECHNOLOGY,
            itemsCount = 1,
            inventoryType = InventoryType.PERMANENT
        ),
        Inventory(
            id = 8,
            name = "Almacenamiento",
            description = "Objetos de almacenamiento",
            createdAt = LocalDate.now(),
            updatedAt = LocalDate.now(),
            icon = InventoryIcon.MATERIALS,
            itemsCount = 1,
            inventoryType = InventoryType.MONTHLY
        )
    )
}