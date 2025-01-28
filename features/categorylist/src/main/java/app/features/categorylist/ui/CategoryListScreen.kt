package app.features.categorylist.ui

import androidx.compose.foundation.clickable
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
import app.base.ui.composables.MediumButton
import app.domain.invoicing.category.Category
import app.domain.invoicing.category.TypeCategory
import app.features.categorylist.R
import java.util.Date


data class CategoryListEvents(
    val onClickBack: () -> Unit,
    val onClickNewCategory: () -> Unit,
    val onClickEditCategory: () -> Unit,
    val onClickDetails: (Category) -> Unit
)

@Composable
fun CategoryListScreen(
    viewModel: CategoryListViewModel = CategoryListViewModel(),
    onClickBack: () -> Unit,
    onClickNewCategory: () -> Unit,
    onClickEditCategory: () -> Unit,
    onClickDetails: () -> Unit
) {
    val categoryListEvents = CategoryListEvents(
        { onClickBack() },
        { onClickNewCategory() },
        { onClickEditCategory() },
        { onClickDetails() }
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

@Composable
fun CategoryListContent(
    state: CategoryListState,
    events: CategoryListEvents,
    modifier: Modifier
) {

    Column(
        modifier = modifier // Asegura que el contenido no se solape con el FAB o el TopAppBar
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
                        .clickable { /* TODO IMPLEMENTAR EL DETAILSCREEN EN FUTURAS PRACTICAS */ }
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

                        // Texto de la categoría
                        Text(
                            text = category.name,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .align(Alignment.CenterVertically) // Centra el texto verticalmente respecto a la imagen
                                .padding(4.dp) // Espaciado interno
                        )

                        /*
                        //Contenedor de Iconos
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            //Informacion categoria
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .width(40.dp)
                            ) {
                                IconButton(
                                    onClick = {
                                        events.onClickDetails(category)
                                    },
                                ) {
                                    Icon(Icons.Filled.Info, "Info")
                                }

                            }

                            //Editar Categoria
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.width(40.dp)
                            ) {
                                IconButton(
                                    onClick = {
                                        events.onClickEditCategory()
                                    },
                                ) {
                                    Icon(Icons.Filled.Edit, "Edit")
                                }

                            }
                            //Borrar categoria
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .width(40.dp)
                            ) {
                                IconButton(
                                    enabled = false,
                                    onClick = {

                                    },
                                ) {
                                    Icon(Icons.Filled.Delete, "Delete")
                                }

                            }

                         */
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
        viewModel = CategoryListViewModel().apply {
            state.categories = dummyCategories // Agrega datos ficticios
        },
        onClickBack = {},
        onClickNewCategory = {},
        onClickEditCategory = {},
        onClickDetails = {}
    )
}