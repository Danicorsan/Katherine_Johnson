package app.domain.invoicing.invoice

import app.domain.invoicing.item.ItemId
import app.domain.invoicing.inventory.LineItem
import com.google.common.truth.Truth
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class LineItemTest {
    private fun testLine(
        rate: Double = 1.0,
        quantity: Int = 1,
        vat: Double = 19.0
    ): LineItem {
        return LineItem(
            itemId = ItemId(1),
            name = "Desarrollo Android",
            quantity = quantity,
            rate = rate,
            vat = app.domain.invoicing.dependency.ValueAddedTax(vat)
        )
    }

    @Test
    fun `Error al crear linea con precio negativo`() {
        assertThrows<RateIsNegative> {
            val negativeRate = -2.0
            testLine(negativeRate)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0])
    fun `Error al crear linea con cantidad menor o igual a cero`(invalidQuantity: Int) {
        assertThrows<QuantityIsLessThanOrEqualsToZero> {
            testLine(quantity = invalidQuantity)
        }
    }

    @Test
    fun `Calcular total de linea`() {
        val lineItem = testLine(2.0, 5, vat = 19.0)

        Truth
            .assertThat(lineItem.total)
            .isEqualTo(10.0)
        Truth
            .assertThat(lineItem.vatTotal)
            .isEqualTo(1.9)
        Truth
            .assertThat(lineItem.subtotal)
            .isEqualTo(8.1)

    }
}