package app.domain.invoicing.item

import org.junit.Test

class ItemTest {


    @Test
    fun `Error al crear articulo con nombre invalido`() {
        val name = ""
        val description = "Fresco y barato"
        val unitCost = 1200.0

        Item.create(
            id = ItemId(1),
            businessId = BusinessId("1"),
            name = name,
            rate = unitCost,
            description = description,
            isTaxable = true
        )
    }

    @Test
    fun `Error al crear articulo con precio invalido`() {
        val name = "Cocacola"
        val description = "Fresco y barato"
        val rate = -10.0

        Item.create(
            id = ItemId(1),
            businessId = BusinessId("1"),
            name = name,
            rate = rate,
            description = description,
            isTaxable = true
        )
    }
}