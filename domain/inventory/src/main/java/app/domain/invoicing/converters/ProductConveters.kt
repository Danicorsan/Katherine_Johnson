package app.domain.invoicing.converters

import androidx.room.TypeConverter
import app.domain.invoicing.product.ProductState
import app.domain.invoicing.product.complements.tags.Tags

class ProductConveters {
    @TypeConverter
    fun fromTagsToStringRepresentation(tags : Tags?) : String?{
        return tags?.toString()
    }

    @TypeConverter
    fun fromStringRepresentationToTags(tagsRepresentantion : String?) : Tags{
        return Tags.fromString(tagsRepresentantion)
    }

    @TypeConverter
    fun fromProductStateEnumToStringState(state : ProductState?) : String?{
        return state?.name
    }

    @TypeConverter
    fun fromStringStateToProductStateEnum(state : String?) : ProductState?{
        state?.let {
            return when(state) {
                ProductState.new.name -> ProductState.new
                ProductState.modified.name -> ProductState.modified
                ProductState.verified.name -> ProductState.verified
                else -> ProductState.new
            }
        }
        return null
    }
}