package uk.cooperca.auth;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.util.ReflectionTestUtils;
import uk.cooperca.entity.User;
import uk.cooperca.service.UserService;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

public class UserDetailsServiceImplTest {

    private UserDetailsService userDetailsService;
    private UserService userService;

    @Before
    public void setup() {
        userService = mock(UserService.class);
        userDetailsService = new UserDetailsServiceImpl();
        ReflectionTestUtils.setField(userDetailsService, "userService", userService);
    }

    @Test
    public void testUserExists() {
        User user = new User("user1", "", "", "");
        when(userService.findOneByUsername("user1")).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailsService.loadUserByUsername("user1");
        assertThat(userDetails.getUsername()).isEqualTo("user1");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testUserDoesNotExist() {
        when(userService.findOneByUsername("user2")).thenReturn(Optional.empty());

        userDetailsService.loadUserByUsername("user2");
    }
}
