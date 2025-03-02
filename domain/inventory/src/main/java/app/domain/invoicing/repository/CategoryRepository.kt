package app.domain.invoicing.repository

import app.domain.invoicing.category.Category
import app.domain.invoicing.category.CategoryDao
import kotlinx.coroutines.flow.Flow

/**
 * Category repository
 *
 * @property categoryDao
 * @constructor Create empty Category repository
 */
class CategoryRepository(
    private val categoryDao: CategoryDao
) {

    /**
     * Get all categories
     *
     * @return
     */
    fun getAllCategories(): Flow<List<Category>> = categoryDao.getCategories()

    /**
     * Get category by id
     *
     * @param id
     * @return
     */
    fun getCategoryById(id: Int): Flow<Category> = categoryDao.getCategoryFromId(id)

    /**
     * Add category
     *
     * @param category
     */
    suspend fun addCategory(category: Category) {
        categoryDao.insertCategory(category)
    }

    /**
     * Update category
     *
     * @param updatedCategory
     */
    suspend fun updateCategory(updatedCategory: Category) {
        categoryDao.updateCategory(updatedCategory)
    }

    /**
     * Delete category
     *
     * @param category
     */
    suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category)
    }
}
