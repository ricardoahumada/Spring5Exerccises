CREATE SCHEMA `proyectostareas` ;

USE mysql;

CREATE USER 'proyectos_user'@'%' IDENTIFIED BY 'tareas123';

GRANT ALL PRIVILEGES ON proyectostareas.* TO 'proyectos_user'@'%';
FLUSH PRIVILEGES;