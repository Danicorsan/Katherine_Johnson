package app.features.categorylist.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseAlertDialog
import app.base.ui.composables.MediumButton
import app.base.ui.composables.baseappbar.BaseAppBar
import app.base.ui.composables.baseappbar.BaseAppBarIcons
import app.base.ui.composables.baseappbar.BaseAppBarState
import app.domain.invoicing.repository.CategoryRepository
import app.features.categorylist.R
import coil.compose.rememberAsyncImagePainter

/**
 * Category list events
 *
 * @property onClickBack
 * @property onClickNewCategory
 * @property onClickDetails
 * @property requestDeleteCategory
 * @property confirmDeleteCategory
 * @property cancelDeleteCategory
 * @constructor Create empty Category list events
 */
data class CategoryListEvents(
    val onClickBack: () -> Unit,
    val onClickNewCategory: () -> Unit,
    val onClickDetails: (Int) -> Unit,
    val requestDeleteCategory: (Int) -> Unit,
    val confirmDeleteCategory: () -> Unit,
    val cancelDeleteCategory: () -> Unit
)

/**
 * Category list screen
 *
 * @param viewModel
 * @param onClickBack
 * @param onClickNewCategory
 * @param onClickDetails
 * @receiver
 * @receiver
 * @receiver
 */
@Composable
fun CategoryListScreen(
    viewModel: CategoryListViewModel,
    onClickBack: () -> Unit,
    onClickNewCategory: () -> Unit,
    onClickDetails: (Int) -> Unit
) {
    val categoryListEvents = CategoryListEvents(
        onClickBack = onClickBack,
        onClickNewCategory = onClickNewCategory,
        onClickDetails = onClickDetails,
        requestDeleteCategory = viewModel::requestDeleteCategory,
        confirmDeleteCategory = viewModel::confirmDeleteCategory,
        cancelDeleteCategory = viewModel::cancelDeleteCategory
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
    events: CategoryListEvents
) {
    Scaffold(
        topBar = {
            BaseAppBar(
                BaseAppBarState(
                    title = stringResource(R.string.lista_de_categoria),
                    navigationIcon = BaseAppBarIcons.goBackPreviousScreenIcon(
                        onClick = {
                            events.onClickBack()
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
    modifier: Modifier
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

    Column(modifier = modifier) {
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
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        if (category.image == null){
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.List,
                            contentDescription = stringResource(R.string.icono_de_categoria),
                            modifier = Modifier
                                .size(48.dp)
                                .padding(end = 8.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )}
                        else{
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(50.dp)
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
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCategoryListScreen() {
    CategoryListScreen(
        viewModel = CategoryListViewModel(CategoryRepository).apply {
        },
        onClickBack = {},
        onClickNewCategory = {},
        onClickDetails = {}
    )
}