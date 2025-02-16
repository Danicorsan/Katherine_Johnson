package com.example.inventory.home

import DrawerItem
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.base.ui.composables.MediumSpace
import com.example.inventory.R
import kotlinx.coroutines.launch

/**
 * Home screen
 *
 * @param onNavigateProducts
 * @param onNavigateCategories
 * @param onNavigateInventory
 * @receiver
 * @receiver
 * @receiver
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateProducts: () -> Unit,
    onNavigateCategories: () -> Unit,
    onNavigateInventory: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
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
                    Icon(Icons.Default.Close, contentDescription = stringResource(R.string.cerrar_menu))
                }

                MediumSpace()

                Box(
                    modifier = Modifier
                        .size(125.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Filled.Person,
                        contentDescription = stringResource(R.string.perfil)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                ) {
                    Text(
                        stringResource(R.string.nombre_apellido),
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center
                    )
                }

                MediumSpace()
                HorizontalDivider()
                MediumSpace()

                // Elementos del Drawer
                DrawerItem(stringResource(R.string.productos), Icons.Default.ShoppingCart) {
                    onNavigateProducts()
                    scope.launch { drawerState.close() }
                }
                DrawerItem(stringResource(R.string.categorias), Icons.AutoMirrored.Filled.List) {
                    onNavigateCategories()
                    scope.launch { drawerState.close() }
                }
                DrawerItem(stringResource(R.string.inventario), Icons.Default.Build) {
                    onNavigateInventory()
                    scope.launch { drawerState.close() }
                }

                MediumSpace()
                HorizontalDivider()

                DrawerItem(stringResource(R.string.ajustes), Icons.Default.Build) {
                    scope.launch { drawerState.close() }
                }
                DrawerItem(stringResource(R.string.acerca_de_nosotros), Icons.Default.Info) {
                    scope.launch { drawerState.close() }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = R.string.inicio)) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = stringResource(R.string.menu))
                        }
                    }
                )
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.bienvenido),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )

                    HomeMenuCard(
                        icon = Icons.Default.ShoppingCart,
                        title = stringResource(id = R.string.productos),
                        description = stringResource(id = R.string.explora_y_gestiona_tus_productos),
                        onClick = onNavigateProducts
                    )

                    HomeMenuCard(
                        icon = Icons.AutoMirrored.Filled.List,
                        title = stringResource(id = R.string.categorias),
                        description = stringResource(id = R.string.organiza_tus_productos_por_categorias),
                        onClick = onNavigateCategories
                    )

                    HomeMenuCard(
                        icon = Icons.Default.Build,
                        title = stringResource(id = R.string.inventario),
                        description = stringResource(id = R.string.consulta_y_gestiona_el_inventario),
                        onClick = onNavigateInventory
                    )
                }
            }
        )
    }
}

/**
 * Home menu card
 *
 * @param icon
 * @param title
 * @param description
 * @param onClick
 * @receiver
 */
@Composable
fun HomeMenuCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onNavigateProducts = {},
        onNavigateCategories = {},
        onNavigateInventory = {}
    )
}