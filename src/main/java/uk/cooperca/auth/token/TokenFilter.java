package uk.cooperca.auth.token;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * Filters incoming requests and installs a Spring Security principal if a valid token is provided.
 *
 * @author Charlie Cooper
 */
public class TokenFilter extends GenericFilterBean {

    public static final String BEARER = "Bearer ";

    private TokenProvider tokenProvider;

    public TokenFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            String authorization = servletRequest.getHeader(AUTHORIZATION);
            if (authorization != null) {
                authorization = authorization.replaceAll(BEARER, "");
                if (tokenProvider.validateToken(authorization)) {
                    Authentication authentication = tokenProvider.getAuthentication(authorization);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(servletRequest, response);
        } catch (ExpiredJwtException eje) {
            ((HttpServletResponse) response).setStatus(SC_UNAUTHORIZED);
        }
    }
}
