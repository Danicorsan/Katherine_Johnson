package app.domain.invoicing.converters

import androidx.room.TypeConverter
import app.domain.invoicing.dependency.Dependency
import app.domain.invoicing.dependency.DependencyDao
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DependencyConverter @Inject constructor() {

    @Inject private lateinit var dependencyDao : DependencyDao
    @TypeConverter
    fun fromDependencyToId(dependency: Dependency?) : Int?{
        return dependency?.id
    }

    @TypeConverter
    fun fromIdToDependency(id : Int?) : Dependency?{
        var dependency : Dependency?
        runBlocking {
            dependency = dependencyDao.getDependencyById(id!!)
        }
        return dependency
    }
}