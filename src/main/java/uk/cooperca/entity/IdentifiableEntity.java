package uk.cooperca.entity;

/**
 * Interface for entities with a primary key of type long.
 *
 * @author Charlie Cooper
 */
public interface IdentifiableEntity {

    /**
     * Gets the primary key from the entity.
     *
     * @return the primary key.
     */
    public Long getId();
}
