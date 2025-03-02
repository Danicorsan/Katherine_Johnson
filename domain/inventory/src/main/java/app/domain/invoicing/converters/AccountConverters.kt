package app.domain.invoicing.converters

import app.domain.invoicing.account.Email
import app.domain.invoicing.account.Password
import androidx.room.TypeConverter

class AccountConverters {

    @TypeConverter
    fun fromEmail(email: Email): String {
        return email.value
    }

    @TypeConverter
    fun toEmail(value: String): Email {
        return Email(value)
    }

    @TypeConverter
    fun fromPassword(password: Password): String {
        return password.value
    }

    @TypeConverter
    fun toPassword(value: String): Password {
        return Password(value)
    }
}
