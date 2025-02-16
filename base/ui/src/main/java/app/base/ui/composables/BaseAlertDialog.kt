package app.base.ui.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

/**
 * Base alert dialog
 *
 * @param title
 * @param text
 * @param confirmText
 * @param dismissText
 * @param onConfirm
 * @param onDismiss
 * @receiver
 * @receiver
 */
@Composable
fun BaseAlertDialog(
    title: String? = null,
    text: String? = null,
    confirmText: String,
    dismissText: String? = null,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {

    AlertDialog(
        title = {
            title?.let { Text(it) }
        },
        text = {
            text?.let { Text(it) }
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                }) {
                Text(text = confirmText)
            }
        },
        dismissButton = {
            dismissText?.let {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text(text = dismissText)
                }
            }
        }
    )
}