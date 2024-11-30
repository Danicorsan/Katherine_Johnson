package app.features.productdetail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import app.base.ui.composables.SmallSpace
import app.base.utils.toCurrency
import app.domain.invoicing.product.EstadoProducto
import app.domain.invoicing.product.Producto
import app.domain.invoicing.product.classHolders.ICategoria
import app.domain.invoicing.product.classHolders.ISeccion
import app.features.productdetail.R
import kotlinx.datetime.LocalDate

@Preview(showSystemUi = true)
@Composable
fun PantallaDetallesProducto(){
    val categoria = object : ICategoria {
        override val nombre: String
            get() = "Nombre Categoria"
    }
    val seccion = object :ISeccion {
        override val nombreSeccion: String
            get() = "Nombre Seccion"
    }
    val producto : Producto = Producto(
        codigo = "dependenciaSeccion3",
        nombre = "Esponja duradera max",
        nombreCorto = "Esponja",
        descripcion = "Algo descriptivo",
        numeroSerie = "424D4234CD",
        codigoModelo = "97878DFWEFw",
        tipoProducto = "Aseo e Higiene",
        categoria = categoria,
        seccion = seccion,
        estado = EstadoProducto.new,
        _cantidad = 32u,
        precio = 23.44,
        fechaAdquisicion = LocalDate(111,12,23)
    )
    ContenidoDetallesProducto(Modifier, producto)
}

@Composable
private fun ContenidoDetallesProducto(modifier: Modifier, producto : Producto){
    Column(
        modifier = modifier.fillMaxSize()
    ){
        DetallesProductoHorizontal(
            tipoDato = stringResource(R.string.codigo_etiqueta), dato = producto.codigo
        )

        DetallesProductoHorizontal(
            tipoDato = stringResource(R.string.nombre_etiqueta), dato = producto.nombre
        )

        DetallesProductoHorizontal(
            tipoDato = stringResource(R.string.nombre_corto_etiqueta), dato = producto.nombreCorto
        )

        DetallesProductoHorizontal(
            tipoDato = stringResource(R.string.descripcion_etiqueta), dato =  producto.descripcion
        )

        DetallesProductoHorizontal(
            tipoDato = stringResource(R.string.numero_serie_etiqueta), dato = producto.numeroSerie
        )

        DetallesProductoHorizontal(
            tipoDato = stringResource(R.string.codigo_modelo_etiqueta), dato = producto.codigoModelo
        )

        DetallesProductoHorizontal(
            tipoDato = stringResource(R.string.tipo_producto_etiqueta), dato = producto.tipoProducto
        )

        DetallesProductoHorizontal(
            tipoDato = stringResource(R.string.categoria_etiqueta), dato = producto.categoria.nombre
        )

        DetallesProductoHorizontal(
            tipoDato = stringResource(R.string.seccion_etiqueta), dato = producto.seccion.nombreSeccion
        )

       DetallesProductoHorizontal(
            tipoDato = stringResource(R.string.estado_etiqueta), estado = producto.estado
        )

        DetallesProductoHorizontal(
            tipoDato = stringResource(R.string.cantidad_etiqueta), dato = producto.cantidad.toString()
        )

        DetallesProductoHorizontal(
            tipoDato = stringResource(R.string.precio_etiqueta), dato = producto.precio.toCurrency()
        )


        DetallesProductoHorizontal(
            tipoDato = stringResource(R.string.fecha_adquisicion_etiqueta), dato = producto.fechaAdquisicion.toString()
        )
    }
}

@Composable fun DetallesProductoHorizontal(modifier: Modifier = Modifier, tipoDato : String, dato : String?){
    dato?.let {
        Row(modifier = modifier){
            Text(
                tipoDato,
                fontWeight = FontWeight.Bold
            )
            SmallSpace()
            Text(dato)
        }
    }
}

@Composable
fun DetallesProductoHorizontal(modifier: Modifier = Modifier, tipoDato : String, estado : EstadoProducto){
    val textoMostrar = when(estado){
        EstadoProducto.new -> stringResource(R.string.estado_producto_nuevo_texto)
        EstadoProducto.modified -> stringResource(R.string.estado_producto_modificado_texto)
        EstadoProducto.verified -> stringResource(R.string.estado_producto_verificado_texto)
    }

    Row(modifier = modifier){
        Text(
            tipoDato,
            fontWeight = FontWeight.Bold
        )
        SmallSpace()
        Text(textoMostrar)
    }
}