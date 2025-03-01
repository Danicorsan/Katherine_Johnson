/**
 * Un men desplegable personalizado que se puede usar para mostrar una lista de
 * elementos y permitir al usuario seleccionar uno de ellos.
 *
 * @param expanded indica si el men desplegable está  abierto o no
 * @param onDismissRequest se llama cuando se cierra el men desplegable
 * @param items la lista de elementos que se van a mostrar en el men desplegable
 * @param onItemSelected se llama cuando se selecciona un elemento del men desplegable
 * @param itemText una función que toma un elemento de la lista y devuelve el texto
 *                 que se va a mostrar para ese elemento en el men desplegable
 * @param itemIcon una función que toma un elemento de la lista y devuelve el icono
 *                 que se va a mostrar para ese elemento en el men desplegable.
 *                 Puede ser nulo si no se quiere mostrar icono alguno.
 */
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