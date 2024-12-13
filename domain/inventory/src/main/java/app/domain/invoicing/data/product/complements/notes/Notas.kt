package app.domain.invoicing.data.product.complements.notes

class Notas(listaNotas : Iterable<Nota>? = null) : Iterable<Nota>{
    val size: Int
        get() = listaNotas.size

    private val listaNotas : MutableList<Nota> = listaNotas?.toMutableList() ?: emptyList<Nota>().toMutableList()

    override fun iterator(): Iterator<Nota> {
        return listaNotas.iterator()
    }

    operator fun get(indice: Int) : Nota {
        return listaNotas[indice]
    }

    operator fun set(indice: Int, nuevaNota : Nota){
        listaNotas[indice] = nuevaNota
    }

    fun añadir(vararg notas: Nota){
        notas.forEach {
            listaNotas.add(it)
        }
    }

    fun eliminar(indiceNota : Int){
        listaNotas.removeAt(indiceNota)
    }
}