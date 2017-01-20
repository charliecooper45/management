package uk.cooperca.auth.token.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import uk.cooperca.auth.token.TokenProvider;
import uk.cooperca.config.properties.ManagementProperties;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static java.time.ZoneOffset.UTC;
import static java.util.UUID.randomUUID;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

/**
 * {@link TokenProvider} implementation for JWT tokens.
 *
 * @author Charlie Cooper
 */
@Component
public class JWTTokenProvider implements TokenProvider {

    @Autowired
    private ManagementProperties managementProperties;

    private String key;
    private int tokenValidity;

    @PostConstruct
    public void init() {
        // upon restart we invalidate all sessions
        key = randomUUID().toString();
        tokenValidity = managementProperties.getSecurity().getToken().getTokenValidity();
    }

    public String generateToken(Authentication authentication) {
        Date validity = Date.from(LocalDateTime.now().plusSeconds(tokenValidity).toInstant(UTC));

        return Jwts.builder()
                .setSubject(authentication.getName())
                .signWith(HS512, key)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        List<GrantedAuthority> authorities = createAuthorityList("ROLE_USER");
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token);
        } catch (SignatureException e) {
            return false;
        }
        return true;
    }
}

