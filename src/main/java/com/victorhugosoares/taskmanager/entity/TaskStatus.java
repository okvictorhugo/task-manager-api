package com.victorhugosoares.taskmanager.entity;
// Define o pacote onde esta enumeração está localizada.

public enum TaskStatus {
    // Define uma enumeração chamada `TaskStatus`, que representa os possíveis status de uma tarefa.

    PENDING,
    // Representa o status "PENDING" (pendente), indicando que a tarefa ainda não foi iniciada.

    IN_PROGRESS,
    // Representa o status "IN_PROGRESS" (em andamento), indicando que a tarefa está sendo executada.

    COMPLETED
    // Representa o status "COMPLETED" (concluída), indicando que a tarefa foi finalizada.
}