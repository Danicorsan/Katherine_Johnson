package app.features.categorydetail.ui

import NoDataScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.MediumSpace
import app.base.ui.composables.baseappbar.Action
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.domain.invoicing.category.Category
import app.features.categorydetail.R
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

/**
 * Category detail events
 *
 * @property onEditCategory
 * @property onGoBack
 * @property onDeleteCategory
 * @property onConfirmDelete
 * @property onCancelDelete
 * @constructor Create empty Category detail events
 */
data class CategoryDetailEvents(
    val onEditCategory: (Int) -> Unit,
    val onGoBack: () -> Unit,
    val onDeleteCategory: () -> Unit,
    val onConfirmDelete: () -> Unit,
    val onCancelDelete: () -> Unit
)

/**
 * Category detail screen
 *
 * @param viewModel
 * @param id
 * @param onEditCategory
 * @param onGoBack
 * @receiver
 * @receiver
 */
@Composable
fun CategoryDetailScreen(
    viewModel: CategoryDetailViewModel = hiltViewModel(),
    id: Int,
    onEditCategory: (Int) -> Unit,
    onGoBack: () -> Unit
) {
    LaunchedEffect(id) {
        viewModel.loadCategory(id)
    }

    val state = viewModel.state

    Scaffold(
        topBar = {
            BaseAppBar(
                BaseAppBarState(
                    title = stringResource(R.string.detalle_de_la_categoria),
                    navigationIcon = BaseAppBarIcons.goBackPreviousScreenIcon { onGoBack() },
                    actions = listOf(
                        Action(
                            icon = Icons.Filled.Edit,
                            onClick = { onEditCategory(state.category?.id ?: 0) },
                            contentDescription = stringResource(R.string.editar)
                        ),
                        Action(
                            icon = Icons.Filled.Delete,
                            onClick = { viewModel.requestDeleteCategory() },
                            contentDescription = stringResource(R.string.eliminar)
                        )
                    )
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> LoadingUi()
                state.notFoundError -> NoDataScreen()
                state.category != null -> {
                    CategoryDetailContent(
                        category = state.category,
                        events = CategoryDetailEvents(
                            onEditCategory = onEditCategory,
                            onGoBack = onGoBack,
                            onDeleteCategory = { viewModel.requestDeleteCategory() },
                            onConfirmDelete = { viewModel.confirmDeleteCategory(onGoBack) },
                            onCancelDelete = { viewModel.cancelDeleteCategory() },
                        ),
                        isDeleteDialogVisible = state.isDeleteDialogVisible
                    )
                }
            }
        }
    }
}

/**
 * Category detail content
 *
 * @param category
 * @param events
 * @param isDeleteDialogVisible
 */
@Composable
fun CategoryDetailContent(
    category: Category,
    events: CategoryDetailEvents,
    isDeleteDialogVisible: Boolean
) {
    val scrollState = rememberScrollState() // Estado del scroll

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(scrollState), // Habilita el scroll vertical
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Imagen de la categoría
                if (category.image != null ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(category.image),
                            contentDescription = "Selected Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    MediumSpace()
                }

                Text(
                    text = stringResource(R.string.nombre_de_la_categoria),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = category.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Text(
                    text = stringResource(R.string.descripcion),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = category.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Text(
                    text = stringResource(R.string.tipo_de_categoria),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = category.typeCategory.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }
    }

    if (isDeleteDialogVisible) {
        BaseAlertDialog(
            onConfirm = events.onConfirmDelete,
            onDismiss = events.onCancelDelete,
            text = stringResource(R.string.seguro_eliminar_categoria),
            confirmText = stringResource(R.string.si_eliminar),
            dismissText = stringResource(R.string.cancelar),
            title = stringResource(R.string.eliminar_categoria)
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun Preview() {
    CategoryDetailScreen(
        viewModel = hiltViewModel(),
        id = 1,
        onEditCategory = {},
        onGoBack = {}
    )
}
