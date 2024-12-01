package app.features.productdetail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.base.ui.composables.SmallSpace
import app.domain.invoicing.product.EstadoProducto
import app.features.productdetail.R

@Composable
fun DetallesProductoColumna(modifier: Modifier = Modifier, tipoDato : String, dato : String?){
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextoEtiqueta(texto = tipoDato)
        SmallSpace()
        dato?.let {
            TextoInformativo(texto = dato)
        } ?: TextoInformativo(texto = stringResource(R.string.campo_sin_valor))
    }
}

@Composable
fun DetallesProductoColumna(modifier: Modifier = Modifier, tipoDato : String, estado : EstadoProducto){
    val textoMostrar = when(estado){
        EstadoProducto.new -> stringResource(R.string.estado_producto_nuevo_texto)
        EstadoProducto.modified -> stringResource(R.string.estado_producto_modificado_texto)
        EstadoProducto.verified -> stringResource(R.string.estado_producto_verificado_texto)
    }

    DetallesProductoColumna(tipoDato = tipoDato, dato = textoMostrar)
}

@Composable
fun DetallesProductoColumna(modifier: Modifier = Modifier, tipoDato : String, contenido : @Composable () -> Unit){
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextoEtiqueta(texto = tipoDato)
        SmallSpace()
        contenido.invoke()
    }
}

@Composable
fun MeterEnFila(
    detalleProducto1 : @Composable () -> Unit,
    detalleProducto2: @Composable () -> Unit
){
    Row(
    ) {
        detalleProducto1()
        LargeSpace()
        detalleProducto2()
    }
}