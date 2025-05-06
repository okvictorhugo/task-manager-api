package com.victorhugosoares.taskmanager.dto;
// Define o pacote onde esta classe está localizada.

import java.time.LocalDateTime;
// Importa a classe `LocalDateTime`, usada para representar a data e hora de criação da tarefa.

import com.victorhugosoares.taskmanager.entity.TaskStatus;
// Importa a enumeração `TaskStatus`, que representa o status da tarefa (ex.: PENDING, COMPLETED).

import lombok.Builder;
// Importa a anotação `@Builder` do Lombok, que permite criar objetos desta classe usando o padrão Builder.

import lombok.Data;
// Importa a anotação `@Data` do Lombok, que gera automaticamente os métodos getters, setters, equals, hashCode e toString.

@Data
// Anotação do Lombok que gera automaticamente os métodos getters, setters, equals, hashCode e toString para esta classe.

@Builder
// Anotação do Lombok que permite criar objetos desta classe usando o padrão Builder.

public class TaskDTO {
    // Classe que representa os dados de uma tarefa que serão transferidos para o cliente.

    private Long id;
    // Campo que armazena o identificador único da tarefa.

    private String title;
    // Campo que armazena o título da tarefa.

    private String description;
    // Campo que armazena a descrição da tarefa.

    private TaskStatus status;
    // Campo que armazena o status da tarefa (ex.: PENDING, COMPLETED).

    private LocalDateTime createdAt;
    // Campo que armazena a data e hora de criação da tarefa.
}