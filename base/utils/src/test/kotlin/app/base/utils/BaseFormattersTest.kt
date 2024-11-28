package app.base.utils

import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BaseFormattersTest{
    @Test
    internal fun `Debe formatear el instante`() {
        val instant = Instant.parse("2010-06-01T22:19:44.475Z")

        val formatted = instant.format()

        assertEquals("01/06/2010", formatted)
    }
}