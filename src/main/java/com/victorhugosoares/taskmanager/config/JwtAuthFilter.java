package com.victorhugosoares.taskmanager.config;


import com.victorhugosoares.taskmanager.entity.User;
// Importa a classe `User`, que representa a entidade de usuário.

import com.victorhugosoares.taskmanager.repository.UserRepository;
// Importa o repositório de usuários para buscar informações no banco de dados.

import com.victorhugosoares.taskmanager.util.JwtUtil;
// Importa a classe utilitária para manipulação de tokens JWT.

import org.springframework.security.core.authority.SimpleGrantedAuthority;
// Importa a classe que representa uma autoridade simples (papel/role) no Spring Security.

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// Importa classes relacionadas ao processamento de requisições HTTP e filtros.

import lombok.RequiredArgsConstructor;
// Importa a anotação do Lombok que gera um construtor com os atributos obrigatórios (final).

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// Importa a classe que representa o token de autenticação no Spring Security.

import org.springframework.security.core.context.SecurityContextHolder;
// Importa a classe que gerencia o contexto de segurança do Spring Security.

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// Importa a classe que fornece detalhes de autenticação baseados na requisição.

import org.springframework.stereotype.Component;
// Marca esta classe como um componente gerenciado pelo Spring.

import org.springframework.web.filter.OncePerRequestFilter;
// Importa a classe base para filtros que são executados uma vez por requisição.

import java.io.IOException;
import java.util.Optional;
import java.util.Collections;
// Importa classes para manipulação de objetos opcionais e coleções.

@Component
// Marca esta classe como um componente do Spring, permitindo que ela seja injetada em outros lugares.

@RequiredArgsConstructor
// Gera um construtor com os atributos obrigatórios (final), facilitando a injeção de dependências.

public class JwtAuthFilter extends OncePerRequestFilter {
    // Define um filtro que será executado uma vez por requisição para verificar o token JWT.

    private final JwtUtil jwtUtil;
    // Dependência para manipulação e validação de tokens JWT.

    private final UserRepository userRepository;
    // Dependência para buscar informações de usuários no banco de dados.

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Método principal do filtro, executado para cada requisição.

        String authHeader = request.getHeader("Authorization");
        // Obtém o cabeçalho "Authorization" da requisição HTTP.

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Verifica se o cabeçalho existe e se começa com "Bearer ", indicando um token JWT.

            String token = authHeader.substring(7);
            // Remove o prefixo "Bearer " para obter apenas o token.

            try {
                String email = jwtUtil.validateTokenAndGetEmail(token);
                // Valida o token e extrai o e-mail do usuário associado.

                Optional<User> optionalUser = userRepository.findByEmail(email);
                // Busca o usuário no banco de dados pelo e-mail extraído do token.

                if (optionalUser.isPresent()) {
                    // Verifica se o usuário foi encontrado.

                    User user = optionalUser.get();
                    // Obtém o objeto `User` do `Optional`.

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            user, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
                    // Cria um token de autenticação para o usuário com a role "ROLE_USER".

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // Adiciona detalhes da requisição ao token de autenticação.

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    // Define o token de autenticação no contexto de segurança do Spring Security.
                }

            } catch (Exception e) {
                // Captura qualquer exceção que ocorra durante a validação do token.

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                // Define o status HTTP como 401 (Não autorizado).

                response.getWriter().write("Token inválido ou expirado: " + e.getMessage());
                // Escreve uma mensagem de erro na resposta HTTP.

                return;
                // Interrompe o processamento da requisição.
            }
        }

        filterChain.doFilter(request, response);
        // Continua o processamento da requisição, passando para o próximo filtro ou endpoint.
    }
}