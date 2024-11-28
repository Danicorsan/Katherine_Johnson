package app.domain.accounts

import app.domain.invoicing.account.Email
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class EmailTest {
    @Test
    fun `Error si el email no tiene formato RFC`() {
        assertThrows<InvalidEmailFormat> {
            Email("@mail.com")
        }
    }
}