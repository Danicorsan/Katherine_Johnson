package app.base.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Normal button
 *
 * @param text
 * @param onClick
 * @param modifier
 * @receiver
 */
@Composable
fun NormalButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp) // Uso del recurso `shape`
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
            )
        )
    }
}

/**
 * Medium button
 *
 * @param onClick
 * @param imageVector
 * @param contentDescription
 * @receiver
 */
@Composable
fun MediumButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String
) {
    FloatingActionButton(
        modifier = Modifier.size(75.dp),
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 6.dp,
            pressedElevation = 10.dp
        )
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}