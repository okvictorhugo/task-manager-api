package com.victorhugosoares.taskmanager.repository;
// Define o pacote onde esta interface está localizada.

import com.victorhugosoares.taskmanager.entity.Task;
// Importa a entidade `Task`, que representa as tarefas no banco de dados.

import com.victorhugosoares.taskmanager.entity.TaskStatus;
// Importa a enumeração `TaskStatus`, que representa os possíveis status de uma tarefa.

import com.victorhugosoares.taskmanager.entity.User;
// Importa a entidade `User`, que representa os usuários no banco de dados.

import org.springframework.data.jpa.repository.JpaRepository;
// Importa a interface `JpaRepository`, que fornece métodos padrão para operações no banco de dados.

import java.util.List;
// Importa a classe `List`, usada para retornar listas de objetos.

public interface TaskRepository extends JpaRepository<Task, Long> {
    // Interface que representa o repositório da entidade `Task`.
    // Estende a interface `JpaRepository`, que fornece métodos padrão como salvar, deletar e buscar entidades.
    // O tipo da entidade é `Task` e o tipo da chave primária é `Long`.

    List<Task> findByUser(User user);
    // Declara um método personalizado para buscar todas as tarefas associadas a um usuário específico.
    // Retorna uma lista de tarefas (`Task`) pertencentes ao usuário fornecido.

    List<Task> findByUserAndStatus(User user, TaskStatus status);
    // Declara um método personalizado para buscar todas as tarefas associadas a um usuário específico e com um status específico.
    // Retorna uma lista de tarefas (`Task`) pertencentes ao usuário fornecido e com o status fornecido.
}