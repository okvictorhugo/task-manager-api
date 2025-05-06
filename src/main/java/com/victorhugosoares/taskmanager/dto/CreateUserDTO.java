package com.victorhugosoares.taskmanager.dto;
// Define o pacote onde esta classe está localizada.

import jakarta.validation.constraints.Email;
// Importa a anotação `@Email`, usada para validar se o campo contém um endereço de e-mail válido.

import jakarta.validation.constraints.NotBlank;
// Importa a anotação `@NotBlank`, usada para validar que os campos não sejam nulos ou vazios.

import jakarta.validation.constraints.Size;
// Importa a anotação `@Size`, usada para validar o tamanho mínimo e máximo de strings.

import lombok.Data;
// Importa a anotação `@Data` do Lombok, que gera automaticamente os métodos getters, setters, equals, hashCode e toString.

@Data
// Anotação do Lombok que gera automaticamente os métodos getters, setters, equals, hashCode e toString para esta classe.

public class CreateUserDTO {
    // Classe que representa os dados necessários para criar um novo usuário.

    @NotBlank(message = "Necessario informar usuario")
    // Valida que o campo `name` não pode ser nulo ou vazio.
    // Exibe a mensagem personalizada "Necessario informar usuario" caso a validação falhe.

    @Size(min = 3, max = 20, message = "Usuario deve ter entre 3 e 20 caracteres")
    // Valida que o campo `name` deve ter entre 3 e 20 caracteres.
    // Exibe a mensagem personalizada "Usuario deve ter entre 3 e 20 caracteres" caso a validação falhe.

    private String name;
    // Campo que armazena o nome do usuário.

    @NotBlank(message = "É necessario informar e-mail")
    // Valida que o campo `email` não pode ser nulo ou vazio.
    // Exibe a mensagem personalizada "É necessario informar email" caso a validação falhe.

    @Email(message = "E-mail deve ser valido")
    // Valida que o campo `email` deve conter um endereço de e-mail válido.
    // Exibe a mensagem personalizada "Email deve ser valido" caso a validação falhe.

    private String email;
    // Campo que armazena o e-mail do usuário.

    @NotBlank(message = "Senha deve ser informada")
    // Valida que o campo `password` não pode ser nulo ou vazio.
    // Exibe a mensagem personalizada "Senha deve ser informada" caso a validação falhe.

    @Size(min = 6, message = "Senha deve ter no minimo 6 caracteres")
    // Valida que o campo `password` deve ter no mínimo 6 caracteres.
    // Exibe a mensagem personalizada "Senha deve ter no minimo 6 caracteres" caso a validação falhe.

    private String password;
    // Campo que armazena a senha do usuário.
}