package app.features.categorydetail.ui

import NoDataScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.base.ui.components.LoadingUi
import app.base.ui.composables.BaseAlertDialog
import app.domain.invoicing.category.Category
import app.features.categorydetail.R

@Composable
fun CategoryDetailScreen(
    viewModel: CategoryDetailViewModel,
    id: Int,
    onEditCategory: (Int) -> Unit,
    onGoBack: () -> Unit
) {
    LaunchedEffect(id) {
        viewModel.loadCategory(id)
    }

    when {
        viewModel.state.notFoundError -> NoDataScreen()
        viewModel.state.category == null -> {
            LoadingUi()
        }
        else -> {
            CategoryDetailContent(
                category = viewModel.state.category!!,
                onEditCategory = onEditCategory,
                onGoBack = onGoBack,
                onDeleteCategory = { viewModel.requestDeleteCategory() },
                onConfirmDelete = { viewModel.confirmDeleteCategory(onGoBack) },
                onCancelDelete = { viewModel.cancelDeleteCategory() },
                isDeleteDialogVisible = viewModel.state.isDeleteDialogVisible
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailContent(
    category: Category,
    onEditCategory: (Int) -> Unit,
    onGoBack: () -> Unit,
    onDeleteCategory: () -> Unit,
    onConfirmDelete: () -> Unit,
    onCancelDelete: () -> Unit,
    isDeleteDialogVisible: Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles") },
                navigationIcon = {
                    IconButton(onClick = { onGoBack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.volver)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onEditCategory(category.id) }) {
                        Icon(Icons.Filled.Edit, "Editar")
                    }
                    IconButton(onClick = { onDeleteCategory() }) {
                        Icon(Icons.Filled.Delete, "Eliminar")
                    }
                },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Card with shadow for category details
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Nombre de la Categoría",
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
                        text = "Descripción",
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
                        text = "Tipo de Categoría",
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

                    // If there's an image, show it
                    /*
                    if (category.image != null) {
                        Image(
                            painter = painterResource(id = R.drawable.category_image_placeholder), // Update this with the actual image resource
                            contentDescription = "Imagen de la categoría",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(vertical = 16.dp)
                        )
                    }

                     */
                }
            }
        }
    }

    // Diálogo de confirmación para eliminar categoría
    if (isDeleteDialogVisible) {
        BaseAlertDialog(
            onConfirm = onConfirmDelete,
            onDismiss = onCancelDelete,
            text = "¿Estás seguro de que quieres eliminar esta categoría?",
            confirmText = "Sí, eliminar",
            dismissText = "Cancelar",
            title = "Eliminar Categoría"
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
