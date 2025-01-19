# Katherine_Johnson

## Descripción
Este proyecto es una aplicación móvil para gestionar el inventario de productos, permitiendo a los usuarios agregar, eliminar y modificar artículos con facilidad.

## Autores

- Daniel Cortés Sánchez
- Leandro Orellana Martos
- Sergio aguilera ibarra
- 

----------------
## Caracteristica Producto
Esta caracteristica está siendo implementada por Sergio.
Un producto es un objeto físico que será parte de un "Inventario",
un producto puede ser cualquier cosa (coches, productos de limpieza,
alimentos...).

### ¿Qué se ha hecho?
Se ha implementado de forma general:
- Una clase producto.
- Una pantalla para ver el detalle de un producto.
- Una pantalla para la creación y edición de productos y sus correspondientes vista-modelo.
- Una pantalla para listar productos y su correspondiente vista-modelo.
- Un repositorio para obtener los productos almacenados en una infraestructura simulada.
- En comparación con la primera entrega se ha traducido el código fuente al ingles.
- Implementar, junto con la base de la navegación entre pantallas para el proyecto, una navegación
  hacia las pantallas de listar y creación de productos.
- Pantallas de carga para las pantallas de creación, edición y lista de productos mientras se accede a
  los repositorios correspondientes.

### Detalles a tener en cuenta
De cara a la clase productos se han ido implementado los campos correspondientes, sin embargo, cabe resaltar:
1. El campo de la imagen del producto ha sido temporamente asignada como un String hasta que se introduzca una
   clase correspodiente para el manejo de imagenes.
   
2. Dado que se prevee que para ciertos productos, cuando sus stocks se reduzca a cierto mínimo, "salte una alarma"
   que avise de este suceso, se ha implementado una clase abracta ```ProductAlarm``` cuya intención es que en base
   a un criterio se llamen a las funciones correspondientes mediante el uso del patrón observable-observador.

Tambien en comparación con la primera entrega se ha buscado mejorar considerablemente el diseño de las vistas,
ademas de aplicar refactorizaciones para mejorar la limpieza y calidad de código.
