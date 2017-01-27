package uk.cooperca.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Properties for the management application.
 *
 * @author Charlie Cooper
 */
@ConfigurationProperties(prefix = "management-app", ignoreUnknownFields = false)
@Component
public class ManagementProperties {

    private final Security security = new Security();

    public Security getSecurity() {
        return security;
    }

    public static class Security {

        private final Token token = new Token();

        public Token getToken() {
            return token;
        }

        public static class Token {

            private int tokenValidity;
            private boolean generateOnRestart;
            private String token;

            public int getTokenValidity() {
                return tokenValidity;
            }

            public void setTokenValidity(int tokenValidity) {
                this.tokenValidity = tokenValidity;
            }

            public boolean isGenerateOnRestart() {
                return generateOnRestart;
            }

            public void setGenerateOnRestart(boolean generateOnRestart) {
                this.generateOnRestart = generateOnRestart;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }
        }
    }
}
