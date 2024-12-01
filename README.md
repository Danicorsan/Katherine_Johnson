# Katherine_Johnson

-----
## Producto
Un producto es un objeto físico que será parte de un "Inventario", 
un producto puede ser cualquier cosa (coches, productos de limpieza,
alimentos...).

Un producto guarda la siguiente información:

- Un código para localizar a partir de un texto la localizacion física
    del producto.
- Un nombre.
- Un nombre corto.
- Una descripcion.
- Un número de serie.
- Un código del modelo.
- El tipo de producto.
- La categoria a la que se le asigna.
- La sección en la que se encuentra el producto.
- El estado de si la información del registro de un producto ha sido modificada, 
    verificada o recien creada.
- La cantidad de este producto almacenado.
- El precio de de este.
- Una Imagen del producto.
- La fecha de adquisición del producto.
- La fecha de baja si el registro es dado de baja.
- Se pueden guardar notas acerca de este producto.
- Se puede etiquetar un producto para busquedas rapidas.

Tambien este producto contará con una función de alarma que
avisará cuando la cantidad almacenada de un producto llegue a 
cierto mínimo.

Tambien un producto debe pasar por la verificacion de un administrador, por lo que,
cuando el registro de un producto este establecido en "Nuevo" o "Modificado", estos seran
registros candidatos a ser guardados en el espacio "virtual" del "almacen". Manteniendo los registros
almacenados en ese almacen hasta que un administrador los verifique.

-----