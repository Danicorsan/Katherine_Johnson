package app.domain.invoicing.product.complements.tags

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class Tag(
    @ColumnInfo(name = "tag")
    val content : String,
){
    override fun toString(): String {
        return content
    }
}