package uk.cooperca.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * An operation performed by the application.
 *
 * @author Charlie Cooper
 */
@Entity
@Table(name = "operation")
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    private Operation() {
        // for Hibernate
    }

    Operation(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
