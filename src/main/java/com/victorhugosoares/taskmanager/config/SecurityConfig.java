package com.victorhugosoares.taskmanager.config;
// Define o pacote onde esta classe está localizada.

import com.victorhugosoares.taskmanager.repository.UserRepository;
// Importa o repositório de usuários para buscar informações no banco de dados.

import lombok.RequiredArgsConstructor;
// Importa a anotação do Lombok que gera um construtor com os atributos obrigatórios (final).

import org.springframework.context.annotation.Bean;
// Importa a anotação para definir métodos que produzem beans gerenciados pelo Spring.

import org.springframework.context.annotation.Configuration;
// Marca esta classe como uma classe de configuração do Spring.

import org.springframework.security.authentication.AuthenticationManager;
// Importa a interface que gerencia o processo de autenticação.

import org.springframework.security.authentication.AuthenticationProvider;
// Importa a interface que fornece a lógica de autenticação.

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// Importa a implementação do `AuthenticationProvider` que usa o banco de dados para autenticação.

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// Importa a classe que fornece a configuração de autenticação.

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// Importa a classe para configurar a segurança baseada na web.

import org.springframework.security.config.http.SessionCreationPolicy;
// Importa a enumeração que define a política de criação de sessões.

import org.springframework.security.core.userdetails.UserDetailsService;
// Importa a interface que carrega os detalhes do usuário para autenticação.

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// Importa a classe que implementa a criptografia de senhas usando o algoritmo BCrypt.

import org.springframework.security.crypto.password.PasswordEncoder;
// Importa a interface para codificação de senhas.

import org.springframework.security.web.SecurityFilterChain;
// Importa a interface que define a cadeia de filtros de segurança.

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// Importa o filtro padrão de autenticação baseado em nome de usuário e senha.

@Configuration
// Marca esta classe como uma classe de configuração do Spring.

@RequiredArgsConstructor
// Gera um construtor com os atributos obrigatórios (final), facilitando a injeção de dependências.

public class SecurityConfig {
    // Classe de configuração de segurança do Spring Security.

    private final JwtAuthFilter jwtAuthFilter;
    // Dependência para o filtro JWT, que será usado para autenticar as requisições.

    private final UserRepository userRepository;
    // Dependência para buscar informações de usuários no banco de dados.

    // Define como o usuário será buscado no banco de dados pelo e-mail.
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado" + email));
        // Retorna um `UserDetailsService` que busca o usuário pelo e-mail no banco de dados.
    }

    // Define como o usuário será autenticado.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // Usa o `DaoAuthenticationProvider` para autenticação baseada em banco de dados.

        provider.setUserDetailsService(userDetailsService());
        // Configura o `UserDetailsService` para buscar os detalhes do usuário.

        provider.setPasswordEncoder(passwordEncoder());
        // Configura o codificador de senhas para comparar senhas criptografadas.

        return provider;
        // Retorna o provedor de autenticação configurado.
    }

    // AuthController caso queira autenticar o usuário com o AuthenticationManager (manualmente).
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
        // Retorna o gerenciador de autenticação configurado.
    }

    // Configurações de segurança da API.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            // Desabilita a proteção CSRF (Cross-Site Request Forgery), já que estamos usando APIs REST.

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/register", "/auth/login", "/h2-console/**").permitAll()
                // Permite acesso público aos endpoints de registro, login e ao console do H2.

                .anyRequest().authenticated()
                // Exige autenticação para todas as outras requisições.
            )

            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Define a política de criação de sessões como `STATELESS`, já que estamos usando JWT.

            .headers().frameOptions().disable()
            // Permite exibir o console do H2 no navegador.

            .and()
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
            // Adiciona o filtro JWT antes do filtro padrão de autenticação baseado em nome de usuário e senha.

        return http.build();
        // Retorna a cadeia de filtros configurada.
    }

    // Configura o codificador de senhas.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        // Retorna uma instância do codificador BCrypt para criptografar senhas.
    }
}