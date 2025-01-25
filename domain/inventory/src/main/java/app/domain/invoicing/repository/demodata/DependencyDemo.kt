package app.domain.invoicing.repository.demodata

import app.domain.invoicing.dependency.Dependency

internal fun dependencyDemoData() : MutableList<Dependency> {
    return mutableListOf(
        Dependency(
            id = 1,
            name = "Almacén Principal",
            shortName = "Almacén",
            description = "Espacio destinado para el almacenamiento de materiales y productos."
        ),
        Dependency(
            id = 2,
            name = "Sala de Reuniones",
            shortName = "Reuniones",
            description = "Sala equipada para realizar reuniones con capacidad para 12 personas."
        ),
        Dependency(
            id = 3,
            name = "Cuarto de Baño Planta Baja",
            shortName = "Baño PB",
            description = "Cuarto de baño ubicado en la planta baja, con acceso para personas con movilidad reducida."
        ),
        Dependency(
            id = 4,
            name = "Sala de Café Planta Alta",
            shortName = "Café PA",
            description = "Área común con máquinas de café, microondas y espacio para descanso."
        ),
        Dependency(
            id = 5,
            name = "Oficina de Recursos Humanos",
            shortName = "RH",
            description = "Oficina administrativa destinada al departamento de Recursos Humanos."
        ),
        Dependency(
            id = 6,
            name = "Laboratorio de Innovación",
            shortName = "Lab Innovación",
            description = "Espacio para proyectos de innovación, equipado con herramientas de prototipado."
        ),
        Dependency(
            id = 7,
            name = "Auditorio Principal",
            shortName = "Auditorio",
            description = "Espacio amplio con capacidad para 100 personas, diseñado para eventos y presentaciones."
        ),
        Dependency(
            id = 8,
            name = "Cuarto de Servidores",
            shortName = "Servidores",
            description = "Sala especializada para servidores y equipos de telecomunicaciones, con acceso restringido."
        )
    )
}