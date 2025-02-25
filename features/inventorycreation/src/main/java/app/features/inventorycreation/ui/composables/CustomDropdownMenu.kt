package app.features.inventorycreation.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun <T> CustomDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    items: List<T>,
    onItemSelected: (T) -> Unit,
    itemText: @Composable (T) -> String,
    itemIcon: @Composable() ((T) -> ImageVector)? = null
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier.fillMaxWidth()
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                onClick = {
                    onDismissRequest()
                    onItemSelected(item)
                },
                text = {
                    Text(text = itemText(item))
                },
                leadingIcon = itemIcon?.let {
                    { Icon(imageVector = it(item), contentDescription = null) }
                }
            )
        }
    }
}
