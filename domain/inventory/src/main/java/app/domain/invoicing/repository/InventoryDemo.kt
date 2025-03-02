package app.domain.invoicing.repository

import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.inventory.InventoryState
import app.domain.invoicing.inventory.InventoryType
import java.util.Date

fun initialiceInventoryDemo(): MutableList<Inventory> {
    initializeProductsDemo().values.toList()
    return mutableListOf(
        Inventory(
            id = 1,
            name = "Electrónica",
            description = "Objetos de electrónica",
            icon = InventoryIcon.ELECTRONICS,
            inventoryType = InventoryType.MONTHLY,
            shortName = "Elec",
            state = InventoryState.ACTIVE,
            activeDateAt = Date(),
            code = "ELEC-001",
            historyDateAt = Date(0),
            inProgressDateAt = Date(0)
        ),
        Inventory(
            id = 2,
            name = "Tecnología",
            description = "Objetos de tecnología",
            icon = InventoryIcon.TECHNOLOGY,
            inventoryType = InventoryType.SEMESTRAL,
            shortName = "Tech",
            state = InventoryState.ACTIVE,
            activeDateAt = Date(),
            code = "TECH-002",
            historyDateAt = Date(0),
            inProgressDateAt = Date(0)
        ),
        Inventory(
            id = 3,
            name = "Materiales",
            description = "Objetos de materiales",
            icon = InventoryIcon.MATERIALS,
            inventoryType = InventoryType.ANNUAL,
            shortName = "Mat",
            state = InventoryState.HISTORY,
            historyDateAt = Date(),
            code = "MAT-003",
            inProgressDateAt = Date(0),
            activeDateAt = Date(0)
        ),
        Inventory(
            id = 4,
            name = "Servicios",
            description = "Objetos de servicios",
            icon = InventoryIcon.SERVICES,
            inventoryType = InventoryType.WEEKLY,
            shortName = "Serv",
            state = InventoryState.IN_PROGRESS,
            inProgressDateAt = Date(),
            code = "SERV-004"
        ),
        Inventory(
            id = 5,
            name = "Muebles",
            description = "Objetos de muebles",
            icon = InventoryIcon.WAREHOUSE,
            inventoryType = InventoryType.PERMANENT,
            shortName = "Mueb",
            state = InventoryState.ACTIVE,
            activeDateAt = Date(),
            code = "MUEB-005",
            historyDateAt = Date(0),
            inProgressDateAt = Date(0)
        ),
        Inventory(
            id = 6,
            name = "Otros",
            description = "Objetos de otros",
            icon = InventoryIcon.NONE,
            inventoryType = InventoryType.TRIMESTRAL,
            shortName = "Otro",
            state = InventoryState.HISTORY,
            historyDateAt = Date(),
            code = "OTRO-006",
            inProgressDateAt = Date(0),
            activeDateAt = Date(0)
        ),
        Inventory(
            id = 7,
            name = "Oficina",
            description = "Objetos de oficina",
            icon = InventoryIcon.TECHNOLOGY,
            inventoryType = InventoryType.MONTHLY,
            shortName = "Ofic",
            state = InventoryState.ACTIVE,
            activeDateAt = Date(),
            code = "OFIC-007",
            historyDateAt = Date(0,),
            inProgressDateAt = Date(0)
        ),
        Inventory(
            id = 8,
            name = "Almacenamiento",
            description = "Objetos de almacenamiento",
            icon = InventoryIcon.MATERIALS,
            inventoryType = InventoryType.ANNUAL,
            shortName = "Almac",
            state = InventoryState.IN_PROGRESS,
            inProgressDateAt = Date(),
            code = "ALMAC-008"
        )
    )
}