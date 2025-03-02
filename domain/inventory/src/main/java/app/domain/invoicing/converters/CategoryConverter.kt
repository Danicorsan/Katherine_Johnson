package app.domain.invoicing.converters

import androidx.room.TypeConverter
import app.domain.invoicing.category.Category
import java.util.Date

class CategoryConverter {
    @TypeConverter
    fun fromCategoryToId(category : Category?) : Int?{
        return 0 //todo no olvidar cambiarlo (esto es simulado)
    }

    @TypeConverter
    fun fromIdToCategory(categoryId : Int?) : Category?{
        return Category( //todo no olvidar cambiarlo
            id = 0,
            name = "Electronics",
            shortName = "ELEC",
            description = "Category for electronic products",
            image = null,
            createdAt = Date(),
            fungible = true
        )
    }
}