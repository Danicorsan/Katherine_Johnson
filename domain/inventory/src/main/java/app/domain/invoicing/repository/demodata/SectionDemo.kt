package app.domain.invoicing.repository.demodata

import app.domain.invoicing.section.Section
import kotlinx.datetime.Instant

internal fun sectionDemoData() : MutableList<Section>{
    val dependencyDemoData = dependencyDemoData()
    return mutableListOf(
        Section(
            id = 1,
            name = "Almacenamiento de Materias Primas",
            shortName = "Materias Primas",
            belongedDependency = dependencyDemoData[0],
            releaseDate = Instant.parse("2023-01-01T10:00:00Z")
        ),
        Section(
            id = 2,
            name = "Almacenamiento de Productos Terminados",
            shortName = "Productos Terminados",
            belongedDependency = dependencyDemoData[0],
            releaseDate = Instant.parse("2023-02-01T10:00:00Z")
        ),
        Section(
            id = 3,
            name = "Mesa Principal",
            shortName = "Mesa Principal",
            belongedDependency = dependencyDemoData[1],
            releaseDate = Instant.parse("2023-03-01T09:00:00Z")
        ),
        Section(
            id = 4,
            name = "Equipo de Videoconferencia",
            shortName = "Videoconferencia",
            belongedDependency = dependencyDemoData[1],
            releaseDate = Instant.parse("2023-04-01T09:00:00Z")
        ),
        Section(
            id = 5,
            name = "Lavabos",
            shortName = "Lavabos",
            belongedDependency = dependencyDemoData[2],
            releaseDate = Instant.parse("2023-01-15T08:00:00Z")
        ),
        Section(
            id = 6,
            name = "Área de Descanso",
            shortName = "Descanso",
            belongedDependency = dependencyDemoData[3],
            releaseDate = Instant.parse("2023-02-10T10:00:00Z")
        ),
        Section(
            id = 7,
            name = "Atención al Empleado",
            shortName = "Atención",
            belongedDependency = dependencyDemoData[4],
            releaseDate = Instant.parse("2023-03-05T11:00:00Z")
        ),
        Section(
            id = 8,
            name = "Prototipado Rápido",
            shortName = "Prototipado",
            belongedDependency = dependencyDemoData[5],
            releaseDate = Instant.parse("2023-01-20T10:00:00Z")
        ),
        Section(
            id = 9,
            name = "Área de Presentaciones",
            shortName = "Presentaciones",
            belongedDependency = dependencyDemoData[6],
            releaseDate = Instant.parse("2023-02-15T09:30:00Z")
        ),
        Section(
            id = 10,
            name = "Área de Redes",
            shortName = "Redes",
            belongedDependency = dependencyDemoData[7],
            releaseDate = Instant.parse("2023-03-01T08:45:00Z")
        )
    )
}