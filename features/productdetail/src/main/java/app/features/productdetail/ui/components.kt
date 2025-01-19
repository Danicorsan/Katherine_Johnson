package app.features.productdetail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.base.ui.composables.HighSpace
import app.base.ui.composables.MediumSpace
import app.base.ui.composables.Separations
import app.base.ui.composables.SmallSpace
import app.base.utils.format
import app.base.utils.toCurrency
import app.domain.invoicing.product.Product
import app.domain.invoicing.product.ProductState
import app.features.productdetail.R

@Composable
fun ScrollableContentColumn(product: Product){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = Separations.Medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        ){
        DetailsProductScreenTitle(product.name)
        MediumSpace()
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
            LargeTextBox(contenido = product.description)
        }
        MediumSpace()
        ProductDetailsColumn(
            dataType = stringResource(R.string.notes_label)
        ){
            LargeTextBox(contenido = product.notes)
        }
    }
}

@Composable
private fun ProductDetailsColumn(dataType : String, data : String?){
    Column (
        modifier = Modifier.width(175.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextTag(text = dataType)
        SmallSpace()
        data?.let {
            InformativeText(text = data)
        } ?: InformativeText(text = stringResource(R.string.field_without_value))
    }
}

@Composable
private fun ProductDetailsColumn(dataType : String, state : ProductState){
    val textoMostrar = when(state){
        ProductState.new -> stringResource(R.string.state_as_new)
        ProductState.modified -> stringResource(R.string.state_as_modified)
        ProductState.verified -> stringResource(R.string.state_as_verified)
    }

    ProductDetailsColumn(dataType = dataType, data = textoMostrar)
}

@Composable
private fun ProductDetailsColumn(modifier: Modifier = Modifier, dataType : String, content : @Composable () -> Unit){
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextTag(text = dataType)
        SmallSpace()
        content.invoke()
    }
}

@Composable
private fun MakeInRow(
    productDetail1 : @Composable () -> Unit,
    productDetail2: @Composable () -> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        productDetail1()
        HighSpace()
        productDetail2()
    }
}