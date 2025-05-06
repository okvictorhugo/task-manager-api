package com.victorhugosoares.taskmanager.util;
// Define o pacote onde esta classe está localizada.

import io.jsonwebtoken.Jwts;
// Importa a classe `Jwts`, usada para criar e validar tokens JWT.

import io.jsonwebtoken.security.Keys;
// Importa a classe `Keys`, usada para gerar e manipular chaves de assinatura.

import org.springframework.stereotype.Component;
// Importa a anotação `@Component`, que marca esta classe como um componente gerenciado pelo Spring.

import java.security.Key;
// Importa a interface `Key`, usada para representar a chave de assinatura.

import java.util.Date;
// Importa a classe `Date`, usada para definir as datas de emissão e expiração do token.

@Component
// Marca esta classe como um componente do Spring, permitindo que ela seja injetada em outros componentes.

public class JwtUtil {
    // Classe utilitária responsável por gerar e validar tokens JWT.

    // Chave secreta fixa (substitua por uma chave segura em produção)
    private final Key key = Keys.hmacShaKeyFor(
        "supersegredosegredosegredosegredosegredosegre".getBytes()
        // Gera uma chave HMAC-SHA com base na string fornecida.
        // A string deve ter pelo menos 256 bits (32 caracteres) para ser válida.
    );

    public String generateToken(String email) {
        // Método que gera um token JWT com base no e-mail fornecido.

        return Jwts.builder()
                .setSubject(email)
                // Define o e-mail como o "subject" (assunto) do token.

                .setIssuedAt(new Date())
                // Define a data de emissão do token como a data atual.

                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                // Define a data de expiração do token (1 hora a partir da emissão).

                .signWith(key)
                // Assina o token usando a chave secreta.

                .compact();
                // Constrói e retorna o token JWT como uma string compactada.
    }

    public String validateTokenAndGetEmail(String token) {
        // Método que valida o token JWT e retorna o e-mail contido no "subject".

        return Jwts.parserBuilder()
                .setSigningKey(key)
                // Configura a chave secreta usada para validar o token.

                .build()
                // Constrói o parser para validar o token.

                .parseClaimsJws(token)
                // Valida o token e retorna as "claims" (informações contidas no token).

                .getBody()
                // Obtém o corpo das "claims".

                .getSubject();
                // Retorna o "subject" (e-mail) contido no token.
    }
}