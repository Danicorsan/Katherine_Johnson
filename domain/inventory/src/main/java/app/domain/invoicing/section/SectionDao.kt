package app.domain.invoicing.section

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: Section)

    @Delete
    suspend fun delete(account: Section)

    @Update
    suspend fun update(account: Section)

    @Query("SELECT * FROM Section")
    suspend fun getAllSection() : List<Section>

    @Query("SELECT * FROM Section WHERE id = :sectionId")
    suspend fun getSectionById(sectionId : Int?) : Section?
}