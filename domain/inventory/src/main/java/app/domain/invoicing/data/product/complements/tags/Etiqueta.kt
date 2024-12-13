package app.domain.invoicing.data.product.complements.tags

data class Etiqueta(
    val contenido : String,
){
    override fun toString(): String {
        return contenido
    }
}