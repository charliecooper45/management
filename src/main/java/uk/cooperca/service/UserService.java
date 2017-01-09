package uk.cooperca.service;

import org.springframework.stereotype.Service;
import uk.cooperca.dto.LoginCredentials;
import uk.cooperca.entity.User;
import uk.cooperca.entity.UserBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Service for managing user login requests.
 *
 * @author Charlie Cooper
 */
@Service
public class UserService {

    private static final Map<String, User> DB = new HashMap<>();

    static {
        DB.put("greenrabbit948", new UserBuilder()
                .firstName("Fred")
                .lastName("Smith")
                .username("greenrabbit948")
                .password("celeste")
                .build());
        DB.put("test", new UserBuilder()
                .firstName("Test")
                .lastName("Test")
                .username("test")
                .password("test")
                .build());
    }

    public Optional<User> login(LoginCredentials credentials) {
        User user = DB.get(credentials.getUsername());
        if (user != null && user.getPassword().equals(credentials.getPassword())) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public Optional<User> getUser(String username) {
        User user = DB.get(username);
        if (user != null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
