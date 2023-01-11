package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UsersEntity user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("API volmed")
                    .withSubject(user.getLogin())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException ex){
            throw new RuntimeException("Erro ao gerar token jwt", ex);
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00"));
    }

}
