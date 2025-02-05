package app.domain.invoicing.repository

import app.domain.invoicing.network.BaseResult
import app.domain.invoicing.repository.demodata.sectionDemoData
import app.domain.invoicing.section.Section
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object SectionRepository {
    private val sectionRegistered = sectionDemoData()

    /**
     * Permite obtener todas las secciones
     *
     * @return Un objeto BaseResult que en caso de que sea del tipo success
     * devolverá un objeto Flow con el que se podrá recoger la lista de
     * secciones. (Por ahora siempre devuelve BaseResult.Success)
     */
    fun getAllSections() : BaseResult<Flow<List<Section>>>{
        return BaseResult.Success(
            flow {
                delay(2000)
                emit(sectionRegistered)
            }
        )
    }
}