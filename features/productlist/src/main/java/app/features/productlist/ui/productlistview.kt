package app.features.productlist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.composables.NormalButton
import app.features.productlist.R

data class Eventos(
    val añadirProducto : () -> Unit,
    val verDetallesProducto : (String) -> Unit
)

@Preview(showSystemUi = true)
@Composable
fun ListadoProductoPantalla(viewModel: ListadoProductoViewModel = ListadoProductoViewModel()){
    ListadoProductosContenido(
        listaProductos = viewModel.productos,
        eventos = Eventos(
            añadirProducto = viewModel::añadirProducto,
            verDetallesProducto = viewModel::verDetallesProducto
        )
    )
}

@Composable
private fun ListadoProductosContenido(listaProductos : List<String>, eventos: Eventos){
    Column {
        Appbar(stringResource(R.string.titulo_appbar))
        BotonAñadirProductos(eventos.añadirProducto)
        ProductosListados(listaProductos, eventos)
    }
}

@Composable
private fun ProductosListados(listaProductos: List<String>, eventos: Eventos){
    LazyColumn {
        items(listaProductos) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = {eventos.verDetallesProducto(it)}
            ) {
                MostrarProducto(it)
            }
        }
    }
}

@Composable
private fun BotonAñadirProductos(añadirProducto: () -> Unit){
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        NormalButton(
            text = stringResource(R.string.texto_boton),
            onClick = añadirProducto
        )
    }
}