# Ejercicio: Proyectos - Tareas

Se quiere construir una API libre que permita a un usuario anónimo gestionar proyectos y sus tareas asociadas.

## Historias de usuario:
1. Como usuario anónimo quiero poder crear proyectos, para poder gestionar una lista asociada de tareas.
2. Como usuario anónimo quiero poder añadir tareas a uno de mis proyectos, para tener una lista ordenada de trabajos.
3. Como usuario anónimo quiero poder mostrar la lista de mis proyectos, para decidir cuál gestionar.
4. Como usuario anónimo quiero ver la lista ordenada de tareas de un proyecto, para ejecutarlas en orden.
5. Como usuario quiero poder marcar una tarea como completada, para olvidarme de ella.

## Entidades:
- Proyecto: [id], nombre, fechaDeCreacion, tareas.
- Tarea: [id], descripción, fechaLimite, orden, completada.

## Reto:
- Diseña e implementa la API usando Spring Boot.
	- Crea el proyecto con Spring Initializr.
	- Usa un perfil de desarrollo (dev) con H2, en el puerto 9900.
	- En la capa de persistencia puedes implementar el repositorio JPA o usar un JPARepository (Spring Data) equivalente.
	- En la capa de vista (controlles) no olvides la gestión de excepciones, la validación y documentación.
- Sube tu solución un repositorio en GitHub.
	- Añade cada implementación de historia a una rama "feature/[funcionalidad]".
- Asegura la calidad de la aplicación con test automatizados para todas las capas: persistencia, servicio, web.
	- Puedes usar un perfil de testing (test)
- Extra: Añade un perfil de producción (prod) que use MySql y comunique en protocolo seguro, en el puerto 9443.