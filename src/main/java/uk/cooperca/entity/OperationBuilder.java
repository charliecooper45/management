package uk.cooperca.entity;

/**
 * Builder for the {@link Operation} class.
 */
public class OperationBuilder {

    private String name;

    public OperationBuilder name(String name) {
        this.name = name;
        return this;
    }

    public Operation build() {
        return new Operation(name);
    }
}