package app.domain.invoicing.dependency

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DependencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: Dependency)

    @Delete
    suspend fun delete(account: Dependency)

    @Update
    suspend fun update(account: Dependency)

    @Query("SELECT * FROM Dependency")
    suspend fun getAllSection() : List<Dependency>

    @Query("SELECT * FROM Dependency WHERE id = :idDependency")
    suspend fun getDependencyById(idDependency : Int) : Dependency?

}