package com.victorhugosoares.taskmanager.dto;
// Define o pacote onde esta classe está localizada.

import lombok.Data;
// Importa a anotação `@Data` do Lombok, que gera automaticamente os métodos getters, setters, equals, hashCode e toString.

@Data
// Anotação do Lombok que gera automaticamente os métodos getters, setters, equals, hashCode e toString para esta classe.

public class LoginRequest {
    // Classe que representa os dados necessários para realizar o login de um usuário.

    private String email;
    // Campo que armazena o e-mail do usuário para autenticação.

    private String password;
    // Campo que armazena a senha do usuário para autenticação.
}

