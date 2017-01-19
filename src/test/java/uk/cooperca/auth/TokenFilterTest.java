package uk.cooperca.auth;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import uk.cooperca.auth.token.TokenFilter;
import uk.cooperca.auth.token.TokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static uk.cooperca.auth.token.TokenFilter.BEARER;

public class TokenFilterTest {

    private TokenFilter filter;
    private TokenProvider provider;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;

    @Before
    public void setup() {
        provider = mock(TokenProvider.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);
        filter = new TokenFilter(provider);
    }

    @Test
    public void testNoToken() throws Exception {
        when(request.getHeader(AUTHORIZATION)).thenReturn(null);

        filter.doFilter(request, response, filterChain);

        verifyZeroInteractions(provider);
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testValidToken() throws Exception {
        String testToken = "test-token";
        when(request.getHeader(AUTHORIZATION)).thenReturn(BEARER + testToken);
        when(provider.validateToken(testToken)).thenReturn(true);
        when(provider.getAuthentication(testToken)).thenReturn(mock(Authentication.class));

        filter.doFilter(request, response, filterChain);

        verify(provider).validateToken(testToken);
        verify(provider).getAuthentication(testToken);
        verifyNoMoreInteractions(provider);
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testInvalidToken() throws Exception {
        String testToken = "test-invalid-token";
        when(request.getHeader(AUTHORIZATION)).thenReturn(BEARER + testToken);
        when(provider.validateToken(testToken)).thenReturn(false);

        filter.doFilter(request, response, filterChain);

        verify(provider).validateToken(testToken);
        verifyNoMoreInteractions(provider);
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testExpiredToken() throws Exception {
        String testToken = "test-invalid-token";
        when(request.getHeader(AUTHORIZATION)).thenReturn(BEARER + testToken);
        when(provider.validateToken(testToken)).thenThrow(mock(ExpiredJwtException.class));

        filter.doFilter(request, response, filterChain);

        verify(provider).validateToken(testToken);
        verifyNoMoreInteractions(provider);
        verify(response).setStatus(SC_UNAUTHORIZED);
        verifyZeroInteractions(filterChain);
    }
}
