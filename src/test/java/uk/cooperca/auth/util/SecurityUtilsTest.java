package uk.cooperca.auth.util;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.util.ReflectionTestUtils;
import uk.cooperca.entity.User;
import uk.cooperca.entity.builder.UserBuilder;
import uk.cooperca.service.UserService;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SecurityUtilsTest {

    private UserService userService;
    private SecurityUtils securityUtils;
    private SecurityContext securityContext;
    private Authentication authentication;

    @Before
    public void setup() {
        userService = mock(UserService.class);
        securityUtils = new SecurityUtils();
        ReflectionTestUtils.setField(securityUtils, "userService", userService);
        securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        authentication = mock(Authentication.class);
    }

    @Test
    public void testGetCurrentAuthenticatedUserSuccess() {
        String testUsername = "test-user";
        when(authentication.getPrincipal()).thenReturn(
                new org.springframework.security.core.userdetails.User(testUsername, "", Collections.emptyList())
        );
        when(userService.findOneByUsername(testUsername)).thenReturn(
                Optional.of(new UserBuilder().username(testUsername).build())
        );
        when(securityContext.getAuthentication()).thenReturn(authentication);

        Optional<User> user = securityUtils.getCurrentAuthenticatedUser();
        assertThat(user.isPresent()).isTrue();
        assertThat(user.get().getUsername()).isEqualTo(testUsername);
        verify(userService).findOneByUsername(testUsername);
    }

    @Test
    public void testGetCurrentAuthenticatedUserFailure() {
        when(securityContext.getAuthentication()).thenReturn(null);

        Optional<User> user = securityUtils.getCurrentAuthenticatedUser();
        assertThat(user.isPresent()).isFalse();
        verifyZeroInteractions(userService);
    }
}
