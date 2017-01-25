package uk.cooperca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.cooperca.entity.User;
import uk.cooperca.repository.UserRepository;

import java.util.Optional;

/**
 * Service for {@link User}s.
 *
 * @author Charlie Cooper
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }
}
