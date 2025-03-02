package app.domain.invoicing.converters

import androidx.room.TypeConverter
import app.domain.invoicing.section.Section
import app.domain.invoicing.section.SectionDao
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SectionConverter {

    @Inject
    private lateinit var sectionDao : SectionDao

    @TypeConverter
    fun fromSectionToId(section : Section?) : Int?{
        return section?.id
    }

    @TypeConverter
    fun fromIdToSection(sectionId : Int?) : Section?{
        var section : Section?
        runBlocking {
            section = sectionDao.getSectionById(sectionId)
        }
        return section
    }
}