package app.features.productcreation.ui.base

data class DropDownItems (
    val categories : List<String> = listOf("Categorias"),
    val categoryExpanded : Boolean = false,
    val section : List<String> = listOf("Secciones"),
    val sectionExpanded: Boolean = false
)