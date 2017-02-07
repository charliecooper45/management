package uk.cooperca.config;

import org.springframework.context.annotation.Bean;

public class CommandConfig {

    @Bean
    public Runtime runtime() {
        return Runtime.getRuntime();
    }
}
