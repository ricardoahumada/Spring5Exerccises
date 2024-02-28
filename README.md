# Ejercicio: Proyectos - Tareas

Se quiere construir una API libre que permita a un usuario anónimo gestionar proyectos y sus tareas asociadas.

## Historias de usuario:
1. Como usuario anónimo quiero poder crear proyectos para poder gestionar una lista asociada de tareas.
2. Como usuario anónimo quiero poder añadir tareas a uno de mis proyectos para tener una lista ordenada de trabajos.
3. Como usuario anónimo quiero poder mostrar la lista de mis proyectos para decidir cuál gestionar.
4. Como usuario anónimo quiero ver la lista de tareas de un proyecto para ejecutarlas en ese orden.
5. Como usuario quiero poder marcar una tarea como completada para olvidarme de ella.

## Entidades:
- Proyecto: [id], nombre, fechaDeCreacion, tareas.
- Tarea: [id], descripción, fechaLimite, orden, completada.

## Reto:
- Crea un repositorio en GitHub.
- Diseña e implementa la API usando Spring Boot.
	- Usa un perfil de desarrollo con H2 (dev)
	- En la capa de persistencia se pude implementar el repositorio JPA o usar un JPARepository equivalente.
	- En la capa de vista (controlles) no olvides la gestión de excepciones, la validación y documentación.
	- Añade cada tarea a una rama "feature/[funcionalidad]".
- Asegura la calidad de la aplicación con test automatizados para todas las capas: persistencia, servicio, web.
- Añade un perfil de producción (prod) que use MySql.