package app.base.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun AppDrawer(
    drawerState: DrawerState,
    onNavigateProducts: () -> Unit,
    onNavigateCategories: () -> Unit,
    onNavigateInventory: () -> Unit,
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                // Botón para cerrar el Drawer
                IconButton(
                    onClick = { scope.launch { drawerState.close() } },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Cerrar Menu")
                }

                MediumSpace()

                // Encabezado del Drawer
                DrawerHeader(
                    icon = Icons.Filled.Person,
                    title = "Nombre Apellido1 Apellido2"
                )

                MediumSpace()
                HorizontalDivider()
                MediumSpace()

                // Elementos del Drawer
                DrawerItem("Productos", Icons.Default.ShoppingCart) {
                    onNavigateProducts()
                    scope.launch { drawerState.close() }
                }
                DrawerItem("Categorias", Icons.AutoMirrored.Filled.List) {
                    onNavigateCategories()
                    scope.launch { drawerState.close() }
                }
                DrawerItem("Inventario", Icons.Default.Build) {
                    onNavigateInventory()
                    scope.launch { drawerState.close() }
                }

                MediumSpace()
                HorizontalDivider()

                DrawerItem("Ajustes", Icons.Default.Build) {
                    scope.launch { drawerState.close() }
                }
                DrawerItem("Acerca de nosotros", Icons.Default.Info) {
                    scope.launch { drawerState.close() }
                }
            }
        }
    ) {
        // Contenido principal
        content()
    }
}

/**
 * Drawer header
 *
 * @param icon
 * @param title
 * @param modifier
 */
@Composable
private fun DrawerHeader(
    icon: ImageVector,
    title: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.size(125.dp)
        )
    }
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(10.dp),
        textAlign = TextAlign.Center
    )
}

/**
 * Drawer item
 *
 * @param title
 * @param icon
 * @param onClick
 * @receiver
 */
@Composable
private fun DrawerItem(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = title, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title, fontSize = 18.sp)
    }
}