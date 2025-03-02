package app.domain.invoicing.product.complements.tags


class Tags(tagList: Iterable<Tag>? = null) : Iterable<Tag>, Collection<Tag> {
    companion object {
        fun fromString(tagsRepresentation: String?) : Tags {
            if (tagsRepresentation == null)
                return Tags()
            val list : MutableList<Tag> = mutableListOf()
            tagsRepresentation.split(", ").forEach{
                if (it.isNotEmpty())
                    list.add(Tag(it.trim()))
            }
            return Tags(list)
        }
    }

    override val size : Int
        get() = tagList.size

    override fun isEmpty(): Boolean {
        return tagList.isEmpty()
    }

    override fun containsAll(elements: Collection<Tag>): Boolean {
        return tagList.containsAll(elements)
    }

    override fun contains(element: Tag): Boolean {
        return tagList.contains(element)
    }

    private val tagList : MutableList<Tag> = tagList?.toMutableList() ?: emptyList<Tag>().toMutableList()

    override fun iterator(): Iterator<Tag> {
        return tagList.iterator()
    }

    operator fun get(index: Int) : Tag {
        return tagList[index]
    }

    operator fun set(index: Int, nuevaTag: Tag){
        tagList[index] = nuevaTag
    }

    fun add(vararg notes: Tag){
        notes.forEach {
            tagList.add(it)
        }
    }

    fun removeAt(noteIndex : Int){
        tagList.removeAt(noteIndex)
    }

    override fun toString(): String {
        return tagList.joinToString(", ")
    }

}