package uk.cooperca.entity.builder;

import uk.cooperca.entity.Server;

/**
 * Builder for the {@link Server} class.
 *
 * @author Charlie Cooper
 */
public class ServerBuilder extends AbstractBuilder<Server> {

    private String name;

    public ServerBuilder() {}

    public ServerBuilder(Server server) {
        super(server);
        this.name = server.getName();
    }

    public ServerBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Server build() {
        return new Server(id, name);
    }
}
