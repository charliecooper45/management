package uk.cooperca.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.cooperca.auth.token.TokenProvider;
import uk.cooperca.dto.CredentialsDTO;

import javax.servlet.http.HttpServletResponse;

import static java.util.Collections.singletonMap;

/**
 * Rest controller for logging users in.
 *
 * @author Charlie Cooper
 */
@RestController
@RequestMapping("/api/login")
public class LoginResource {

    public static final String TOKEN = "Token";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping
    public ResponseEntity login(@RequestBody CredentialsDTO credentials,
                                HttpServletResponse response) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            response.setHeader(TOKEN, tokenProvider.generateToken(authentication));
            return ResponseEntity.ok().build();
        } catch (AuthenticationException exception) {
            return new ResponseEntity<>(singletonMap("AuthenticationException", exception.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
}
