package app.domain.invoicing.product.complements.tags

import app.domain.invoicing.product.complements.notes.Nota

class Etiquetas(listaEtiquetas: Iterable<Etiqueta>? = null) : Iterable<Etiqueta> {
    val size : Int
        get() = listaEtiquetas.size

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


}