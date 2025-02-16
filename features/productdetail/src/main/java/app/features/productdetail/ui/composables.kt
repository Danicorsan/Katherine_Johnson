package app.features.productdetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import app.base.ui.composables.Separations
import app.base.ui.composables.TitleText

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
fun DetailsProductScreenTitle(text: String){
    CakeColorBox{
        TitleText(
            text,
            modifier = Modifier.padding(Separations.Medium)
        )
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
fun LargeTextBox(contenido : String){
    Text(
        contenido,
        modifier = Modifier.fillMaxWidth(80/100f)
    )
}
