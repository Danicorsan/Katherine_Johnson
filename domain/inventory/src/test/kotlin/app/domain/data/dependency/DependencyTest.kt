package app.domain.data.dependency

import app.domain.invoicing.dependency.Dependency
import org.junit.Assert
import org.junit.Test

class DependencyTest {

    @Test
    fun dependencyShouldBeCreatedCorrectly() {
        // Arrange
        val dependency = Dependency(
            id = 1,
            name = "IT Department",
            shortName = "IT",
            description = "Handles all IT-related tasks",
            image = "it_logo.png"
        )

        // Assert
        Assert.assertEquals(1, dependency.id)
        Assert.assertEquals("IT Department", dependency.name)
        Assert.assertEquals("IT", dependency.shortName)
        Assert.assertEquals("Handles all IT-related tasks", dependency.description)
        Assert.assertEquals("it_logo.png", dependency.image)
    }

    @Test
    fun dependencyShouldHandleNullImage() {
        // Arrange
        val dependency = Dependency(
            id = 2,
            name = "HR",
            shortName = "HR",
            description = "Human Resources Department",
            image = null // No image provided
        )

        // Assert
        Assert.assertEquals(2, dependency.id)
        Assert.assertEquals("HR", dependency.name)
        Assert.assertEquals("HR", dependency.shortName)
        Assert.assertEquals("Human Resources Department", dependency.description)
        Assert.assertNull(dependency.image)
    }

    @Test
    fun dependenciesWithSameIdShouldBeEqual() {
        // Arrange
        val dependency1 = Dependency(1, "Finance", "FIN", "Finance Department", null)
        val dependency2 = Dependency(1, "Finance", "FIN", "Finance Department", null)

        // Assert
        Assert.assertEquals(dependency1, dependency2)
        Assert.assertEquals(dependency1.hashCode(), dependency2.hashCode())
    }

    @Test
    fun dependenciesWithDifferentIdsShouldNotBeEqual() {
        // Arrange
        val dependency1 = Dependency(1, "Finance", "FIN", "Finance Department", null)
        val dependency2 = Dependency(2, "Finance", "FIN", "Finance Department", null)

        // Assert
        Assert.assertNotSame(dependency1, dependency2)
    }
}
