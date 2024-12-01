package app.domain.invoicing.product.complements.tags

data class Etiqueta(
    val contenido : String,
){
    fun equals(etiqueta: Etiqueta, ignoreCase : Boolean = false) : Boolean{
        return this.contenido.equals(etiqueta.contenido, ignoreCase)
    }

    override fun toString(): String {
        return contenido
    }
}