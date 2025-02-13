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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import app.domain.invoicing.category.Category
import app.domain.invoicing.category.TypeCategory
import app.domain.invoicing.repository.CategoryRepository
import app.features.categorylist.R
import java.util.Date

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
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.lista_de_categoria),
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { events.onClickBack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.volver)
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            MediumButton(
                contentDescription = "Add",
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
                            contentDescription = "Icono de categoría",
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
    val dummyCategories = List(20) { index ->
        Category(
            id = index,
            name = "Category $index",
            description = "Description $index",
            shortName = "Short $index",
            image = null,
            createdAt = Date(),
            typeCategory = TypeCategory.BASICOS,
            fungible = true
        )
    }
    CategoryListScreen(
        viewModel = CategoryListViewModel(CategoryRepository).apply {
        },
        onClickBack = {},
        onClickNewCategory = {},
        onClickDetails = {}
    )
}