package app.features.productdetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.base.ui.composables.MediumTitleText
import app.base.ui.composables.Separations
import app.base.ui.composables.TitleText


@Composable
fun TextoEtiqueta(modifier: Modifier = Modifier, texto : String){
    CajaPastel(
        modifier = Modifier.width(150.dp)
    ) {
        Text(
            texto,
            modifier = modifier.padding(Separations.Small),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TextoTitulo(modifier: Modifier = Modifier , text: String){
    CajaPastel(
        modifier = modifier
    ) {
        TitleText(
            text,
            modifier = Modifier.padding(Separations.Medium))
    }
}

@Composable
fun TextoInformativo(
    modifier : Modifier = Modifier,
    texto: String){
    Text(
        texto,
        modifier = modifier
            .width(150.dp),
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appbar(modifier: Modifier = Modifier, titleText : String, accionNavegacion : () -> Unit = {}){
    CenterAlignedTopAppBar(
        title = {
            Text(text = titleText)
        },
        navigationIcon = {
            IconButton(onClick = accionNavegacion) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
            }
        },
    )
}

@Composable
fun LargeSpace(){
    Spacer(modifier = Modifier.size(40.dp))
}

@Composable
fun CajaPastel(modifier: Modifier = Modifier, contenido : @Composable () -> Unit){
    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ){
        contenido.invoke()
    }
}

@Composable
fun TextoDescripcion(modifier: Modifier = Modifier, contenido : String){
    Text(
        contenido,
        modifier = Modifier.width(300.dp)
    )
}

@Composable
fun <T> FilaDeContenidoScrolleable(
    listaDeObjetos : Iterable<T>,
    formaDeMostrar : @Composable (objeto : T) -> Unit){
    LazyRow {
        items(listaDeObjetos.toList()){
                objetoAMostrar -> formaDeMostrar.invoke(objetoAMostrar)
        }
    }
}