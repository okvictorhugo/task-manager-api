package com.victorhugosoares.taskmanager.dto;
// Define o pacote onde esta classe está localizada.

import lombok.Data;
// Importa a anotação `@Data` do Lombok, que gera automaticamente os métodos getters, setters, equals, hashCode e toString.

import lombok.Builder;
// Importa a anotação `@Builder` do Lombok, que permite criar objetos desta classe usando o padrão Builder.

@Data
// Anotação do Lombok que gera automaticamente os métodos getters, setters, equals, hashCode e toString para esta classe.

@Builder
// Anotação do Lombok que permite criar objetos desta classe usando o padrão Builder.

public class UserDTO {
    // Classe que representa os dados de um usuário que serão transferidos para o cliente.

    private Long id;
    // Campo que armazena o identificador único do usuário.

    private String username;
    // Campo que armazena o nome de usuário.

    private String email;
    // Campo que armazena o endereço de e-mail do usuário.
}