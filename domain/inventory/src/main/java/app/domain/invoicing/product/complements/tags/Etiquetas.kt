package app.domain.invoicing.product.complements.tags


class Etiquetas(listaEtiquetas: Iterable<Etiqueta>? = null) : Iterable<Etiqueta>, Collection<Etiqueta> {
    override val size : Int
        get() = listaEtiquetas.size

    override fun isEmpty(): Boolean {
        return listaEtiquetas.isEmpty()
    }

    override fun containsAll(elements: Collection<Etiqueta>): Boolean {
        return listaEtiquetas.containsAll(elements)
    }

    override fun contains(element: Etiqueta): Boolean {
        return listaEtiquetas.contains(element)
    }

    val listaEtiquetas : MutableList<Etiqueta> = listaEtiquetas?.toMutableList() ?: emptyList<Etiqueta>().toMutableList()

    override fun iterator(): Iterator<Etiqueta> {
        return listaEtiquetas.iterator()
    }

    operator fun get(indice: Int) : Etiqueta {
        return listaEtiquetas[indice]
    }

    operator fun set(indice: Int, nuevaEtiqueta: Etiqueta){
        listaEtiquetas[indice] = nuevaEtiqueta
    }

    fun añadir(vararg notas: Etiqueta){
        notas.forEach {
            listaEtiquetas.add(it)
        }
    }

    fun eliminar(indiceNota : Int){
        listaEtiquetas.removeAt(indiceNota)
    }

    override fun toString(): String {
        return listaEtiquetas.joinToString(", ")
    }

}