package app.domain.invoicing.section

import app.domain.invoicing.dependency.Dependency
import kotlinx.datetime.Instant

data class Section(
    val id : Int,
    val name : String,
    val shortName : String,
    val belongedDependency : Dependency,
    val image : String? = null, //Tipo String hasta que se decida el tipo para imagenes
    val releaseDate : Instant //Referido a la fecha de alta de una sección, ha ser posible, buscar un mejor nombre
)
