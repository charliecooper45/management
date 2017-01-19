package uk.cooperca.auth.token.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;
import static org.assertj.core.api.Assertions.*;

public class JWTTokenProviderTest {

    private JWTTokenProvider provider;
    private Authentication authentication;

    @Before
    public void setup() {
        provider = new JWTTokenProvider();
        ReflectionTestUtils.setField(provider, "key", "test-key");
        List<GrantedAuthority> authority = createAuthorityList("ROLE_USER");
        User principal = new User("test_user", "", authority);
        authentication = new UsernamePasswordAuthenticationToken(principal, "", authority);
    }

    @Test
    public void test() {
        ReflectionTestUtils.setField(provider, "tokenValidity", 180);

        // generate a token
        String jwt = provider.generateToken(authentication);
        assertThat(jwt).isNotEmpty();

        // get authentication
        Authentication jwtAuthentication = provider.getAuthentication(jwt);
        assertThat(authentication).isEqualTo(jwtAuthentication);

        // validate the token
        boolean valid = provider.validateToken(jwt);
        assertThat(valid).isTrue();
    }

    @Test(expected = ExpiredJwtException.class)
    public void testTokenExpiration() {
        ReflectionTestUtils.setField(provider, "tokenValidity", 0);

        // generate a token
        String jwt = provider.generateToken(authentication);
        assertThat(jwt).isNotEmpty();

        // validate the token
        provider.validateToken(jwt);
    }
}
