package uk.cooperca.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uk.cooperca.service.UserService;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

/**
 * Authenticates a user.
 *
 * @author Charlie Cooper
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getUser(username)
                .map(user -> new User(user.getUsername(), user.getPassword(), createAuthorityList("ROLE_USER")))
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }
}
