package app.domain.invoicing.category

import java.util.Date

data class Category(
    val id: Int,
    val name: String,
    val shortName: String,
    val description: String,
    val image: ByteArray,
    val typeCategory: String,
    val createdAt: Date,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (id != other.id) return false
        if (name != other.name) return false
        if (shortName != other.shortName) return false
        if (description != other.description) return false
        if (!image.contentEquals(other.image)) return false
        if (typeCategory != other.typeCategory) return false
        if (createdAt != other.createdAt) return false

        return true
    }
    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + shortName.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + image.contentHashCode()
        result = 31 * result + typeCategory.hashCode()
        result = 31 * result + createdAt.hashCode()
        return result
    }
}