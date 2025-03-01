package app.domain.invoicing.category

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Category
 *
 * @property id
 * @property name
 * @property shortName
 * @property description
 * @property image
 * @property createdAt
 * @property typeCategory
 * @property fungible
 * @constructor Create empty Category
 */
@Entity(tableName = "Categoria")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val shortName: String,
    val description: String,
    val image: Uri?,
    val createdAt: Date,
    val typeCategory: TypeCategory = TypeCategory.BASICOS,
    val fungible: Boolean
)