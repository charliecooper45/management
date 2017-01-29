package uk.cooperca.entity.builder;

import uk.cooperca.entity.Application;

/**
 * Builder for the {@link Application} class.
 *
 * @author Charlie Cooper
 */
public class ApplicationBuilder extends AbstractBuilder<Application> {

    private String name;
    private String description;

    public ApplicationBuilder() {}

    public ApplicationBuilder(Application application) {
        super(application);
        this.name = application.getName();
        this.description = application.getDescription();
    }

    public ApplicationBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ApplicationBuilder description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public Application build() {
        return new Application(id, name, description);
    }
}
