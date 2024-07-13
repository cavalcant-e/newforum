package com.cavalcante.forumhub.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cavalcante.forumhub.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${senhaParaGerarJWT}")
    private String secreto;

    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secreto);
            return JWT.create()
                    .withIssuer("forumhub")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    private Instant dataExpiracao() {

        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String pegarSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secreto);
            return JWT.require(algoritmo)
                    .withIssuer("forumhub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }

 }
    public String pegarIDClaim(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secreto);
          var verifier = JWT.require(algoritmo)
                    .withIssuer("forumhub")
                    .build();
            var decodedJWT = verifier.verify(tokenJWT);
           String id = decodedJWT.getClaim("id").asString();

            System.out.println("imprindo o pegarIDClaim: ID " + id);

            return id;

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }


}}



