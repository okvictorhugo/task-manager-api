package com.victorhugosoares.taskmanager;


import org.springframework.boot.SpringApplication;
// Importa a classe SpringApplication, que é usada para iniciar a aplicação Spring Boot.

import org.springframework.boot.autoconfigure.SpringBootApplication;
// Importa a anotação @SpringBootApplication, que configura automaticamente a aplicação Spring Boot.

@SpringBootApplication
// Marca esta classe como a classe principal da aplicação Spring Boot. 
// Combina as anotações @Configuration, @EnableAutoConfiguration e @ComponentScan.

public class TaskManagerApplication {
    // Define a classe principal da aplicação.

    public static void main(String[] args) {
        // O método main é o ponto de entrada da aplicação Java.

        SpringApplication.run(TaskManagerApplication.class, args);
        // Inicia a aplicação Spring Boot. Configura o contexto da aplicação e inicia o servidor embutido (como Tomcat).
    }
}
