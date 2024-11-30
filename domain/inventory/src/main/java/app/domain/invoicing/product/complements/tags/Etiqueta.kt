package app.domain.invoicing.product.complements.tags

data class Etiqueta(
    val etiqueta : String,
){
    fun equals(etiqueta: Etiqueta, ignoreCase : Boolean = false) : Boolean{
        return this.etiqueta.equals(etiqueta.etiqueta, ignoreCase)
    }
}