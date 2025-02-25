package app.features.productdetail.ui.base.composables

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
fun DetailsHead(text : String){
    PrimaryColoredBox(
        modifier = Modifier.fillMaxWidth(85 / 100f)
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
    PrimaryColoredBox{
        TitleText(
            text,
            modifier = Modifier.padding(Separations.Medium)
        )
    }
}

@Composable
fun PrimaryColoredBox(modifier: Modifier = Modifier, contenido : @Composable () -> Unit){
    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ){
        contenido.invoke()
    }
}