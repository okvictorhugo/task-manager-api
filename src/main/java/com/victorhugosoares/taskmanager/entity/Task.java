package com.victorhugosoares.taskmanager.entity;
// Define o pacote onde esta classe está localizada.

import jakarta.persistence.*;
// Importa as anotações para mapeamento da classe como uma entidade JPA.

import jakarta.validation.constraints.NotBlank;
// Importa a anotação `@NotBlank`, usada para validar que os campos não sejam nulos ou vazios.

import lombok.*;
// Importa as anotações do Lombok para gerar automaticamente métodos e construtores.

import com.fasterxml.jackson.annotation.JsonIgnore;
// Importa a anotação `@JsonIgnore`, usada para evitar a serialização de determinados campos.

import java.time.LocalDateTime;
// Importa a classe `LocalDateTime`, usada para representar a data e hora.

@Entity
// Marca esta classe como uma entidade JPA, indicando que ela será mapeada para uma tabela no banco de dados.

@Table(name = "tasks")
// Define o nome da tabela no banco de dados como "tasks".

@Data
// Anotação do Lombok que gera automaticamente os métodos getters, setters, equals, hashCode e toString.

@NoArgsConstructor
// Anotação do Lombok que gera um construtor sem argumentos.

@AllArgsConstructor
// Anotação do Lombok que gera um construtor com todos os argumentos.

@Builder
// Anotação do Lombok que permite criar objetos desta classe usando o padrão Builder.

public class Task {
    // Classe que representa a entidade `Task`, que será mapeada para a tabela "tasks" no banco de dados.

    @Id
    // Marca o campo `id` como a chave primária da tabela.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Define que o valor do campo `id` será gerado automaticamente pelo banco de dados.

    private Long id;
    // Campo que armazena o identificador único da tarefa.

    @NotBlank(message = "Title is required")
    // Valida que o campo `title` não pode ser nulo ou vazio.
    // Exibe a mensagem personalizada "Title is required" caso a validação falhe.

    private String title;
    // Campo que armazena o título da tarefa.

    @NotBlank(message = "Description is required")
    // Valida que o campo `description` não pode ser nulo ou vazio.
    // Exibe a mensagem personalizada "Description is required" caso a validação falhe.

    private String description;
    // Campo que armazena a descrição da tarefa.

    @Enumerated(EnumType.STRING)
    // Define que o campo `status` será armazenado como uma string no banco de dados.

    private TaskStatus status;
    // Campo que armazena o status da tarefa (ex.: PENDING, COMPLETED).

    private LocalDateTime createdAt;
    // Campo que armazena a data e hora de criação da tarefa.

    @ManyToOne(fetch = FetchType.LAZY)
    // Define um relacionamento `ManyToOne` com a entidade `User`.
    // O carregamento é definido como `LAZY`, ou seja, os dados do usuário só serão carregados quando acessados.

    @JoinColumn(name = "user_id")
    // Define a coluna `user_id` como a chave estrangeira que referencia a tabela de usuários.

    @JsonIgnore
    // Evita que o campo `user` seja serializado em respostas JSON, prevenindo erros de serialização em relacionamentos `LAZY`.

    private User user;
    // Campo que armazena o usuário associado à tarefa.

    @PrePersist
    // Método anotado com `@PrePersist` será executado antes de a entidade ser persistida no banco de dados.

    public void prePersist() {
        createdAt = LocalDateTime.now();
        // Define o campo `createdAt` com a data e hora atual antes de salvar a entidade.

        if (status == null) {
            status = TaskStatus.PENDING;
            // Define o status padrão como `PENDING` caso ele não tenha sido definido.
        }
    }
}