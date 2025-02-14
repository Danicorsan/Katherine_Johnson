package app.domain.data.category

import app.domain.invoicing.category.Category
import app.domain.invoicing.category.TypeCategory
import org.junit.Assert
import org.junit.Test
import java.util.Date

class CategoryTest {
    @Test
    fun categoryShouldBeCreatedCorrectly() {
        // Arrange
        val now = Date()
        val category = Category(
            1,
            "Electronics",
            "Elec",
            "Devices and gadgets",
            null,
            now,
            TypeCategory.BASICOS,
            true
        )

        // Assert
        Assert.assertEquals(1, category.id.toLong())
        Assert.assertEquals("Electronics", category.name)
        Assert.assertEquals("Elec", category.shortName)
        Assert.assertEquals("Devices and gadgets", category.description)
        Assert.assertNull(category.image)
        Assert.assertEquals(now, category.createdAt)
        Assert.assertEquals(TypeCategory.BASICOS, category.typeCategory)
        Assert.assertTrue(category.fungible)
    }

    @Test
    fun categoriesWithSameIdShouldBeEqual() {
        val now = Date()
        val category1 = Category(1, "Tech", "T", "Desc", null, now, TypeCategory.BASICOS, true)
        val category2 = Category(1, "Tech", "T", "Desc", null, now, TypeCategory.BASICOS, true)

        // Assert
        Assert.assertEquals(category1, category2)
        Assert.assertEquals(category1.hashCode().toLong(), category2.hashCode().toLong())
    }

    @Test
    fun categoriesWithDifferentIdsShouldNotBeEqual() {
        val now = Date()
        val category1 = Category(1, "Tech", "T", "Desc", null, now, TypeCategory.BASICOS, true)
        val category2 = Category(2, "Tech", "T", "Desc", null, now, TypeCategory.BASICOS, true)

        // Assert
        Assert.assertNotEquals(category1, category2)
    }
}