package app.domain.invoicing.section

import com.google.common.truth.Truth
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class CustomerIdTest {

    @Test
    internal fun `A es diferente de B`() {
        val a = CustomerId(1)
        val b = CustomerId(2)
        Truth.assertThat(a).isNotEqualTo(b)
    }

    @Test
    internal fun `A es igual de B`() {
        val a = CustomerId(1)
        val b = CustomerId(1)
        Truth.assertThat(a).isEqualTo(b)
    }

    @Test
    internal fun `Id invalido`(){
        assertThrows<CustomerException.InvalidId> {
            CustomerId(-1)
        }
    }
}