import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.base.ui.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Deprecated("Se pide una pantalla no data que contenga una animación Lottie, " +
        "por lo que debe usar NoDataAnimatedScreen",
    replaceWith = ReplaceWith("NoDataAnimatedScreen"))
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoDataScreen(onRetry: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Datos") },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Placeholder image, replace with your own resource
                Image(
                    painter = painterResource(id = R.drawable.ic_cactus),
                    contentDescription = "No Data Available",
                    modifier = Modifier.size(120.dp)
                )

                Text(
                    text = "No hay datos disponibles",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )

                Text(
                    text = "Intenta actualizar para ver si hay algo nuevo.",
                    fontSize = 16.sp,
                    color = Color(0xFF777777)
                )

                Button(
                    onClick = onRetry,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Text(text = "Reintentar", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun NoDataAnimatedScreen(modifier: Modifier = Modifier) {
    val lottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.nodatalottieanimation)
    )
    val loaderProgress by animateLottieCompositionAsState(lottieComposition)
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = lottieComposition,
            progress = { loaderProgress }
        )
    }
}