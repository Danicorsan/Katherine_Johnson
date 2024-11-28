package app.domain.ddd.repository

import app.domain.ddd.model.UniqueId

interface Repository<ID : UniqueId, E> {


    suspend fun save(entity: E) {
        TODO()
    }

    suspend fun remove(entity: E) {
        TODO()
    }

}
