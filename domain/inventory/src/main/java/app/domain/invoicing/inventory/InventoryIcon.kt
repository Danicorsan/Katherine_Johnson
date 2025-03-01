/**
 * Enumeración que representa los diferentes iconos para las categorías de inventario.
 *
 * Los valores de esta enumeración se utilizan para representar diferentes categorías de
 * inventario, como electrónica, tecnología, materiales, servicios y almacén.
 *
 * @property ELECTRONICS Icono para objetos electrónicos.
 * @property TECHNOLOGY Icono para objetos relacionados con la tecnología.
 * @property MATERIALS Icono para objetos materiales.
 * @property SERVICES Icono para objetos relacionados con servicios.
 * @property WAREHOUSE Icono para objetos almacenados en un almacén.
 * @property NONE Icono predeterminado cuando no se especifica una categoría.
 */
enum class InventoryIcon {
    /** Icono para objetos electrónicos. */
    ELECTRONICS,

    /** Icono para objetos relacionados con la tecnología. */
    TECHNOLOGY,

    /** Icono para objetos materiales. */
    MATERIALS,

    /** Icono para objetos relacionados con servicios. */
    SERVICES,

    /** Icono para objetos almacenados en un almacén. */
    WAREHOUSE,

    /** Icono predeterminado cuando no se especifica una categoría. */
    NONE
}