package app.domain.invoicing.converters

import android.net.Uri
import androidx.room.TypeConverter

class UriConverter {
    @TypeConverter
    fun fromUri(uri: Uri?): String? {
        return uri?.toString()  // Si URI es null, se devolverá null
    }

    // Convierte un String a URI
    @TypeConverter
    fun toUri(uriString: String?): Uri? {
        return uriString?.let { Uri.parse(it) }  // Si uriString es null, se devolverá null
    }
}