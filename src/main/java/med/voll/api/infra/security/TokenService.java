package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
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

    public String getSubject(String token){
        try {
//            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
//                    .withIssuer("API volmed")
//                    .build()
//                    .verify(token)
//                    .getSubject();
            System.out.println(JWT.decode(token).getSubject());
            return JWT.decode(token).getSubject();
        } catch (JWTVerificationException ex){
            throw new RuntimeException("Erro ao validar token", ex);
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00"));
    }

}
