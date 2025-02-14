package app.features.categorylist.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

data class CategoryListEvents(
    val onClickBack: () -> Unit,
    val onClickNewCategory: () -> Unit,
    val onClickDetails: (Int) -> Unit,
    val requestDeleteCategory: (Int) -> Unit,
    val confirmDeleteCategory: () -> Unit,
    val cancelDeleteCategory: () -> Unit
)

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

@OptIn(ExperimentalMaterial3Api::class)
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
            text = "¿Estás seguro que quieres eliminar la categoría?",
            confirmText = "Sí, estoy seguro",
            dismissText = "No, volver",
            title = "Borrar Categoría"
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
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.List,
                            contentDescription = stringResource(R.string.icono_de_categoria),
                            modifier = Modifier
                                .size(48.dp)
                                .padding(end = 8.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            text = category.name,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(4.dp)
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