package uk.cooperca.entity;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

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
    @GeneratedValue(strategy = SEQUENCE, generator="operation_id_seq")
    @SequenceGenerator(name = "operation_id_seq", sequenceName = "operation_id_seq", allocationSize = 1)
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
