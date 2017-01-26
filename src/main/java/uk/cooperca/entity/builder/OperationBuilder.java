package uk.cooperca.entity.builder;

import uk.cooperca.entity.Application;
import uk.cooperca.entity.Operation;
import uk.cooperca.entity.Script;

/**
 * Builder for the {@link Operation} class.
 *
 * @author Charlie Cooper
 */
public class OperationBuilder {

    private String name;
    private Application application;
    private Script script;

    public OperationBuilder name(String name) {
        this.name = name;
        return this;
    }

    public OperationBuilder application(Application application) {
        this.application = application;
        return this;
    }

    public OperationBuilder script(Script script) {
        this.script = script;
        return this;
    }

    public Operation build() {
        return new Operation(name, application, script);
    }
}