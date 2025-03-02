package app.domain.invoicing.converters

import androidx.room.TypeConverter
import kotlinx.datetime.Instant
import java.time.LocalDate
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
    @TypeConverter
    fun fromLocalDate(localDate: LocalDate?): String? {
        return localDate?.toString()
    }

    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let { LocalDate.parse(it) }
    }
}