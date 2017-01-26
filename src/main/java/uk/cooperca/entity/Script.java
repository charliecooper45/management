package uk.cooperca.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * A script.
 *
 * @author Charlie Cooper
 */
@Entity
@Table(name = "script")
public class Script implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator="path_id_seq")
    @SequenceGenerator(name = "path_id_seq", sequenceName = "path_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "path")
    private String path;

    @OneToMany(mappedBy = "script")
    private Set<Operation> operations = new HashSet<>();

    private Script() {
        // for Hibernate
    }

    public Script(String path) {
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public Set<Operation> getOperations() {
        return operations;
    }
}

