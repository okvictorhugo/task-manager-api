package com.victorhugosoares.taskmanager.controller;
// Define o pacote onde esta classe está localizada.

import com.victorhugosoares.taskmanager.dto.CreateUserDTO;
// Importa o DTO usado para receber os dados de criação de um novo usuário.

import com.victorhugosoares.taskmanager.dto.UserDTO;
// Importa o DTO usado para retornar os dados do usuário.

import com.victorhugosoares.taskmanager.entity.User;
// Importa a entidade `User`, que representa os usuários no sistema.

import com.victorhugosoares.taskmanager.service.UserService;
// Importa o serviço de usuário, que contém a lógica de negócios relacionada aos usuários.

import jakarta.validation.Valid;
// Importa a anotação para validar os dados recebidos no corpo da requisição.

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

@RestController
// Indica que esta classe é um controlador REST, permitindo que ela manipule requisições HTTP.

@RequestMapping("/api/users")
// Define o caminho base para os endpoints deste controlador. Todas as requisições começarão com "/api/users".

@RequiredArgsConstructor
// Gera um construtor com os atributos obrigatórios (final), facilitando a injeção de dependências.

public class UserController {
    // Controlador responsável por gerenciar as operações relacionadas aos usuários.

    private final UserService userService;
    // Dependência para o serviço de usuário, que contém a lógica de negócios.

    @PostMapping
    // Define o endpoint para criar um novo usuário, acessível via método HTTP POST.

    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        // Método que processa a requisição para criar um novo usuário.
        // Recebe um objeto `CreateUserDTO` no corpo da requisição e valida os dados com a anotação `@Valid`.

        UserDTO userDTO = userService.createUser(createUserDTO);
        // Chama o serviço para criar o usuário e retorna um objeto `UserDTO` com os dados do usuário criado.

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        // Retorna uma resposta HTTP 201 (Criado) com o DTO do usuário criado.
    }

    @GetMapping("/me")
    // Define o endpoint para obter os dados do usuário autenticado, acessível via método HTTP GET no caminho "/api/users/me".

    public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal User user) {
        // Método que retorna os dados do usuário autenticado.
        // O usuário autenticado é injetado automaticamente pelo Spring Security usando a anotação `@AuthenticationPrincipal`.

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .username(user.getName())
                .email(user.getEmail())
                .build();
        // Cria um objeto `UserDTO` com os dados do usuário autenticado.

        return ResponseEntity.ok(userDTO);
        // Retorna uma resposta HTTP 200 (OK) com o DTO do usuário autenticado.
    }
}