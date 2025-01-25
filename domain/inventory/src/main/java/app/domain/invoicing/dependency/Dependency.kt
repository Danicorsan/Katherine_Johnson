package app.domain.invoicing.dependency

data class Dependency(
    val id : Int,
    val name : String,
    val shortName : String,
    val description : String,
    val image : String? = null //Tipo String hasta decidir el tipo de imagen
)
