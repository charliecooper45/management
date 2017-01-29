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
public class Script implements Serializable, IdentifiableEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator="script_id_seq")
    @SequenceGenerator(name = "script_id_seq", sequenceName = "script_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "command")
    private String command;

    @OneToMany(mappedBy = "script")
    private Set<Operation> operations = new HashSet<>();

    private Script() {
        // for Hibernate
    }

    public Script(Long id, String command) {
        this.id = id;
        this.command = command;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }

    public Set<Operation> getOperations() {
        return operations;
    }
}

