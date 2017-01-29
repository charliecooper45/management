package uk.cooperca.entity.builder;

import uk.cooperca.entity.IdentifiableEntity;

/**
 * Abstract builder class that contains common behaviour.
 *
 * @author Charlie Cooper
 */
public abstract class AbstractBuilder<T extends IdentifiableEntity> {

    protected Long id;

    public AbstractBuilder() {}

    public AbstractBuilder(T t) {
        id = t.getId();
    }

    public Long getId() {
        return id;
    }

    public abstract T build();
}
