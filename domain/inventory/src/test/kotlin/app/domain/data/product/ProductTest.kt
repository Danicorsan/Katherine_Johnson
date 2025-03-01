package app.domain.invoicing.product
/*
import app.domain.invoicing.category.Category
import app.domain.invoicing.product.complements.ProductAlarm
import app.domain.invoicing.product.complements.tags.Tags
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.Assert
import org.junit.Test
import java.util.Date

class ProductTest {

    @Test
    fun productShouldBeCreatedCorrectly() {
        // Arrange
        val now = Clock.System.now()
        val category = Category(1, "Electronics", "Elec", "Devices", null, Date(2,3,4), fungible = false )

        val product = Product(
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
            acquisitionDate = now,
            discontinuationDate = null,
            notes = "Latest model",
            tags = Tags(),
            minimunStock = 2u
        )

        // Assert
        Assert.assertEquals(1, product.id)
        Assert.assertEquals("LPT123", product.code)
        Assert.assertEquals("Laptop", product.name)
        Assert.assertEquals("LPT", product.shortName)
        Assert.assertEquals("High-end gaming laptop", product.description)
        Assert.assertEquals("SN12345", product.serialNumber)
        Assert.assertEquals("ModelX", product.modelCode)
        Assert.assertEquals("Gaming", product.productType)
        Assert.assertEquals(category, product.category)
        Assert.assertEquals("Computers", product.section)
        Assert.assertEquals(ProductState.new, product.state)
        Assert.assertEquals(5u, product.stock)
        Assert.assertEquals(1500.0, product.price, 0.0)
        Assert.assertEquals(now, product.acquisitionDate)
        Assert.assertNull(product.discontinuationDate)
        Assert.assertEquals("Latest model", product.notes)
        Assert.assertEquals(Tags(), product.tags)
        Assert.assertEquals(2u, product.minimunStock)
    }

    @Test
    fun productsWithSameIdShouldBeEqual() {
        val now = Clock.System.now()
        val category = Category(1, "Electronics", "Elec", "Devices", null, Date(2,3,4), fungible = false )

        val product1 = Product(1, "LPT123", "Laptop", "LPT", "Desc", "SN12345", "ModelX", "Gaming", category, "Computers", ProductState.new, 5u, 1500.0, null, now)
        val product2 = Product(1, "LPT123", "Laptop", "LPT", "Desc", "SN12345", "ModelX", "Gaming", category, "Computers", ProductState.new, 5u, 1500.0, null, now)

        // Assert
        Assert.assertEquals(product1, product2)
        Assert.assertEquals(product1.hashCode(), product2.hashCode())
    }

    @Test
    fun productsWithDifferentIdsShouldNotBeEqual() {
        val now = Clock.System.now()
        val category = Category(1, "Electronics", "Elec", "Devices", null, Date(2,3,4), fungible = false )

        val product1 = Product(1, "LPT123", "Laptop", "LPT", "Desc", "SN12345", "ModelX", "Gaming", category, "Computers", ProductState.new, 5u, 1500.0, null, now)
        val product2 = Product(2, "LPT456", "Mouse", "MSE", "Wireless", "SN54321", "ModelY", "Accessories", category, "Peripherals", ProductState.new, 10u, 50.0, null, now)

        // Assert
        Assert.assertNotEquals(product1, product2)
    }

    @Test
    fun productShouldTriggerMinimunStockAlarm() {
        val now = Clock.System.now()
        val category = Category(1, "Electronics", "Elec", "Devices", null, Date(2,3,4), fungible = false )

        val product = Product(
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
            stock = 1u,  // Stock bajo
            price = 1500.0,
            productImage = null,
            acquisitionDate = now,
            discontinuationDate = null,
            notes = "Latest model",
            tags = Tags(),
            minimunStock = 2u  // Stock mínimo
        )

        // Act
        val alarm = product.MinimunStockAlarm

        // Assert
        Assert.assertTrue( product.stock < alarm.product.minimunStock!!)
    }
}
*/