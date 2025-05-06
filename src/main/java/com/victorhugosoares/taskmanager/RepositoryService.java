package com.victorhugosoares.taskmanager;
// Define o pacote onde esta interface está localizada.

import com.victorhugosoares.taskmanager.entity.User;
// Importa a entidade `User`, que representa os usuários no banco de dados.

import org.springframework.data.jpa.repository.JpaRepository;
// Importa a interface `JpaRepository`, que fornece métodos padrão para operações no banco de dados.

import java.util.Optional;
// Importa a classe `Optional`, usada para encapsular o resultado de buscas que podem retornar valores nulos.

public interface RepositoryService extends JpaRepository<User, Long> {
    // Interface que representa o repositório da entidade `User`.
    // Estende a interface `JpaRepository`, que fornece métodos padrão como salvar, deletar e buscar entidades.
    // O tipo da entidade é `User` e o tipo da chave primária é `Long`.

    Optional<User> findByEmail(String email);
    // Declara um método personalizado para buscar um usuário pelo e-mail.
    // Retorna um objeto `Optional<User>`, que pode conter o usuário encontrado ou estar vazio caso nenhum usuário seja encontrado.
}
