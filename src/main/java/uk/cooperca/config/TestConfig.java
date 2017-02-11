package uk.cooperca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.*;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public Runtime runtime() {
        return mock(Runtime.class);
    }
}
