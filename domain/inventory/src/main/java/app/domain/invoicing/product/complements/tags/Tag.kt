package app.domain.invoicing.product.complements.tags

data class Tag(
    val content : String,
){
    override fun toString(): String {
        return content
    }
}