package app.domain.invoicing.converters

import android.net.Uri
import androidx.room.TypeConverter

class UriConverter {
    @TypeConverter
    fun fromUriToString(uri : Uri?) : String?{
        return uri?.toString()
    }

    @TypeConverter
    fun fromStringToUri(string : String?) : Uri?{
        return Uri.parse(string)
    }
}