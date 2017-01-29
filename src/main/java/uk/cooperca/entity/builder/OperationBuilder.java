package uk.cooperca.entity.builder;

import uk.cooperca.entity.Application;
import uk.cooperca.entity.Operation;
import uk.cooperca.entity.Script;

/**
 * Builder for the {@link Operation} class.
 *
 * @author Charlie Cooper
 */
public class OperationBuilder extends AbstractBuilder<Operation> {

    private String name;
    private Application application;
    private Script script;

    public OperationBuilder() {}

    public OperationBuilder(Operation operation) {
        super(operation);
        this.name = operation.getName();
        this.application = operation.getApplication();
        this.script = operation.getScript();
    }

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

    @Override
    public Operation build() {
        return new Operation(id, name, application, script);
    }
}