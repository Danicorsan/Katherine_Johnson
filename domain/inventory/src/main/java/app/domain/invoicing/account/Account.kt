package app.domain.invoicing.account

import androidx.room.Entity

/**
 * Account
 *
 * @property id
 * @property email
 * @property password
 * @property username
 * @property name
 * @property surname
 * @property birthdate
 * @constructor Create empty Account
 */
data class Account(
    val id:Int,
    val email: Email,
    val password: Password,
    val username: String,
    val name: String,
    val surname: String,
    val birthdate: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Account

        return email == other.email
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }
}