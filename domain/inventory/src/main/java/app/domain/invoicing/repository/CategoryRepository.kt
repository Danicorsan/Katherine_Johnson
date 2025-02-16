package app.domain.invoicing.repository

import app.domain.invoicing.category.Category
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.util.Date
import javax.imageio.ImageIO
import javax.swing.ImageIcon

/**
 * Category repository
 *
 * @constructor Create empty Category repository
 */
object CategoryRepository {

    // Simulación de un dataset en memoria
    private val dataSet: MutableList<Category> = mutableListOf()

    init {
        initialize()
    }

    private fun initialize() {
        dataSet.add(
            Category(
                id = 1,
                name = "Electronics",
                shortName = "ELEC",
                description = "Category for electronic products",
                image = null,
                createdAt = Date(),
                fungible = true
            )
        )
        dataSet.add(
            Category(
                id = 2,
                name = "Books",
                shortName = "BOOK",
                description = "Category for books and literature",
                image = null,
                createdAt = Date(),
                fungible = false
            )
        )
        dataSet.add(
            Category(
                id = 3,
                name = "Services",
                shortName = "SERV",
                description = "Category for service offerings",
                image = null,
                createdAt = Date(),
                fungible = false
            )
        )
    }

    /**
     * Get all categories
     *
     * @return
     */
    fun getAllCategories(): List<Category> = dataSet

    /**
     * Get category by id
     *
     * @param id
     * @return
     */
    fun getCategoryById(id: Int): Category? {
        return dataSet.find { it.id == id }
    }

    /**
     * Add category
     *
     * @param category
     * @return
     */
    fun addCategory(category: Category): Boolean {
        if (dataSet.any { it.id == category.id }) return false // ID duplicado
        dataSet.add(category)
        return true
    }

    /**
     * Update category
     *
     * @param updatedCategory
     * @return
     */
    fun updateCategory(updatedCategory: Category): Boolean {
        val index = dataSet.indexOfFirst { it.id == updatedCategory.id }
        if (index == -1) return false
        dataSet[index] = updatedCategory
        return true
    }

    /**
     * Delete category
     *
     * @param id
     * @return
     */
    fun deleteCategory(id: Int): Boolean {
        return dataSet.removeIf { it.id == id }
    }

    /**
     * Image to byte array
     *
     * @param imagePath
     * @return
     */
    private fun imageToByteArray(imagePath: String): ByteArray {
        val image = ImageIcon(imagePath).image
        val bufferedImage = BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB)
        val graphics = bufferedImage.createGraphics()
        graphics.drawImage(image, 0, 0, null)
        graphics.dispose()

        val byteArrayOutputStream = ByteArrayOutputStream()
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream)
        return byteArrayOutputStream.toByteArray()
    }

}