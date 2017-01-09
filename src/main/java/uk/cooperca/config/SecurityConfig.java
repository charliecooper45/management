package uk.cooperca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uk.cooperca.auth.JwtAuthFilter;
import uk.cooperca.auth.JwtAuthenticationEntryPoint;
import uk.cooperca.auth.JwtAuthenticationProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthEndPoint;

    private final String[] patterns = new String[]{
            "/api/login",
            "/bower_components/**/*",
            "/app/**/*",
            "/resources/**/*",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO: csrf
        http.csrf().ignoringAntMatchers("/**/*");

        http.authorizeRequests()
                .antMatchers(patterns)
                .permitAll()
                .antMatchers("/**/*")
                .hasRole("USER")
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEndPoint);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth)  throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }
}
