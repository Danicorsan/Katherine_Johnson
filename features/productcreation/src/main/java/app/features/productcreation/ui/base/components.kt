package app.features.productcreation.ui.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.base.ui.composables.MediumSpace
import app.base.ui.composables.NormalButton
import app.features.productcreation.R

@Composable
fun FormularioProducto(
    estadoProducto: ProductoEstado,
    eventos : EventosProducto,
    desplegables : Desplegables,
    textoBoton : String,
    botonPulsado : () -> Unit = {}
){
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextoEditableUnaLinea(
            texto = estadoProducto.nombre,
            cambio = eventos.nombreCambiado,
            etiqueta = stringResource(R.string.nombre_etiqueta)
        )

        TextoEditableUnaLinea(
            texto = estadoProducto.nombreCorto,
            cambio = eventos.nombreCambiado,
            etiqueta = stringResource(R.string.nombre_corto_etiqueta)
        )

        TextoEditableUnaLinea(
            texto = estadoProducto.codigo,
            cambio = eventos.nombreCambiado,
            etiqueta = stringResource(R.string.codigo_etiqueta)
        )

        TextoEditableUnaLinea(
            texto = estadoProducto.numeroSerie,
            cambio = eventos.nombreCambiado,
            etiqueta = stringResource(R.string.numero_serie_etiqueta)
        )

        TextoEditableUnaLinea(
            texto = estadoProducto.codigoModelo,
            cambio = eventos.nombreCambiado,
            etiqueta = stringResource(R.string.codigo_modelo_etiqueta)
        )

        TextoEditableUnaLinea(
            texto = estadoProducto.tipoProducto,
            cambio = eventos.nombreCambiado,
            etiqueta = stringResource(R.string.tipo_producto_etiqueta)
        )

        MenuDesplegable(
            expandido = desplegables.categoriasDesplegada ,
            tocarFuera = {},
            listaElementos = desplegables.categorias,
            alPulsarApartado = {}
        ) {
            Text(it)
        }

        TextoEditableUnaLinea(
            texto = estadoProducto.precio,
            cambio = eventos.nombreCambiado,
            etiqueta = stringResource(R.string.precio_etiqueta)
        )

        TextoEditableLineaMultiple(
            texto = estadoProducto.descripcion,
            cambio = eventos.nombreCambiado,
            etiqueta = stringResource(R.string.descripcion_etiqueta)
        )
        MediumSpace()
        NormalButton(
            text = textoBoton,
            onClick = botonPulsado
        )
    }
}