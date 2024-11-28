package app.domain.invoicing.invoice

import app.domain.invoicing.invoice.InvoiceException.AtLeastOneItem
import app.domain.invoicing.item.ItemId
import app.domain.invoicing.inventory.LineItem
import com.google.common.truth.Truth
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InvoiceTest {

    private fun fixture(linesError: Boolean = false): Invoice {
        val lines = if (linesError) emptyList()
        else listOf(
            LineItem(
                itemId = ItemId(1),
                name = "Producto #1",
                quantity = 1,
                rate = 1.0,
                vat = app.domain.invoicing.dependency.ValueAddedTax(19.0)
            )
        )
        return Invoice.create(
            invoiceId = InvoiceId(1001),
            businessId = BusinessId("1"),
            number = 1,
            status = InvoiceStatus.PENDING,
            customerId = CustomerId(1001),
            lines = lines
        )
    }

    @Test
    fun `Marcar como pagada`() {
        val pendingInvoice = fixture()
        pendingInvoice.markAsPaid()
        Truth.assertThat(pendingInvoice.isPaid()).isTrue()
    }

    @Test
    fun `Error al crear factura sin lineas`() {
        assertThrows<AtLeastOneItem> {
            fixture(true)
        }
    }

    @Test
    fun `Calcular total`() {
        val invoice = fixture()

        Truth
            .assertThat(invoice.total())
            .isEqualTo(1.0)
        Truth
            .assertThat(invoice.taxTotal())
            .isEqualTo(0.19)
        Truth
            .assertThat(invoice.subTotal())
            .isEqualTo(0.81)
    }
}


