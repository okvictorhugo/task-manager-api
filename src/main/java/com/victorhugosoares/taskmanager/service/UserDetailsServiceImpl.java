package com.victorhugosoares.taskmanager.service;
// Define o pacote onde esta classe está localizada.

import com.victorhugosoares.taskmanager.repository.UserRepository;
// Importa o repositório de usuários, usado para buscar informações no banco de dados.

import lombok.RequiredArgsConstructor;
// Importa a anotação do Lombok que gera um construtor com os atributos obrigatórios (final).

import org.springframework.security.core.userdetails.UserDetails;
// Importa a interface `UserDetails`, que representa os detalhes de um usuário para autenticação.

import org.springframework.security.core.userdetails.UserDetailsService;
// Importa a interface `UserDetailsService`, que define o contrato para carregar os detalhes de um usuário.

import org.springframework.security.core.userdetails.UsernameNotFoundException;
// Importa a exceção lançada quando um usuário não é encontrado.

import org.springframework.stereotype.Service;
// Marca esta classe como um serviço gerenciado pelo Spring.

@Service
// Marca esta classe como um serviço do Spring, permitindo que ela seja injetada em outros componentes.

@RequiredArgsConstructor
// Gera um construtor com os atributos obrigatórios (final), facilitando a injeção de dependências.

public class UserDetailsServiceImpl implements UserDetailsService {
    // Classe que implementa a interface `UserDetailsService` para carregar os detalhes de um usuário.
    // É usada pelo Spring Security para autenticar usuários.

    private final UserRepository userRepository;
    // Dependência para o repositório de usuários, usada para buscar informações no banco de dados.

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Método que carrega os detalhes de um usuário com base no e-mail fornecido.
        // É chamado pelo Spring Security durante o processo de autenticação.

        return userRepository.findByName(email)
                // Busca o usuário no banco de dados pelo e-mail fornecido.

                .orElseThrow(() -> new UsernameNotFoundException("Usuario com email não encontrado: " + email));
                // Lança uma exceção `UsernameNotFoundException` se o usuário não for encontrado.
    }
}