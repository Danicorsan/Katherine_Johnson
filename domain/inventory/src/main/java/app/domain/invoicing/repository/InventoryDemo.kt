package app.domain.invoicing.repository

import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.inventory.InventoryState
import app.domain.invoicing.inventory.InventoryType
import java.time.LocalDateTime

fun initialiceInventoryDemo(): MutableList<Inventory> {
    initializeProductsDemo().values.toList()
    return mutableListOf(
        Inventory(
            id = 1,
            name = "Electrónica",
            description = "Objetos de electrónica",
            icon = InventoryIcon.ELECTRONICS,
            itemsCount = 10,
            inventoryType = InventoryType.MONTHLY,
            shortName = "Elec",
            state = InventoryState.ACTIVE,
            activeDateAt = LocalDateTime.now(),
            code = "ELEC-001"
        ),
        Inventory(
            id = 2,
            name = "Tecnología",
            description = "Objetos de tecnología",
            icon = InventoryIcon.TECHNOLOGY,
            itemsCount = 15,
            inventoryType = InventoryType.SEMESTRAL,
            shortName = "Tech",
            state = InventoryState.ACTIVE,
            activeDateAt = LocalDateTime.now(),
            code = "TECH-002"
        ),
        Inventory(
            id = 3,
            name = "Materiales",
            description = "Objetos de materiales",
            icon = InventoryIcon.MATERIALS,
            itemsCount = 20,
            inventoryType = InventoryType.ANNUAL,
            shortName = "Mat",
            state = InventoryState.HISTORY,
            historyDateAt = LocalDateTime.now(),
            code = "MAT-003"
        ),
        Inventory(
            id = 4,
            name = "Servicios",
            description = "Objetos de servicios",
            icon = InventoryIcon.SERVICES,
            itemsCount = 5,
            inventoryType = InventoryType.WEEKLY,
            shortName = "Serv",
            state = InventoryState.IN_PROGRESS,
            inProgressDateAt = LocalDateTime.now(),
            code = "SERV-004"
        ),
        Inventory(
            id = 5,
            name = "Muebles",
            description = "Objetos de muebles",
            icon = InventoryIcon.WAREHOUSE,
            itemsCount = 8,
            inventoryType = InventoryType.PERMANENT,
            shortName = "Mueb",
            state = InventoryState.ACTIVE,
            activeDateAt = LocalDateTime.now(),
            code = "MUEB-005"
        ),
        Inventory(
            id = 6,
            name = "Otros",
            description = "Objetos de otros",
            icon = InventoryIcon.NONE,
            itemsCount = 12,
            inventoryType = InventoryType.TRIMESTRAL,
            shortName = "Otro",
            state = InventoryState.HISTORY,
            historyDateAt = LocalDateTime.now(),
            code = "OTRO-006"
        ),
        Inventory(
            id = 7,
            name = "Oficina",
            description = "Objetos de oficina",
            icon = InventoryIcon.TECHNOLOGY,
            itemsCount = 18,
            inventoryType = InventoryType.MONTHLY,
            shortName = "Ofic",
            state = InventoryState.ACTIVE,
            activeDateAt = LocalDateTime.now(),
            code = "OFIC-007"
        ),
        Inventory(
            id = 8,
            name = "Almacenamiento",
            description = "Objetos de almacenamiento",
            icon = InventoryIcon.MATERIALS,
            itemsCount = 9,
            inventoryType = InventoryType.ANNUAL,
            shortName = "Almac",
            state = InventoryState.IN_PROGRESS,
            inProgressDateAt = LocalDateTime.now(),
            code = "ALMAC-008"
        )
    )
}