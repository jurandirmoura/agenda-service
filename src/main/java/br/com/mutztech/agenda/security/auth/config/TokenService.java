package br.com.mutztech.agenda.security.auth.config;

import br.com.mutztech.agenda.security.auth.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {


    private  String secret = "_Ju04R@08D!R_";

  public String generateToken(User user){
      try {
          Algorithm algorithm = Algorithm.HMAC256(secret);

          String token = JWT.create()
                  .withIssuer("auth")
                  .withSubject(user.getEmail())
                  .withExpiresAt(getExpirationDate())
                  .sign(algorithm);

          return token;

      } catch (JWTCreationException exception){
          throw new RuntimeException("ERROR WHILE GENERATING TOKEN", exception);
      }
  }

  public String validateToken(String token){
    try {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.require(algorithm)
                .withIssuer("auth")
                .build()
                .verify(token)
                .getSubject();

    } catch (JWTCreationException exception){
        return "";
    }
  }

    private Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
