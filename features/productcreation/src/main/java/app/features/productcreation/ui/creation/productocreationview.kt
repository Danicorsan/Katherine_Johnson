package app.features.productcreation.ui.creation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun CreacionProductoPantalla(viewModel: ProductoCreacionViewModel = ProductoCreacionViewModel()){
    CreacionProductoContenido(
        estadoProducto = viewModel.estadoProducto,
        eventos = EventosProducto(
            confirmacionSobreProducto = viewModel::crearProducto,
            abandonarPagina = viewModel::abandonarPagina,
            nombreCambiado = viewModel::nombreCambiado
        ), desplegables = viewModel.desplegables
        )
}

@Composable
fun CreacionProductoContenido(
    estadoProducto: ProductoEstado,
    eventos : EventosProducto,
    desplegables: Desplegables){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Appbar(titleText = stringResource(R.string.titulo_creacion_producto_appbar))
        FormularioProducto(
            estadoProducto,
            eventos,
            desplegables,
            stringResource(R.string.boton_aceptar)
        )
    }

}

