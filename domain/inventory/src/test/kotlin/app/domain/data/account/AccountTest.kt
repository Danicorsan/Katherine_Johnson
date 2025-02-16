package app.domain.data.account

import app.domain.invoicing.account.Account
import app.domain.invoicing.account.Email
import app.domain.invoicing.account.Password
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class AccountTest {

    @Test
    fun `should create an account correctly`() {
        val email = Email("test@example.com")
        val password = Password("SecurePassword123!")
        val account = Account(
            id = 1,
            email = email,
            password = password,
            username = "testuser",
            name = "John",
            surname = "Doe",
            birthdate = "1990-01-01"
        )

        assertEquals(1, account.id)
        assertEquals(email, account.email)
        assertEquals(password, account.password)
        assertEquals("testuser", account.username)
        assertEquals("John", account.name)
        assertEquals("Doe", account.surname)
        assertEquals("1990-01-01", account.birthdate)
    }

    @Test
    fun `should consider accounts with same email as equal`() {
        val email = Email("test@example.com")
        val password1 = Password("Password1!")
        val password2 = Password("Password2!")  // Contraseñas diferentes

        val account1 = Account(1, email, password1, "user1", "Alice", "Smith", "1990-01-01")
        val account2 = Account(2, email, password2, "user2", "Bob", "Brown", "1995-05-05")

        assertEquals(account1, account2)  // Deben ser iguales porque tienen el mismo email
    }

    @Test
    fun `should generate same hashCode for accounts with same email`() {
        val email = Email("test@example.com")

        val account1 = Account(1, email, Password("pass1"), "user1", "Alice", "Smith", "1990-01-01")
        val account2 = Account(2, email, Password("pass2"), "user2", "Bob", "Brown", "1995-05-05")

        assertEquals(account1.hashCode(), account2.hashCode())  // Mismo hashCode por email
    }

    @Test
    fun `should consider accounts with different emails as different`() {
        val account1 = Account(1, Email("email1@example.com"), Password("pass1"), "user1", "Alice", "Smith", "1990-01-01")
        val account2 = Account(2, Email("email2@example.com"), Password("pass2"), "user2", "Bob", "Brown", "1995-05-05")

        assertNotEquals(account1, account2)  // Diferentes emails -> objetos diferentes
    }
}
