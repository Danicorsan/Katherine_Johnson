package app.domain.invoicing.category

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Category dao
 *
 * @constructor Create empty Category dao
 */
@Dao
interface CategoryDao {

    /**
     * Get categories
     *
     * @return
     */
    @Query("SELECT * FROM CATEGORIA")
    fun getCategories(): Flow<List<Category>>

    /**
     * Get category from id
     *
     * @param categoryId
     * @return
     */
    @Query("SELECT * FROM CATEGORIA where id = :categoryId")
    fun getCategoryFromId(categoryId: Int): Flow<Category>

    /**
     * Insert category
     *
     * @param category
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    /**
     * Insert all category
     *
     * @param category
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCategory(category: Category)

    /**
     * Delete category
     *
     * @param category
     */
    @Delete
    suspend fun deleteCategory(category: Category)

    /**
     * Update category
     *
     * @param category
     */
    @Update
    suspend fun updateCategory(category: Category)

}