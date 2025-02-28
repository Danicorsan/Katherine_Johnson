package app.features.productdetail.ui.base.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun NormalTextBox(text: String){
    Text(
        text,
        textAlign = TextAlign.Center
    )
}

@Composable
fun LargeTextBox(text : String){
    Text(
        text,
        modifier = Modifier.fillMaxWidth(80/100f)
    )
}
