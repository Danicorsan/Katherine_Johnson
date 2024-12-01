package app.features.productdetail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.base.ui.composables.MediumSpace
import app.base.ui.composables.Separations
import app.base.ui.composables.SmallSpace
import app.base.ui.composables.TitleText
import app.base.utils.toCurrency
import app.domain.invoicing.product.EstadoProducto
import app.domain.invoicing.product.Producto
import app.domain.invoicing.product.classHolders.ICategoria
import app.domain.invoicing.product.classHolders.ISeccion
import app.domain.invoicing.product.complements.notes.Nota
import app.features.productdetail.R
import kotlinx.datetime.LocalDate

@Preview(showBackground = true)
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
        descripcion = "Algo descripcionfffffffffffffffffffffffffffffffffffffffff",
        numeroSerie = "424D4234CD",
        codigoModelo = "97878DFWEFw",
        tipoProducto = "Aseo e Higiene",
        categoria = categoria,
        seccion = seccion,
        estado = EstadoProducto.new,
        _cantidad = 32u,
        precio = 23.44,
        fechaAdquisicion = LocalDate(111,12,23),
    )
    producto.notas.añadir(Nota("Un titulo genial", "Un cuerpo muy pobreeeeee"))
    ContenidoDetallesProducto(Modifier, producto)
}

@Composable
private fun ContenidoDetallesProducto(modifier: Modifier, producto : Producto){

    Column {
        Appbar(titleText = stringResource(R.string.titulo_appbar))
        ColumnaDeContenidoScroleable(modifier = modifier ,producto = producto)
    }
}

@Composable
fun ColumnaDeContenidoScroleable(modifier: Modifier = Modifier, producto: Producto){
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = Separations.Medium),
        horizontalAlignment = Alignment.CenterHorizontally,

        ){

        TextoTitulo(modifier.padding(Separations.Medium), producto.nombre)

        MeterEnFila(
            {DetallesProductoColumna(
                tipoDato = stringResource(R.string.codigo_etiqueta), dato = producto.codigo
            )},
            {DetallesProductoColumna(
                tipoDato = stringResource(R.string.nombre_corto_etiqueta), dato = producto.nombreCorto
            )}
        )
        MediumSpace()
        MeterEnFila(
            { DetallesProductoColumna(
                tipoDato = stringResource(R.string.categoria_etiqueta), dato = producto.categoria.nombre
            )},
            {DetallesProductoColumna(
                tipoDato = stringResource(R.string.numero_serie_etiqueta), dato = producto.numeroSerie
            )}
        )

        MediumSpace()
        MeterEnFila(
            {DetallesProductoColumna(
                tipoDato = stringResource(R.string.codigo_modelo_etiqueta), dato = producto.codigoModelo
            )},
            {DetallesProductoColumna(
                tipoDato = stringResource(R.string.tipo_producto_etiqueta), dato = producto.tipoProducto
            )}
        )
        MediumSpace()

        MeterEnFila(
            {DetallesProductoColumna(
                tipoDato = stringResource(R.string.seccion_etiqueta), dato = producto.seccion.nombreSeccion
            )},
            {DetallesProductoColumna(
                tipoDato = stringResource(R.string.estado_etiqueta), estado = producto.estado
            )}
        )
        MediumSpace()
        MeterEnFila(
            {DetallesProductoColumna(
                tipoDato = stringResource(R.string.cantidad_etiqueta), dato = producto.cantidad.toString()
            )},
            {DetallesProductoColumna(
                tipoDato = stringResource(R.string.precio_etiqueta), dato = producto.precio.toCurrency()
            )}
        )
        MediumSpace()
        MeterEnFila(
            {DetallesProductoColumna(
                tipoDato = stringResource(R.string.fecha_adquisicion_etiqueta), dato = producto.fechaAdquisicion.toString()
            )},
            {DetallesProductoColumna(
                tipoDato = stringResource(R.string.fecha_modificacion_etiqueta), dato = producto.fechaBaja?.toString()
            )}
        )
        MediumSpace()
        MeterEnFila(
            {DetallesProductoColumna(
                tipoDato = stringResource(R.string.stock_minimo_etiqueta), dato = producto.stockMinimo?.toString()
            )},
            {DetallesProductoColumna(
                tipoDato = stringResource(R.string.etiquetas_etiqueta), dato = if (producto.etiquetas.isEmpty()) null else producto.etiquetas.toString()
            )}
        )
        MediumSpace()
        DetallesProductoColumna(
            tipoDato = stringResource(R.string.descripcion_etiqueta)
        ){
            val contenidoAMostrar = when(producto.descripcion){
                null -> stringResource(R.string.campo_sin_valor)
                else -> producto.descripcion!!
            }
            TextoDescripcion(contenido = contenidoAMostrar)
        }
        MediumSpace()
        DetallesProductoColumna(
            tipoDato = stringResource(R.string.notas_etiqueta)
        ){
            FilaDeContenidoScrolleable(producto.notas) {
                    nota -> MotrarNota(nota)
            }
        }
    }
}

@Composable
private fun MotrarNota(nota: Nota){
    Column {
        TextoInformativo(texto = nota.titulo)
        SmallSpace()
        TextoInformativo(texto = nota.titulo)
    }
}
