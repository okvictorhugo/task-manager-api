package com.victorhugosoares.taskmanager;
// Define o pacote onde esta classe está localizada.

import com.victorhugosoares.taskmanager.dto.CreateTaskDTO;
// Importa o DTO usado para transferir os dados necessários para criar ou atualizar uma tarefa.

import com.victorhugosoares.taskmanager.dto.TaskDTO;
// Importa o DTO usado para transferir os dados de uma tarefa para o cliente.

import com.victorhugosoares.taskmanager.entity.TaskStatus;
// Importa a enumeração `TaskStatus`, que representa os possíveis status de uma tarefa.

import com.victorhugosoares.taskmanager.entity.User;
// Importa a entidade `User`, que representa os usuários no sistema.

import com.victorhugosoares.taskmanager.service.TaskService;
// Importa o serviço de tarefas, que contém a lógica de negócios relacionada às tarefas.

import jakarta.validation.Valid;
// Importa a anotação `@Valid`, usada para validar os dados recebidos no corpo da requisição.

import lombok.RequiredArgsConstructor;
// Importa a anotação do Lombok que gera um construtor com os atributos obrigatórios (final).

import org.springframework.http.HttpStatus;
// Importa a enumeração que define os códigos de status HTTP.

import org.springframework.http.ResponseEntity;
// Importa a classe que encapsula a resposta HTTP.

import org.springframework.security.core.annotation.AuthenticationPrincipal;
// Importa a anotação que injeta o usuário autenticado no método.

import org.springframework.web.bind.annotation.*;
// Importa as anotações para definir controladores e endpoints.

import java.util.List;
// Importa a interface `List`, usada para representar listas de objetos.

@RestController
// Marca esta classe como um controlador REST, permitindo que ela manipule requisições HTTP.

@RequestMapping("/tasks")
// Define o caminho base para os endpoints deste controlador. Todas as requisições começarão com "/tasks".

@RequiredArgsConstructor
// Gera um construtor com os atributos obrigatórios (final), facilitando a injeção de dependências.

public class TaskController {
    // Controlador responsável por gerenciar as operações relacionadas às tarefas.

    private final TaskService taskService;
    // Dependência para o serviço de tarefas, que contém a lógica de negócios.

    @PostMapping
    // Define o endpoint para criar uma nova tarefa, acessível via método HTTP POST.

    public ResponseEntity<TaskDTO> createTask(
            @Valid @RequestBody CreateTaskDTO createTaskDTO,
            // Recebe os dados da nova tarefa no corpo da requisição e valida com a anotação `@Valid`.

            @AuthenticationPrincipal User user) {
            // Injeta automaticamente o usuário autenticado no método.

        TaskDTO taskDTO = taskService.createTask(createTaskDTO, user);
        // Chama o serviço para criar a tarefa e retorna um objeto `TaskDTO` com os dados da tarefa criada.

        return new ResponseEntity<>(taskDTO, HttpStatus.CREATED);
        // Retorna uma resposta HTTP 201 (Criado) com o DTO da tarefa criada.
    }

    @GetMapping
    // Define o endpoint para listar todas as tarefas do usuário autenticado, acessível via método HTTP GET.

    public ResponseEntity<List<TaskDTO>> getAllTasks(
            @AuthenticationPrincipal User user,
            // Injeta automaticamente o usuário autenticado no método.

            @RequestParam(required = false) TaskStatus status) {
            // Recebe um parâmetro opcional `status` para filtrar as tarefas pelo status.

        return ResponseEntity.ok(taskService.getAllTasks(user, status));
        // Chama o serviço para buscar as tarefas e retorna uma resposta HTTP 200 (OK) com a lista de tarefas.
    }

    @PutMapping("/{id}")
    // Define o endpoint para atualizar uma tarefa existente, acessível via método HTTP PUT.

    public ResponseEntity<TaskDTO> updateTask(
            @PathVariable Long id,
            // Recebe o ID da tarefa como parte da URL.

            @Valid @RequestBody CreateTaskDTO updatedTaskDTO,
            // Recebe os dados atualizados da tarefa no corpo da requisição e valida com a anotação `@Valid`.

            @AuthenticationPrincipal User user) {
            // Injeta automaticamente o usuário autenticado no método.

        return ResponseEntity.ok(taskService.updateTask(id, updatedTaskDTO, user));
        // Chama o serviço para atualizar a tarefa e retorna uma resposta HTTP 200 (OK) com o DTO da tarefa atualizada.
    }

    @DeleteMapping("/{id}")
    // Define o endpoint para excluir uma tarefa existente, acessível via método HTTP DELETE.

    public ResponseEntity<Void> deleteTask(
            @PathVariable Long id,
            // Recebe o ID da tarefa como parte da URL.

            @AuthenticationPrincipal User user) {
            // Injeta automaticamente o usuário autenticado no método.

        taskService.deleteTask(id, user);
        // Chama o serviço para excluir a tarefa.

        return ResponseEntity.noContent().build();
        // Retorna uma resposta HTTP 204 (Sem conteúdo) indicando que a tarefa foi excluída com sucesso.
    }
}