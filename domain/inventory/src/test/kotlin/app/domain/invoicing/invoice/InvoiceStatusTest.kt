package app.domain.invoicing.invoice

import com.google.common.truth.Truth
import org.junit.Test

class InvoiceStatusTest {

    @Test
    fun `La clave del estado coincide`() {
        val status = InvoiceStatus.fromKey("pendiente")
        Truth
            .assertThat(status)
            .isEqualTo(InvoiceStatus.PENDING)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Error al usar key incorrecta`() {
        InvoiceStatus.fromKey("abierto")
    }
}