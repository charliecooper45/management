package uk.cooperca.auth;

import org.springframework.security.core.AuthenticationException;

/**
 * Exception thrown when authentication fails.
 *
 * @author Charlie Cooper
 */
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException() {
        super("Failed to verify JWT token");
    }
}
