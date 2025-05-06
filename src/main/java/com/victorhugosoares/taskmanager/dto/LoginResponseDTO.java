package com.victorhugosoares.taskmanager.dto;
// Define o pacote onde esta classe está localizada.

import lombok.Builder;
// Importa a anotação `@Builder` do Lombok, que permite criar objetos desta classe usando o padrão Builder.

import lombok.Data;
// Importa a anotação `@Data` do Lombok, que gera automaticamente os métodos getters, setters, equals, hashCode e toString.

@Data
// Anotação do Lombok que gera automaticamente os métodos getters, setters, equals, hashCode e toString para esta classe.

@Builder
// Anotação do Lombok que permite criar objetos desta classe usando o padrão Builder.

public class LoginResponseDTO {
    // Classe que representa a resposta enviada ao cliente após um login bem-sucedido.

    private String token;
    // Campo que armazena o token JWT gerado para o usuário autenticado.

    private UserDTO user;
    // Campo que armazena os dados do usuário autenticado, representados por um objeto `UserDTO`.
}