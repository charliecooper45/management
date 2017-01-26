package uk.cooperca.entity.builder;

import uk.cooperca.entity.Application;

/**
 * Builder for the {@link Application} class.
 *
 * @author Charlie Cooper
 */
public class ApplicationBuilder {

    private String name;
    private String description;

    public ApplicationBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ApplicationBuilder description(String description) {
        this.description = description;
        return this;
    }

    public Application build() {
        return new Application(name, description);
    }
}
