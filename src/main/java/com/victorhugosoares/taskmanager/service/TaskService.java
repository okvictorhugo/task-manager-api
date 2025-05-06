package com.victorhugosoares.taskmanager.service;


import com.victorhugosoares.taskmanager.dto.CreateTaskDTO;
// Importa o DTO usado para transferir os dados necessários para criar uma nova tarefa.

import com.victorhugosoares.taskmanager.dto.TaskDTO;
// Importa o DTO usado para transferir os dados de uma tarefa para o cliente.

import com.victorhugosoares.taskmanager.entity.Task;
// Importa a entidade `Task`, que representa as tarefas no banco de dados.

import com.victorhugosoares.taskmanager.entity.TaskStatus;
// Importa a enumeração `TaskStatus`, que representa os possíveis status de uma tarefa.

import com.victorhugosoares.taskmanager.entity.User;
// Importa a entidade `User`, que representa os usuários no banco de dados.

import com.victorhugosoares.taskmanager.mapper.TaskMapper;
// Importa a classe utilitária `TaskMapper`, usada para converter objetos `Task` em `TaskDTO`.

import com.victorhugosoares.taskmanager.repository.TaskRepository;
// Importa o repositório de tarefas, usado para interagir com o banco de dados.

import lombok.RequiredArgsConstructor;
// Importa a anotação do Lombok que gera um construtor com os atributos obrigatórios (final).

import org.springframework.http.HttpStatus;
// Importa a enumeração que define os códigos de status HTTP.

import org.springframework.stereotype.Service;
// Marca esta classe como um serviço gerenciado pelo Spring.

import org.springframework.web.server.ResponseStatusException;
// Importa a exceção usada para retornar erros HTTP personalizados.

import java.util.List;
// Importa a interface `List`, usada para representar listas de objetos.

import java.util.stream.Collectors;
// Importa a classe `Collectors`, usada para transformar streams em coleções.

@Service
// Marca esta classe como um serviço do Spring, permitindo que ela seja injetada em outros componentes.

@RequiredArgsConstructor
// Gera um construtor com os atributos obrigatórios (final), facilitando a injeção de dependências.

public class TaskService {
    // Classe que contém a lógica de negócios relacionada às tarefas.

    private final TaskRepository taskRepository;
    // Dependência para o repositório de tarefas, usada para interagir com o banco de dados.

    public TaskDTO createTask(CreateTaskDTO createTaskDTO, User user) {
        // Método que cria uma nova tarefa associada a um usuário.

        Task task = Task.builder()
                .title(createTaskDTO.getTitle())
                // Define o título da tarefa com base no DTO recebido.

                .description(createTaskDTO.getDescription())
                // Define a descrição da tarefa com base no DTO recebido.

                .user(user)
                // Associa a tarefa ao usuário fornecido.

                .status(TaskStatus.PENDING)
                // Define o status inicial da tarefa como `PENDING`.

                .build();
                // Constrói o objeto `Task` usando o padrão Builder.

        Task savedTask = taskRepository.save(task);
        // Salva a tarefa no banco de dados.

        return TaskMapper.toDTO(savedTask);
        // Converte a tarefa salva em um `TaskDTO` e a retorna.
    }

    public List<TaskDTO> getAllTasks(User user, TaskStatus status) {
        // Método que retorna todas as tarefas de um usuário, com ou sem filtro de status.

        List<Task> tasks = (status == null)
                ? taskRepository.findByUser(user)
                // Busca todas as tarefas do usuário se o status não for fornecido.

                : taskRepository.findByUserAndStatus(user, status);
                // Busca as tarefas do usuário com o status especificado.

        return tasks.stream()
                .map(task -> TaskMapper.toDTO(task))
                // Converte cada tarefa em um `TaskDTO`.

                .collect(Collectors.toList());
                // Retorna a lista de `TaskDTO`.
    }

    public TaskDTO updateTask(Long id, CreateTaskDTO updatedTaskDTO, User user) {
        // Método que atualiza uma tarefa existente.

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task não encontrada"));
                // Busca a tarefa pelo ID. Lança uma exceção 404 se a tarefa não for encontrada.

        if (!task.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            // Lança uma exceção 403 se o usuário autenticado não for o dono da tarefa.
        }

        task.setTitle(updatedTaskDTO.getTitle());
        // Atualiza o título da tarefa com base no DTO recebido.

        task.setDescription(updatedTaskDTO.getDescription());
        // Atualiza a descrição da tarefa com base no DTO recebido.

        Task updatedTask = taskRepository.save(task);
        // Salva as alterações no banco de dados.

        return TaskMapper.toDTO(updatedTask);
        // Converte a tarefa atualizada em um `TaskDTO` e a retorna.
    }

    public void deleteTask(Long id, User user) {
        // Método que exclui uma tarefa existente.

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task não encontrada"));
                // Busca a tarefa pelo ID. Lança uma exceção 404 se a tarefa não for encontrada.

        if (!task.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            // Lança uma exceção 403 se o usuário autenticado não for o dono da tarefa.
        }

        taskRepository.delete(task);
        // Exclui a tarefa do banco de dados.
    }
}