package app.domain.invoicing.repository

import app.domain.invoicing.category.Category
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.util.Date
import javax.imageio.ImageIO
import javax.swing.ImageIcon

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
                image = imageToByteArray("path/to/electronics.png"),
                typeCategory = "Goods",
                createdAt = Date()
            )
        )
        dataSet.add(
            Category(
                id = 2,
                name = "Books",
                shortName = "BOOK",
                description = "Category for books and literature",
                image = imageToByteArray("path/to/books.png"),
                typeCategory = "Goods",
                createdAt = Date()
            )
        )
        dataSet.add(
            Category(
                id = 3,
                name = "Services",
                shortName = "SERV",
                description = "Category for service offerings",
                image = imageToByteArray("path/to/services.png"),
                typeCategory = "Services",
                createdAt = Date()
            )
        )
    }

    // Método para obtener todas las categorías
    fun getAllCategories(): List<Category> = dataSet

    // Método para obtener una categoría por ID
    fun getCategoryById(id: Int): Category? {
        return dataSet.find { it.id == id }
    }

    // Método para agregar una nueva categoría
    fun addCategory(category: Category): Boolean {
        if (dataSet.any { it.id == category.id }) return false // ID duplicado
        dataSet.add(category)
        return true
    }

    // Método para actualizar una categoría existente
    fun updateCategory(updatedCategory: Category): Boolean {
        val index = dataSet.indexOfFirst { it.id == updatedCategory.id }
        if (index == -1) return false
        dataSet[index] = updatedCategory
        return true
    }

    // Método para eliminar una categoría por ID
    fun deleteCategory(id: Int): Boolean {
        return dataSet.removeIf { it.id == id }
    }

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