package uk.cooperca.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import uk.cooperca.entity.User;

import java.util.Optional;

/**
 * Authentication provider for the JWT token.
 *
 * @author Charlie Cooper
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            Optional<User> user = jwtService.verifyToken((String) authentication.getCredentials());
            return new JwtAuthenticatedProfile(user.get());
        } catch (Exception e) {
            throw new JwtAuthenticationException();
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.equals(authentication);
    }
}
