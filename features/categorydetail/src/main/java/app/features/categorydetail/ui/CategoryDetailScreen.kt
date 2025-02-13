package app.features.categorydetail.ui

import NoDataScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.base.ui.components.LoadingUi
import app.domain.invoicing.category.Category
import app.features.categorydetail.R

@Composable
fun CategoryDetailScreen(
    viewModel: CategoryDetailViewModel,
    id: Int,
    onClickBack:()->Unit
) {
    // Cargar la categoría al montar el Composable
    LaunchedEffect(id) {
        viewModel.loadCategory(id)
    }

    when {
        viewModel.state.notFoundError -> NoDataScreen()
        viewModel.state.category == null -> {
            // Muestra un indicador de carga mientras se obtiene la categoría
            LoadingUi()
        }
        else -> {
            CategoryDetailContent(
                category = viewModel.state.category!!,
                onEditCategory = onClickBack
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailContent(
    category: Category,
    onEditCategory: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Datos") },
                navigationIcon = {
                    IconButton(onClick = onEditCategory) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.volver)
                        )
                    }
                },
            )
        },

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Detalle de la Categoría",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Text(
                    text = "Nombre:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = category.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Descripción:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = category.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun Preview() {
    val viewModel = CategoryDetailViewModel()
    CategoryDetailScreen(
        viewModel = viewModel,
        id = 1,
        onClickBack = {}
    )
}


