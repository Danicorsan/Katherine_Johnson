package app.features.productdetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import app.base.ui.composables.Separations
import app.base.ui.composables.TitleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appbar(titleText : String, navigationAction : () -> Unit){
    CenterAlignedTopAppBar(
        title = {
            Text(text = titleText)
        },
        navigationIcon = {
            IconButton(onClick = navigationAction) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
            }
        },
    )
}

@Composable
fun TextTag(text : String){
    CakeColorBox(
       modifier = Modifier.fillMaxWidth(85/100f)
    ) {
        Text(
            text,
            modifier = Modifier.padding(Separations.Small),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TitleText(text: String){
    CakeColorBox(
        modifier = Modifier
    ) {
        TitleText(text)
    }
}

@Composable
fun InformativeText(text: String){
    Text(
        text,
        textAlign = TextAlign.Center
    )
}

@Composable
fun CakeColorBox(modifier: Modifier = Modifier, contenido : @Composable () -> Unit){
    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ){
        contenido.invoke()
    }
}

@Composable
fun DescriptionText(contenido : String){
    Text(
        contenido,
        modifier = Modifier.fillMaxWidth(80/100f)
    )
}

@Composable
fun <T> ScrollabelRow(
    listaDeObjetos : Iterable<T>,
    formaDeMostrar : @Composable (objeto : T) -> Unit){
    LazyRow {
        items(listaDeObjetos.toList()){
                objetoAMostrar -> formaDeMostrar.invoke(objetoAMostrar)
        }
    }
}