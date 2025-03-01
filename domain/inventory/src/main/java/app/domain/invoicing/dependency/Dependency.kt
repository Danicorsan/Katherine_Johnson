package app.domain.invoicing.dependency

import android.net.Uri

data class Dependency(
    val id : Int,
    val name : String,
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