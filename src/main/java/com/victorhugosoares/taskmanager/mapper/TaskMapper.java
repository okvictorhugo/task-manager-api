package com.victorhugosoares.taskmanager.mapper;
// Define o pacote onde esta classe está localizada.

import com.victorhugosoares.taskmanager.dto.TaskDTO;
// Importa a classe `TaskDTO`, que representa os dados transferidos de uma tarefa.

import com.victorhugosoares.taskmanager.entity.Task;
// Importa a classe `Task`, que representa a entidade de tarefa no banco de dados.

import lombok.experimental.UtilityClass;
// Importa a anotação `@UtilityClass` do Lombok, que transforma a classe em uma classe utilitária.

@UtilityClass
// Anotação do Lombok que transforma esta classe em uma classe utilitária.
// Isso significa que:
// - A classe será final.
// - Todos os métodos serão estáticos.
// - Um construtor privado será gerado automaticamente para evitar instâncias.

public class TaskMapper {
    // Classe utilitária responsável por converter objetos da entidade `Task` para o DTO `TaskDTO`.

    public static TaskDTO toDTO(Task task) {
        // Método estático que converte um objeto `Task` em um objeto `TaskDTO`.

        return TaskDTO.builder()
                .id(task.getId())
                // Define o ID da tarefa no DTO.

                .title(task.getTitle())
                // Define o título da tarefa no DTO.

                .description(task.getDescription())
                // Define a descrição da tarefa no DTO.

                .status(task.getStatus())
                // Define o status da tarefa no DTO.

                .createdAt(task.getCreatedAt())
                // Define a data e hora de criação da tarefa no DTO.

                .build();
                // Constrói e retorna o objeto `TaskDTO` usando o padrão Builder.
    }
}