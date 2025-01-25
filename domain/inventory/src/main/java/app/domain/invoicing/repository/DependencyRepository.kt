package app.domain.invoicing.repository

import app.domain.invoicing.dependency.Dependency
import app.domain.invoicing.network.BaseResult
import app.domain.invoicing.repository.demodata.dependencyDemoData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object DependencyRepository {
    private val dependenciesRegistered = dependencyDemoData()

    /**
     * Permite obtener todas las dependencias
     *
     * @return Un objeto BaseResult que en caso de que sea del tipo success
     * devolverá un objeto Flow con el que se podrá recoger la lista de
     * dependencias. (Por ahora siempre devuelve BaseResult.Success)
     */
    fun getAllDependencies() : BaseResult<Flow<List<Dependency>>> {
        return BaseResult.Success(flow {
            delay(2000)
            emit(dependenciesRegistered)
        })
    }
}