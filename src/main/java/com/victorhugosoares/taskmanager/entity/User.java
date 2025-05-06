package com.victorhugosoares.taskmanager.entity;
// Define o pacote onde esta classe está localizada.

import jakarta.persistence.*;
// Importa as anotações para mapeamento da classe como uma entidade JPA.

import jakarta.validation.constraints.Email;
// Importa a anotação `@Email`, usada para validar se o campo contém um endereço de e-mail válido.

import jakarta.validation.constraints.NotBlank;
// Importa a anotação `@NotBlank`, usada para validar que os campos não sejam nulos ou vazios.

import jakarta.validation.constraints.Size;
// Importa a anotação `@Size`, usada para validar o tamanho mínimo e máximo de strings.

import lombok.*;
// Importa as anotações do Lombok para gerar automaticamente métodos e construtores.

import org.springframework.security.core.GrantedAuthority;
// Importa a interface que representa uma autoridade concedida (role) no Spring Security.

import org.springframework.security.core.userdetails.UserDetails;
// Importa a interface que define os métodos necessários para representar um usuário no Spring Security.

import java.util.Collection;
// Importa a interface `Collection`, usada para representar coleções de objetos.

import java.util.Collections;
// Importa a classe `Collections`, usada para criar coleções imutáveis.

import java.util.List;
// Importa a interface `List`, usada para representar listas de objetos.

@Entity
// Marca esta classe como uma entidade JPA, indicando que ela será mapeada para uma tabela no banco de dados.

@Table(name = "users")
// Define o nome da tabela no banco de dados como "users".

@Data
// Anotação do Lombok que gera automaticamente os métodos getters, setters, equals, hashCode e toString.

@NoArgsConstructor
// Anotação do Lombok que gera um construtor sem argumentos.

@AllArgsConstructor
// Anotação do Lombok que gera um construtor com todos os argumentos.

@Builder
// Anotação do Lombok que permite criar objetos desta classe usando o padrão Builder.

public class User implements UserDetails {
    // Classe que representa a entidade `User`, que será mapeada para a tabela "users" no banco de dados.
    // Implementa a interface `UserDetails` para integração com o Spring Security.

    @Id
    // Marca o campo `id` como a chave primária da tabela.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Define que o valor do campo `id` será gerado automaticamente pelo banco de dados.

    private Long id;
    // Campo que armazena o identificador único do usuário.

    @NotBlank
    // Valida que o campo `name` não pode ser nulo ou vazio.

    @Size(min = 3, max = 50)
    // Valida que o campo `name` deve ter entre 3 e 50 caracteres.

    private String name;
    // Campo que armazena o nome do usuário.

    @NotBlank
    // Valida que o campo `password` não pode ser nulo ou vazio.

    @Size(min = 6, max = 100)
    // Valida que o campo `password` deve ter entre 6 e 100 caracteres.

    @Column(nullable = false)
    // Define que o campo `password` não pode ser nulo no banco de dados.

    private String password;
    // Campo que armazena a senha do usuário.

    @NotBlank
    // Valida que o campo `email` não pode ser nulo ou vazio.

    @Email
    // Valida que o campo `email` deve conter um endereço de e-mail válido.

    @Column(nullable = false, unique = true)
    // Define que o campo `email` não pode ser nulo e deve ser único no banco de dados.

    private String email;
    // Campo que armazena o e-mail do usuário.

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    // Define um relacionamento `OneToMany` com a entidade `Task`.
    // O atributo `mappedBy` indica que o relacionamento é mapeado pelo campo `user` na entidade `Task`.
    // O `cascade = CascadeType.ALL` garante que operações na entidade `User` sejam propagadas para as tarefas associadas.
    // O `orphanRemoval = true` garante que tarefas órfãs (sem usuário associado) sejam removidas automaticamente.

    private List<Task> tasks;
    // Campo que armazena a lista de tarefas associadas ao usuário.

    // Métodos obrigatórios do UserDetails:

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
        // Retorna uma lista vazia de autoridades (roles), já que roles não foram implementadas.
    }

    @Override
    public String getUsername() {
        return this.email;
        // Retorna o e-mail do usuário como o "username" para login.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        // Indica que a conta do usuário não está expirada.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        // Indica que a conta do usuário não está bloqueada.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        // Indica que as credenciais do usuário (senha) não estão expiradas.
    }

    @Override
    public boolean isEnabled() {
        return true;
        // Indica que a conta do usuário está habilitada.
    }
}