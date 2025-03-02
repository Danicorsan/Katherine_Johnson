package app.domain.invoicing.dependency

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dependency(
    @PrimaryKey
    val id : Int,
    val name : String,
    @ColumnInfo(name = "short_name")
    val shortName : String,
    val description : String,
    val image : Uri? = null
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Dependency

        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}