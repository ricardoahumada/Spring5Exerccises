package com.banana.proyectostareas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasProyectosServiceApplication {
    private static final Logger logger = LoggerFactory.getLogger(TareasProyectosServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TareasProyectosServiceApplication.class, args);
    }

}