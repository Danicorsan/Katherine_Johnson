package app.features.productcreation.ui.base.composables


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.features.productcreation.R
import coil.compose.rememberAsyncImagePainter

/**
 * Permite al usuario escoger una imagen
 * ya sea desde su dispositivo local o Drive
 *
 * @param onUriImageSelected Pasa por parametro
 * la [Uri] de la imagen seleccionada por el usuario.
 * @param currentUriSelected El [Uri] actualmente seleccionado.
 */
@Composable
fun ProductImagePicker(
    onUriImageSelected : (Uri) -> Unit,
    currentUriSelected : Uri? = null
){
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            onUriImageSelected(it)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable { launcher.launch("image/*") }
    ) {
        currentUriSelected?.let { imageUri ->
            Image(
                painter = rememberAsyncImagePainter(imageUri),
                contentDescription = stringResource(R.string.image_picker_image_content_description),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } ?: Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.image_picker_add_icon_conent_description),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(50.dp)
        )
    }
}
