package app.features.categorycreation.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import app.base.ui.composables.BaseAlertDialog
import app.features.categorycreation.R

/**
 * Error dialog
 *
 * @param onDismiss
 * @receiver
 */
@Composable
fun ErrorDialog(onDismiss: () -> Unit) {
    BaseAlertDialog(
        title = stringResource(R.string.error_al_crear_la_categoria),
        text = stringResource(R.string.revisar_cambios_dialog),
        confirmText = stringResource(R.string.aceptar),
        onConfirm = onDismiss,
        onDismiss = onDismiss
    )
}
