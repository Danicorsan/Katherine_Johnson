package app.domain.invoicing.section

import android.net.Uri
import app.domain.invoicing.dependency.Dependency
import kotlinx.datetime.Instant

data class Section(
    val id : Int,
    val name : String,
    val shortName : String,
    val belongedDependency : Dependency,
    val description : String = "",
    val image : Uri? = null,
    val releaseDate : Instant
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Section

        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}