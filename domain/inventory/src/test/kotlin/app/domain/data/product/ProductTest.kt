package app.domain.invoicing.product

import app.domain.invoicing.category.Category
import app.domain.invoicing.dependency.Dependency
import app.domain.invoicing.product.complements.tags.Tags
import app.domain.invoicing.section.Section
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.Assert
import org.junit.Test
import java.util.Date

class ProductTest {

    private fun createSection() = Section(
        id = 1,
        name = "seccion",
        shortName = "nombreCorto",
        belongedDependency = Dependency(
            id = 1,
            description = "Descripccion",
            name = "nombreDescripción",
            shortName = "nombreDesc"
        ),
        description = "fjksfs",
        releaseDate = Instant.fromEpochMilliseconds(0)
    )

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
            section = createSection(),
            state = ProductState.new,
            stock = 5u,
            price = 1500.0,
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
        Assert.assertTrue(category == product.category)
        Assert.assertEquals(createSection(), product.section)
        Assert.assertEquals(ProductState.new, product.state)
        Assert.assertEquals(5u, product.stock)
        Assert.assertEquals(1500.0, product.price, 0.0)
        Assert.assertEquals(now, product.acquisitionDate)
        Assert.assertNull(product.discontinuationDate)
        Assert.assertEquals("Latest model", product.notes)
        Assert.assertNotNull(product.tags)
        Assert.assertEquals(2u, product.minimunStock)
    }

    @Test
    fun productsWithSameNameShouldBeEqual() {
        val now = Clock.System.now()
        val category = Category(1, "Electronics", "Elec", "Devices", null, Date(2,3,4), fungible = false )

        val product1 = Product(1, "LPT123", "Laptop", "LPT", "Desc", "SN12345", "ModelX", "Gaming", category, createSection(), ProductState.new, stock = 5u, price = 1500.0, notes = "", acquisitionDate = Instant.fromEpochMilliseconds(0))
        val product2 = Product(1, "LPT123", "Laptop", "LPT", "Desc", "SN12345", "ModelX", "Gaming", category, createSection(), ProductState.new,  stock = 5u, price =  1500.0, notes = "", acquisitionDate = Instant.fromEpochMilliseconds(0))

        // Assert
        Assert.assertTrue(product1 == product2)
        Assert.assertEquals(product1.hashCode(), product2.hashCode())
    }
}
