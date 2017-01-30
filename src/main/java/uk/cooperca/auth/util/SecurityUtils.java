package uk.cooperca.auth.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import uk.cooperca.entity.User;
import uk.cooperca.service.UserService;

import java.util.Optional;

/**
 * Utility class for security and authentication.
 *
 * @author Charlie Cooper
 */
@Component
public class SecurityUtils {

    @Autowired
    private UserService userService;

    public Optional<User> getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        return userService.findOneByUsername(username);
    }
}
