package app.domain.invoicing.product

import app.base.utils.DateUtils
import app.base.utils.DateUtils.toKotlinInstant
import app.domain.invoicing.product.EstimateException.CustomerIsMandatory
import app.domain.invoicing.product.EstimateException.NoLinesInEstimate
import app.domain.invoicing.invoice.Invoice
import app.domain.invoicing.invoice.InvoiceId
import app.domain.invoicing.invoice.InvoiceStatus
import app.domain.invoicing.item.ItemId
import app.domain.invoicing.inventory.LineItem
import com.google.common.truth.Truth
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EstimateTest {

    private fun fixture(customerId: CustomerId = CustomerId(1)) = Estimate.create(
        estimateId = EstimateId(1000),
        businessId = BusinessId("1"),
        number = 1,
        customerId = customerId,
        lines = emptyList()
    )


    @Test
    fun `Error al abrir un estimado sin lineas`() {
        assertThrows<NoLinesInEstimate> {
            val estimate = fixture()
            estimate.open()
        }
    }


    @Test
    fun `Error al crear estimado sin especificar cliente`() {
        assertThrows<CustomerIsMandatory> {
            fixture(CustomerId.Unspecified)
        }
    }

    @Test
    fun `Crear factura vencida`() {
        val invoice = overdueInvoice()

        Truth
            .assertThat(invoice.status)
            .isEqualTo(InvoiceStatus.OVERDUE)
    }

    fun overdueInvoice(): Invoice {
        return Invoice.create(
            invoiceId = InvoiceId(1),
            businessId = BusinessId("1"),
            number = 4,
            status = InvoiceStatus.PENDING,
            customerId = CustomerId(1),
            issuedDate = DateUtils.parse("10/01/2023").toKotlinInstant(),
            dueDate = DateUtils.parse("30/01/2023").toKotlinInstant(),
            lines = listOf(
                LineItem(
                    itemId = ItemId(value = 1),
                    name = "I1",
                    quantity = 1,
                    rate = 2.0,
                    vat = null
                )
            )
        )
    }
}

