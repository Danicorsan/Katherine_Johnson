package app.domain.accounts

import app.domain.invoicing.account.Account
import app.domain.invoicing.account.Email
import app.domain.invoicing.account.Password
import com.google.common.truth.Truth
import org.junit.jupiter.api.Test

internal class AccountTest {

    private val fixture = Account.create(
        email = Email(value = "m@m.m"),
        password = Password("Colombia1!"),
        settings = AccountSettings.Default
    )

    @Test
    internal fun `Cambiar nombre de perfil`() {
        val account = this.fixture

        Truth.assertThat(account.businessName()).isEmpty()

        account.renameBusiness("Develou")

        Truth.assertThat(account.businessName()).isEqualTo("Develou")
    }

    @Test
    internal fun `Cambiar direccion del negocio`() {
        val account = this.fixture

        Truth.assertThat(account.businessAddress()).isEmpty()

        account.changeBusinessAddress("Calle 123")

        Truth.assertThat(account.businessAddress()).isEqualTo("Calle 123")
    }

    @Test
    internal fun `Cambiar telefono del negocio`() {
        val account = this.fixture

        Truth.assertThat(account.businessPhone()).isEmpty()

        account.changeBusinessPhone("321 456 654")

        Truth.assertThat(account.businessPhone()).isEqualTo("321 456 654")
    }
}