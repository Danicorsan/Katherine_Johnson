package app.domain.invoicing.converters

import androidx.room.TypeConverter
import kotlinx.datetime.Instant
import java.util.Date

class DateTimeConverter {
    @TypeConverter
    fun fromTimestampToDate(timestamp : Long?) : Date?{
        return timestamp?.let {
            Date(it)
        }
    }
    @TypeConverter
    fun dateToTimestamp(date: Date?) : Long?{
        return date?.time
    }
    @TypeConverter
    fun fromTimestampToInstant(timestamp : Long?) : Instant?{
        return timestamp?.let {
            Instant.fromEpochMilliseconds(timestamp)
        }
    }
    @TypeConverter
    fun instantToTimestamp(instant : Instant?) : Long?{
        return instant?.toEpochMilliseconds()
    }
}