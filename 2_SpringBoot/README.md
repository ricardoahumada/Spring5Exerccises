# 2. SpringBoot

### 2.1. Actualiza el proyecto para:
	- incorporar las dependencias de SpringBoot necesarias para que sea un proyecto web.
	- incorporar el archivo de propiedades yaml para conectarse a la base de datos mysql.
	- definir el nivel de log a INFO
### 2.2. Implementa:
	- 3 endpoints: "/songs", "/users" y "/orders" que devuelvan 
	- un interceptor para añadir un request param "date" con la fecha actual.
	- un filtro para denegar el acceso a la ruta "/user" (Response code 403).
### 2.3. Configura el proyecto para usar dos perfiles: dev (datasource H2) y prod (datasource mysql).
	- Limpia los perfiles antiguos para usar los nuevos 
