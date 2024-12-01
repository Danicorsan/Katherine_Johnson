package app.features.productcreation.ui.edition

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.features.productcreation.R
import app.features.productcreation.ui.base.Appbar
import app.features.productcreation.ui.base.Desplegables
import app.features.productcreation.ui.base.FormularioProducto
import app.features.productcreation.ui.base.ProductoEstado
import app.features.productcreation.ui.base.EventosProducto

@Preview(showBackground = true)
@Composable
fun EdicionProductoPantalla(viewModel: ProductoEdicionViewModel = ProductoEdicionViewModel()){
    EdicionProductoContenido(
        estadoProducto = viewModel.estadoProducto,
        eventos = EventosProducto(
            confirmacionSobreProducto = viewModel::confirmarEdicionProducto,
            abandonarPagina = viewModel::abandonarPagina,
            nombreCambiado = viewModel::nombreCambiado
        ),
        desplegables = viewModel.desplegable
    )
}

@Composable
fun EdicionProductoContenido(
    estadoProducto: ProductoEstado,
    eventos : EventosProducto,
    desplegables: Desplegables
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Appbar(titleText = stringResource(R.string.titulo_edicion_producto_appbar))
        FormularioProducto(
            estadoProducto,
            eventos,
            desplegables,
            stringResource(R.string.boton_aceptar)
        )
    }
}
