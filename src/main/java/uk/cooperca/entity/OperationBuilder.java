package uk.cooperca.entity;

/**
 * Builder for the {@link Operation} class.
 */
public class OperationBuilder {
    private String name;

    public OperationBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Operation createOperation() {
        return new Operation(name);
    }
}