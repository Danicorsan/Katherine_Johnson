package app.domain.data.inventory

import app.domain.invoicing.category.Category
import app.domain.invoicing.inventory.Inventory
import app.domain.invoicing.product.Product
import app.domain.invoicing.product.ProductState
import app.domain.invoicing.product.complements.tags.Tags
import org.junit.Assert
import org.junit.Test
import java.util.Date

class InventoryTest {

    @Test
    fun inventoryShouldBeCreatedCorrectly() {
        // Arrange
        val now = Date()
        val category = Category(1, "Electronics", "Elec", "Devices", null, Date(2,3,4), fungible = false )

        val products = listOf(
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
                section = "Computers",
                state = ProductState.new,
                stock = 5u,
                price = 1500.0,
                productImage = null,
                acquisitionDate =  kotlinx.datetime.Instant.parse("2023-03-15T12:30:00Z"),
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
                section = "Peripherals",
                state = ProductState.new,
                stock = 10u,
                price = 50.0,
                productImage = null,
                acquisitionDate =  kotlinx.datetime.Instant.parse("2023-03-15T12:30:00Z"),
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
            items = products,
            createdAt = now
        )

        // Assert
        Assert.assertEquals(1, inventory.id)
        Assert.assertEquals("Tech Inventory", inventory.name)
        Assert.assertEquals("Inventory for IT department", inventory.description)
        Assert.assertEquals(products, inventory.items)
        Assert.assertEquals(now, inventory.createdAt)
    }
}
