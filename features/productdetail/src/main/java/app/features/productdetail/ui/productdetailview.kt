package app.features.productdetail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun ProductDetailScreen(){
    val category = Category (
        id = 3,
        name = "Nombre Categoria",
        shortName = "cat",
        description = "",
        image = byteArrayOf(12,23,14).toString(),
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
    ProductDetailContent(Modifier, product)
}

@Composable
private fun ProductDetailContent(modifier: Modifier, product : Product){

    Column {
        Appbar(titleText = stringResource(R.string.title_appbar))
        ScrollableContentColumn(modifier = modifier ,product = product)
    }
}

@Composable
fun ScrollableContentColumn(modifier: Modifier = Modifier, product: Product){
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = Separations.Medium),
        horizontalAlignment = Alignment.CenterHorizontally,

        ){

        TitleText(modifier.padding(Separations.Medium), product.name)

        MakeInRow(
            {ProductDetailsColumn(
                dataType = stringResource(R.string.code_label), data = product.code
            )},
            {ProductDetailsColumn(
                dataType = stringResource(R.string.short_name_label), data = product.shortName
            )}
        )
        MediumSpace()
        MakeInRow(
            { ProductDetailsColumn(
                dataType = stringResource(R.string.category_label), data = product.category.name
            )},
            {ProductDetailsColumn(
                dataType = stringResource(R.string.serial_number_label), data = product.serialNumber
            )}
        )

        MediumSpace()
        MakeInRow(
            {ProductDetailsColumn(
                dataType = stringResource(R.string.model_code_label), data = product.modelCode
            )},
            {ProductDetailsColumn(
                dataType = stringResource(R.string.product_type_label), data = product.productType
            )}
        )
        MediumSpace()

        MakeInRow(
            {ProductDetailsColumn(
                dataType = stringResource(R.string.section_label), data = product.section
            )},
            {ProductDetailsColumn(
                dataType = stringResource(R.string.state_label), state = product.state
            )}
        )
        MediumSpace()
        MakeInRow(
            {ProductDetailsColumn(
                dataType = stringResource(R.string.stock_label), data = product.stock.toString()
            )},
            {ProductDetailsColumn(
                dataType = stringResource(R.string.price_label), data = product.price.toCurrency()
            )}
        )
        MediumSpace()
        MakeInRow(
            {ProductDetailsColumn(
                dataType = stringResource(R.string.acquisition_date_label), data = product.acquisitionDate.format()
            )},
            {ProductDetailsColumn(
                dataType = stringResource(R.string.discontinuation_date_label), data = product.discontinuationDate?.format()
            )}
        )
        MediumSpace()
        MakeInRow(
            {ProductDetailsColumn(
                dataType = stringResource(R.string.minimun_stock_label), data = product.minimunStock?.toString()
            )},
            {ProductDetailsColumn(
                dataType = stringResource(R.string.labels_label), data = if (product.tags.isEmpty()) null else product.tags.toString()
            )}
        )
        MediumSpace()
        ProductDetailsColumn(
            dataType = stringResource(R.string.description_label)
        ){
            val contenidoAMostrar = when(product.description){
                null -> stringResource(R.string.field_without_value)
                else -> product.description!!
            }
            TextoDescripcion(contenido = contenidoAMostrar)
        }
        MediumSpace()
        ProductDetailsColumn(
            dataType = stringResource(R.string.notes_label)
        ){
            FilaDeContenidoScrolleable(product.notes) {
                    nota -> ShowNote(nota)
            }
        }
    }
}

@Composable
private fun ShowNote(note: Note){
    Column {
        InformativeText(text = note.title)
        SmallSpace()
        InformativeText(text = note.title)
    }
}
