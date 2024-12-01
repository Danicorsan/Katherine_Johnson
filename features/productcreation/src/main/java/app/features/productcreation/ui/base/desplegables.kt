package app.features.productcreation.ui.base

data class Desplegables (
    val categorias : List<String> = listOf("Categorias"),
    val categoriasDesplegada : Boolean = false,
    val secciones : List<String> = listOf("Secciones"),
    val seccionesDesplegadas: Boolean = false
)