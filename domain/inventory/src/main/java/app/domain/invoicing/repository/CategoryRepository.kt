package app.domain.invoicing.repository

import app.domain.invoicing.category.Category
import app.domain.invoicing.category.CategoryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * Category repository
 *
 * @property categoryDao
 * @constructor Create empty Category repository
 */
class CategoryRepository(
    private val categoryDao: CategoryDao,
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
        withContext(Dispatchers.IO) {
            categoryDao.insertCategory(category)
        }
    }

    /**
     * Update category
     *
     * @param updatedCategory
     */
    suspend fun updateCategory(updatedCategory: Category) {
        withContext(Dispatchers.IO) {
            categoryDao.updateCategory(updatedCategory)
        }
    }

    /**
     * Delete category
     *
     * @param category
     */
    suspend fun deleteCategory(category: Category) {
        withContext(Dispatchers.IO) {
            categoryDao.deleteCategory(category)
        }
    }
}
