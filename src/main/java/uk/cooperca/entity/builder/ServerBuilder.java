package uk.cooperca.entity.builder;

import uk.cooperca.entity.Server;

/**
 * Builder for the {@link Server} class.
 *
 * @author Charlie Cooper
 */
public class ServerBuilder {

    private String name;

    public ServerBuilder name(String name) {
        this.name = name;
        return this;
    }

    public Server build() {
        return new Server(name);
    }
}
