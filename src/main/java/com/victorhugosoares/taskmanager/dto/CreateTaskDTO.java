package com.victorhugosoares.taskmanager.dto;


import lombok.Data;
// Importa a anotação `@Data` do Lombok, que gera automaticamente os métodos getters, setters, equals, hashCode e toString.

import jakarta.validation.constraints.NotBlank;
// Importa a anotação `@NotBlank`, usada para validar que os campos não sejam nulos ou vazios.

@Data
// Anotação do Lombok que gera automaticamente os métodos getters, setters, equals, hashCode e toString para esta classe.

public class CreateTaskDTO {
    // Classe que representa os dados necessários para criar uma nova tarefa.

    @NotBlank
    // Valida que o campo `title` não pode ser nulo ou vazio.

    private String title;
    // Campo que armazena o título da tarefa.

    @NotBlank
    // Valida que o campo `description` não pode ser nulo ou vazio.

    private String description;
    // Campo que armazena a descrição da tarefa.
}