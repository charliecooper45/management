package uk.cooperca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandConfig {

    @Bean
    public Runtime runtime() {
        return Runtime.getRuntime();
    }
}
