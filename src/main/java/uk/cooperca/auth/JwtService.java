package uk.cooperca.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.cooperca.entity.User;
import uk.cooperca.service.UserService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static java.time.ZoneOffset.UTC;

/**
 * Creates a JWT token for a user.
 *
 * @author Charlie Cooper
 */
@Service
public class JwtService {

    private static final String ISSUER = "management-test";

    @Autowired
    private UserService userService;

    public String generateToken(String username) {
        // TODO: security
        byte[] secretKey = "shhh".getBytes();
        Date expiration = Date.from(LocalDateTime.now().plusHours(2).toInstant(UTC));
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration)
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Optional<User> verifyToken(String token) {
        byte[] secretKey = "shhh".getBytes();
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return userService.getUser(claims.getBody().getSubject());
    }
}
