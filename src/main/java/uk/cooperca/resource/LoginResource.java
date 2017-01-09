package uk.cooperca.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.cooperca.dto.LoginCredentials;
import uk.cooperca.entity.User;
import uk.cooperca.auth.JwtService;
import uk.cooperca.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Rest controller for logging users in.
 *
 * @author Charlie Cooper
 */
// TODO: tests
@RestController
@RequestMapping("/api/login")
public class LoginResource {

    @Autowired
    private UserService userService;

    @Autowired
    public JwtService jwtService;

    @PostMapping
    public ResponseEntity<User> login(@RequestBody LoginCredentials credentials,
                                      HttpServletResponse response) {
        Optional<User> optional = userService.login(credentials);
        if (optional.isPresent()) {
            User user = optional.get();
            response.setHeader("Token", jwtService.generateToken(user.getUsername()));
            return ResponseEntity.ok(user);
        }
        // TODO: message
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
