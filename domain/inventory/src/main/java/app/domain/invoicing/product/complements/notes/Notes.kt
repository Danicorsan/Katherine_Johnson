package app.domain.invoicing.product.complements.notes

class Notes(noteList : Iterable<Note>? = null) : Iterable<Note>{
    val size: Int
        get() = _noteList.size

    private val _noteList : MutableList<Note> = noteList?.toMutableList() ?: emptyList<Note>().toMutableList()

    override fun iterator(): Iterator<Note> {
        return _noteList.iterator()
    }

    operator fun get(index: Int) : Note {
        return _noteList[index]
    }

    operator fun set(index: Int, newNote : Note){
        _noteList[index] = newNote
    }

    fun add(vararg notes: Note){
        notes.forEach {
            _noteList.add(it)
        }
    }

    fun removeAt(noteIndex : Int){
        _noteList.removeAt(noteIndex)
    }
}