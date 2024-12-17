package app.features.productdetail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.composables.MediumSpace
import app.base.ui.composables.Separations
import app.base.ui.composables.SmallSpace
import app.base.utils.format
import app.base.utils.toCurrency
import app.domain.invoicing.category.Category
import app.domain.invoicing.product.Product
import app.domain.invoicing.product.ProductState
import app.domain.invoicing.product.complements.notes.Note
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
    )
    product.notes.add(Note("Un titulo genial", "Un cuerpo muy pobreeeeee"))
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