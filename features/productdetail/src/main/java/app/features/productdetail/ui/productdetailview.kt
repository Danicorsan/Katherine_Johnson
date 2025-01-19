package app.features.productdetail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.domain.invoicing.category.Category
import app.domain.invoicing.product.Product
import app.domain.invoicing.product.ProductState
import app.features.productdetail.R
import kotlinx.datetime.Instant
import java.util.Date

@Preview(showBackground = true)
@Composable
fun ProductDetailScreen(
    onGoBackNav: () -> Unit = {}
){
    val category = Category (
        id = 3,
        name = "Nombre Categoria",
        shortName = "cat",
        description = "",
        image = "Una imagen",
        createdAt = Date(342422424),
        fungible = false
    )
    val section = "Nombre Seccion"
    val product : Product = Product(
        code = "dependenciaSeccion3",
        name = "Esponja duradera max",
        shortName = "Esponja",
        description = "Algo descripcionfffffffffffffffffffffffffffffffffffffffff",
        serialNumber = "424D4234CD",
        modelCode = "97878DFWEFw",
        productType = "Aseo e Higiene",
        category = category,
        section = section,
        state = ProductState.new,
        stock = 32u,
        price = 23.44,
        acquisitionDate = Instant.parse("2023-06-10T15:00:00Z"),
        notes = "Algunas notas de prueba\n¿Que más?"
    )
    ProductDetailContent(
        product = product,
        onGoBackNav = onGoBackNav
        )
}

@Composable
private fun ProductDetailContent(
    product : Product,
    onGoBackNav : () -> Unit
){
    Scaffold(
        topBar = @Composable {
            Appbar(
                titleText = stringResource(R.string.title_appbar),
                navigationAction = onGoBackNav
            )
        }
    ){ contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            ScrollableContentColumn(product = product)
        }
    }
}