package com.victorhugosoares.taskmanager.service;
import com.victorhugosoares.taskmanager.dto.CreateUserDTO;
import com.victorhugosoares.taskmanager.dto.UserDTO;
import com.victorhugosoares.taskmanager.entity.User;
import com.victorhugosoares.taskmanager.repository.UserRepository;      
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {
    
    private final UserRepository userRepository; 
    
    @Transactional //anotação para indicar que o método deve ser executado dentro de uma transação
    public UserDTO createUser (CreateUserDTO createUserDTO) {//injetando o repositório de usuário

        if (userRepository .existsByEmail(createUserDTO.getEmail())) { //verificando se o email já existe
            throw new RuntimeException("Email já existe"); //se existir, lança uma exceção
        }

        if (userRepository.existsByName(createUserDTO.getName())) { //verificando se o username já existe
            throw new RuntimeException("Username já está em uso"); //se existir, lança uma exceção
        }

        // Cria um novo usuário com os dados do DTO
        User user = User.builder() //construindo o usuário com o builder
            .name(createUserDTO.getName()) //setando o username
            .password(createUserDTO.getPassword()) //setando a senha
            .email(createUserDTO.getEmail()) //setando o email  
            .build();      //construindo o usuário com o builder

        User savedUser = userRepository.save(user); //salvando o usuário no banco de dados
        
        return UserDTO.builder() //retornando o usuário com os dados do DTO
            .id(savedUser.getId()) //setando o id do usuário
            .username(savedUser.getUsername()) //setando o username do usuário
            .email(savedUser.getEmail()) //setando o email do usuário
            .build(); //construindo o usuário com o builder

    } //fim do método createUser
}
