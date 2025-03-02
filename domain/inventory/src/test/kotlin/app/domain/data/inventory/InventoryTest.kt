package app.domain.data.inventory

import app.domain.invoicing.category.Category
import app.domain.invoicing.dependency.Dependency
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.inventory.InventoryIcon
import app.domain.invoicing.inventory.InventoryState
import app.domain.invoicing.inventory.InventoryType
import app.domain.invoicing.product.Product
import app.domain.invoicing.product.ProductState
import app.domain.invoicing.product.complements.tags.Tags
import app.domain.invoicing.section.Section
import kotlinx.datetime.Instant
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate
import java.util.Date

class InventoryTest {

    @Test
    fun inventoryShouldBeCreatedCorrectly() {
        // Arrange
        LocalDate.now()
        val category = Category(1, "Electronics", "Elec", "Devices", null, Date(2,3,4), fungible = false )

        listOf(
            Product(
                id = 1,
                code = "LPT123",
                name = "Laptop",
                shortName = "LPT",
                description = "High-end gaming laptop",
                serialNumber = "SN12345",
                modelCode = "ModelX",
                productType = "Gaming",
                category = category,
                section = Section(
                    id = 1,
                    name = "Computers",
                    shortName = "Comp",
                    belongedDependency = Dependency(
                        id = 1,
                        name = "Finance",
                        shortName = "FIN",
                        description = "Finance Department",
                        image = null
                    ),
                    description = "Section for computers",
                    image = null,
                    releaseDate = Instant.parse("2023-03-15T12:30:00Z")
                ),
                state = ProductState.new,
                stock = 5u,
                price = 1500.0,
                acquisitionDate =  Instant.parse("2023-03-15T12:30:00Z"),
                discontinuationDate = null,
                notes = "Latest model",
                tags = Tags(),
                minimunStock = 2u
            ),
            Product(
                id = 2,
                code = "MSE456",
                name = "Mouse",
                shortName = "MSE",
                description = "Wireless mouse",
                serialNumber = "SN54321",
                modelCode = "ModelY",
                productType = "Accessories",
                category = category,
                section = Section(
                    id = 2,
                    name = "Peripherals",
                    shortName = "Peri",
                    belongedDependency = Dependency(
                        id = 2,
                        name = "HR",
                        shortName = "HR",
                        description = "Human Resources Department",
                        image = null
                    ),
                    description = "Section for peripherals",
                    image = null,
                    releaseDate = Instant.parse("2023-03-16T12:30:00Z")
                ),
                state = ProductState.new,
                stock = 10u,
                price = 50.0,
                acquisitionDate =  Instant.parse("2023-03-15T12:30:00Z"),
                discontinuationDate = null,
                notes = "Ergonomic design",
                tags = Tags(),
                minimunStock = 5u
            )
        )

        val inventory = Inventory(
            id = 1,
            name = "Tech Inventory",
            description = "Inventory for IT department",
            inventoryType = InventoryType.SEMESTRAL,
            icon = InventoryIcon.MATERIALS,
            shortName = "Tech Inv",
            code = "TINV",
            historyDateAt = null,
            inProgressDateAt = Date(2,3,4),
            activeDateAt = null,
            state = InventoryState.IN_PROGRESS,
        )

        // Assert
        Assert.assertEquals(1, inventory.id)
        Assert.assertEquals("Tech Inventory", inventory.name)
        Assert.assertEquals("Inventory for IT department", inventory.description)
        Assert.assertEquals(InventoryType.SEMESTRAL, inventory.inventoryType)
        Assert.assertEquals(InventoryIcon.MATERIALS, inventory.icon)
    }

    @Test
    fun inventoryWithSameIdShouldBeEqual() {
        // Arrange
        val inventory1 = Inventory(
            id = 1,
            name = "Tech Inventory",
            description = "Inventory for IT department",
            inventoryType = InventoryType.SEMESTRAL,
            icon = InventoryIcon.MATERIALS,
            shortName = "Tech Inv",
            code = "TINV",
            historyDateAt = null,
            inProgressDateAt = Date(2,3,4),
            activeDateAt = null,
            state = InventoryState.IN_PROGRESS,
        )

        val inventory2 = Inventory(
            id = 1,
            name = "Tech Inventory",
            description = "Inventory for IT department",
            inventoryType = InventoryType.SEMESTRAL,
            icon = InventoryIcon.MATERIALS,
            shortName = "Tech Inv",
            code = "TINV",
            historyDateAt = null,
            inProgressDateAt = Date(2,3,4),
            activeDateAt = null,
            state = InventoryState.IN_PROGRESS,
        )

        // Assert
        Assert.assertEquals(inventory1, inventory2)
        Assert.assertEquals(inventory1.hashCode(), inventory2.hashCode())
    }

    @Test
    fun inventoryWithDifferentIdsShouldNotBeEqual() {
        // Arrange
        val inventory1 = Inventory(
            id = 1,
            name = "Tech Inventory",
            description = "Inventory for IT department",
            inventoryType = InventoryType.SEMESTRAL,
            icon = InventoryIcon.MATERIALS,
            shortName = "Tech Inv",
            code = "TINV",
            historyDateAt = null,
            inProgressDateAt = Date(2,3,4),
            activeDateAt = null,
            state = InventoryState.IN_PROGRESS,
        )

        val inventory2 = Inventory(
            id = 2,
            name = "Tech Inventory 2",
            description = "Inventory for IT department",
            inventoryType = InventoryType.SEMESTRAL,
            icon = InventoryIcon.MATERIALS,
            shortName = "Tech Inv 2",
            code = "TINV2",
            historyDateAt = null,
            inProgressDateAt = Date(2,3,4),
            activeDateAt = null,
            state = InventoryState.IN_PROGRESS,
        )

        // Assert
        Assert.assertNotEquals(inventory1, inventory2)
    }
}