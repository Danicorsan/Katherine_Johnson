package app.domain.accounts

import app.domain.invoicing.account.Password
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PasswordTest{

    @Test
    fun `Error si la longitud es invalida`(){
        assertThrows<InvalidPasswordLength> {
            Password("1234")
        }
    }

    @Test
    fun `Error si el formato es invalido`(){
        assertThrows<InvalidPasswordFormat> {
            Password("12345678")
        }
    }
}