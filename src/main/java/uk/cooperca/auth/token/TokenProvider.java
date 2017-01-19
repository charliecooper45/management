package uk.cooperca.auth.token;

import org.springframework.security.core.Authentication;

/**
 * Interface for a token provider which manages tokens.
 *
 * @author Charlie Cooper
 */
public interface TokenProvider {

    String generateToken(Authentication authentication);

    Authentication getAuthentication(String token);

    boolean validateToken(String token);
}
