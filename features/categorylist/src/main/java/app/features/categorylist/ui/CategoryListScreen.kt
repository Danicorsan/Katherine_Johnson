package app.features.categorylist.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.base.ui.components.LoadingUi
import app.base.ui.composables.AppDrawer
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.MediumButton
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.features.categorylist.R
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.CoroutineScope

/**
 * Category list events
 *
 * @property onClickBack
 * @property onClickNewCategory
 * @property onClickDetails
 * @property requestDeleteCategory
 * @property confirmDeleteCategory
 * @property cancelDeleteCategory
 * @property onOpenDrawer
 * @property onClickInventory
 * @property onClickProduct
 * @constructor Create empty Category list events
 */
data class CategoryListEvents(
    val onClickBack: () -> Unit,
    val onClickNewCategory: () -> Unit,
    val onClickDetails: (Int) -> Unit,
    val requestDeleteCategory: (Int) -> Unit,
    val confirmDeleteCategory: () -> Unit,
    val cancelDeleteCategory: () -> Unit,
    val onOpenDrawer: (CoroutineScope) -> Unit,
    val onClickInventory: () -> Unit,
    val onClickProduct: () -> Unit,
)

/**
 * Category list screen
 *
 * @param viewModel
 * @param onClickBack
 * @param onClickNewCategory
 * @param onClickDetails
 * @param onClickInventory
 * @param onClicProduct
 * @receiver
 * @receiver
 * @receiver
 * @receiver
 * @receiver
 */
@Composable
fun CategoryListScreen(
    viewModel: CategoryListViewModel,
    onClickBack: () -> Unit,
    onClickNewCategory: () -> Unit,
    onClickDetails: (Int) -> Unit,
    onClickInventory: () -> Unit,
    onClicProduct: () -> Unit,
) {
    val categoryListEvents = CategoryListEvents(
        onClickBack = onClickBack,
        onClickNewCategory = onClickNewCategory,
        onClickDetails = onClickDetails,
        requestDeleteCategory = viewModel::requestDeleteCategory,
        confirmDeleteCategory = viewModel::confirmDeleteCategory,
        cancelDeleteCategory = viewModel::cancelDeleteCategory,
        onOpenDrawer = viewModel::onOpenDrawer,
        onClickInventory = onClickInventory,
        onClickProduct = onClicProduct
    )
    CategoryListScreen(viewModel.state, categoryListEvents)
}

/**
 * Category list screen
 *
 * @param state
 * @param events
 */
@Composable
fun CategoryListScreen(
    state: CategoryListState,
    events: CategoryListEvents,
) {
    val scopeCoroutine = rememberCoroutineScope()
    AppDrawer(
        drawerState = state.drawerState,
        onNavigateProducts = { events.onClickProduct() },
        onNavigateCategories = {},
        onNavigateInventory = { events.onClickInventory() },
        content = {
            Scaffold(
                topBar = {
                    BaseAppBar(
                        BaseAppBarState(
                            title = stringResource(R.string.lista_de_categoria),
                            navigationIcon = BaseAppBarIcons.drawerMenuIcon(
                                onClick = {
                                    events.onOpenDrawer(scopeCoroutine)
                                }
                            )
                        )
                    )
                },
                floatingActionButton = {
                    MediumButton(
                        contentDescription = stringResource(R.string.add),
                        imageVector = Icons.Filled.Add,
                        onClick = { events.onClickNewCategory() },
                    )
                },
                content = { innerPadding ->

                    when {
                        state.isLoading -> LoadingUi()
                        else -> CategoryListContent(
                            state, events, modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            )
        })
}

/**
 * Category list content
 *
 * @param state
 * @param events
 * @param modifier
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryListContent(
    state: CategoryListState,
    events: CategoryListEvents,
    modifier: Modifier,
) {
    if (state.isDeleteDialogVisible) {
        BaseAlertDialog(
            onConfirm = {
                events.confirmDeleteCategory()
            },
            onDismiss = { events.cancelDeleteCategory() },
            text = stringResource(R.string.estas_seguro_que_quieres_eliminar_la_categoria),
            confirmText = stringResource(R.string.si_estoy_seguro),
            dismissText = stringResource(R.string.no_volver),
            title = stringResource(R.string.borrar_categoria)
        )
    }


    if (state.categories.isNotEmpty()) {

        Column(
            modifier = modifier
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(state.categories) { category ->
                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .combinedClickable(
                                onLongClick = {
                                    events.requestDeleteCategory(category.id)
                                },
                                onClick = {
                                    events.onClickDetails(category.id)
                                }
                            )
                            .shadow(8.dp, shape = CircleShape), // Sombra con forma personalizada
                        elevation = CardDefaults.elevatedCardElevation(3.dp) // Usa CardDefaults.elevation()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            if (category.image == null) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.List,
                                    contentDescription = stringResource(R.string.icono_de_categoria),
                                    modifier = Modifier
                                        .size(48.dp)
                                        .padding(end = 8.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            } else {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.surfaceVariant)
                                ) {
                                    // Aquí es donde usas AsyncImage para cargar la URI
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(category.image) // URI de la imagen
                                            .crossfade(true) // Añadir un efecto de desvanecimiento cuando se carga
                                            .build(),
                                        contentDescription = "Category Image",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop, // Ajuste de la imagen
                                        placeholder = painterResource(app.base.ui.R.drawable.ic_cactus), // Imagen de carga
                                        error = painterResource(app.base.ui.R.drawable.ic_cactus) // Imagen de error
                                    )
                                }
                            }

                            Text(
                                text = category.name,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(12.dp)
                            )
                        }
                    }
                }
            }
        }

    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // Centra la columna dentro del Box
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "No Data",
                    modifier = Modifier.size(64.dp), // Ajusta el tamaño del icono
                    tint = MaterialTheme.colorScheme.error // Color de error para resaltar
                )
                Text(
                    text = stringResource(R.string.no_hay_datos),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

/**
 * Preview category list screen
 *
 */
@Preview(showSystemUi = true)
@Composable
fun PreviewCategoryListScreen() {
    CategoryListScreen(
        viewModel = hiltViewModel(),
        onClickBack = {},
        onClickNewCategory = {},
        onClickDetails = {},
        {},
        {}
    )
}
