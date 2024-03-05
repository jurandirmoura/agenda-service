package br.com.mutztech.agenda.security.domain.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.mutztech.agenda.security.domain.models.User;

@Service
public class TokenService {
    
    @Value("${api.segurity.toke.secret}")
    private String ChaveSecreta;

    //Gerando Token para o usuario
    public String gerandoToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(ChaveSecreta);
            String token = JWT.create()
            .withIssuer("NOME-DE-SUA-ESCOLHA")
            .withSubject(user.getEmail())
            .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))) //Tempo de vida do Token
            .sign(algorithm);
            return token;

        } catch (JWTCreationException exception) {
           throw new RuntimeException("Error while generating token", exception);
        }
    }

    //Validando Token
    public String validandoToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(ChaveSecreta);
            return JWT.require(algorithm)
            .withIssuer("NOME-DE-SUA-ESCOLHA")
            .build()
            .verify(token)
            .getSubject();
        } catch (JWTVerificationException exception) {
            return "SEM TOKEN";   
        }
    }
}
