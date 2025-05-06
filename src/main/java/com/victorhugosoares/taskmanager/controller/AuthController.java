package com.victorhugosoares.taskmanager.controller;
// Define o pacote onde esta classe está localizada.

import com.victorhugosoares.taskmanager.entity.User;
// Importa a entidade `User`, que representa os usuários no sistema.

import com.victorhugosoares.taskmanager.repository.UserRepository;
// Importa o repositório de usuários para interagir com o banco de dados.

import com.victorhugosoares.taskmanager.util.JwtUtil;
// Importa a classe utilitária para manipulação de tokens JWT.

import jakarta.validation.Valid;
// Importa a anotação para validar os dados recebidos no corpo da requisição.

import lombok.RequiredArgsConstructor;
// Importa a anotação do Lombok que gera um construtor com os atributos obrigatórios (final).

import org.springframework.http.HttpStatus;
// Importa a enumeração que define os códigos de status HTTP.

import org.springframework.http.ResponseEntity;
// Importa a classe que encapsula a resposta HTTP.

import org.springframework.security.crypto.password.PasswordEncoder;
// Importa a interface para codificação de senhas.

import org.springframework.web.bind.annotation.*;
// Importa as anotações para definir controladores e endpoints.

import com.victorhugosoares.taskmanager.dto.CreateUserDTO;
// Importa o DTO usado para criar um novo usuário.

import com.victorhugosoares.taskmanager.dto.LoginRequest;
// Importa o DTO usado para receber os dados de login.

import com.victorhugosoares.taskmanager.dto.LoginResponseDTO;
// Importa o DTO usado para retornar a resposta de login.

import com.victorhugosoares.taskmanager.dto.UserDTO;
// Importa o DTO usado para retornar os dados do usuário.

import java.util.Optional;
// Importa a classe para manipulação de objetos opcionais.

@RestController
// Marca esta classe como um controlador REST, permitindo que ela manipule requisições HTTP.

@RequestMapping("/auth")
// Define o caminho base para os endpoints deste controlador.

@RequiredArgsConstructor
// Gera um construtor com os atributos obrigatórios (final), facilitando a injeção de dependências.

public class AuthController {
    // Controlador responsável por gerenciar a autenticação e registro de usuários.

    private final UserRepository userRepository;
    // Dependência para interagir com o banco de dados de usuários.

    private final PasswordEncoder passwordEncoder;
    // Dependência para codificar e verificar senhas.

    private final JwtUtil jwtUtil;
    // Dependência para manipular tokens JWT.

    @PostMapping("/login")
    // Define o endpoint para login, acessível via método HTTP POST no caminho "/auth/login".

    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Método que processa a requisição de login.

        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
        // Busca o usuário no banco de dados pelo e-mail fornecido.

        if (optionalUser.isEmpty()) {
            // Verifica se o usuário não foi encontrado.

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail não encontrado");
            // Retorna uma resposta HTTP 401 (Não autorizado) com a mensagem de erro.
        }

        User user = optionalUser.get();
        // Obtém o objeto `User` do `Optional`.

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            // Verifica se a senha fornecida não corresponde à senha armazenada.

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
            // Retorna uma resposta HTTP 401 (Não autorizado) com a mensagem de erro.
        }

        String token = jwtUtil.generateToken(user.getEmail());
        // Gera um token JWT para o usuário autenticado.

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .username(user.getName())
                .email(user.getEmail())
                .build();
        // Cria um DTO com os dados do usuário para retornar na resposta.

        LoginResponseDTO response = LoginResponseDTO.builder()
                .token(token)
                .user(userDTO)
                .build();
        // Cria um DTO com o token e os dados do usuário para retornar na resposta.

        return ResponseEntity.ok(response);
        // Retorna uma resposta HTTP 200 (OK) com o DTO de resposta.
    }

    @PostMapping("/register")
    // Define o endpoint para registro de usuários, acessível via método HTTP POST no caminho "/auth/register".

    public ResponseEntity<?> register(@Valid @RequestBody CreateUserDTO createUserDTO) {
        // Método que processa a requisição de registro de um novo usuário.

        if (userRepository.existsByEmail(createUserDTO.getEmail())) {
            // Verifica se já existe um usuário com o e-mail fornecido.

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail já está em uso");
            // Retorna uma resposta HTTP 400 (Requisição inválida) com a mensagem de erro.
        }

        if (userRepository.existsByName(createUserDTO.getName())) {
            // Verifica se já existe um usuário com o nome fornecido.

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já está em uso");
            // Retorna uma resposta HTTP 400 (Requisição inválida) com a mensagem de erro.
        }

        User user = User.builder()
                .name(createUserDTO.getName())
                .email(createUserDTO.getEmail())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .build();
        // Cria um novo objeto `User` com os dados fornecidos e codifica a senha.

        userRepository.save(user);
        // Salva o novo usuário no banco de dados.

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso");
        // Retorna uma resposta HTTP 201 (Criado) com a mensagem de sucesso.
    }
}