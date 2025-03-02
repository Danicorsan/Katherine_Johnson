package app.domain.invoicing.product
/*
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Update
    suspend fun update(product: Product)

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts() : List<Product>

    @Query("SELECT * FROM Product WHERE id = :productId")
    suspend fun getProductById(productId : Int)
}*/