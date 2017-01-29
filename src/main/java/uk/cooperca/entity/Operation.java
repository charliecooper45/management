package uk.cooperca.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * An operation.
 *
 * @author Charlie Cooper
 */
@Entity
@Table(name = "operation")
public class Operation implements Serializable, IdentifiableEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator="operation_id_seq")
    @SequenceGenerator(name = "operation_id_seq", sequenceName = "operation_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @NotNull
    @ManyToOne
    private Application application;

    @JsonIgnore
    @NotNull
    @ManyToOne
    private Script script;

    @JsonIgnore
    @OneToMany(mappedBy = "operation")
    private Set<Execution> executions = new HashSet<>();

    private Operation() {
        // for Hibernate
    }

    public Operation(Long id, String name, Application application, Script script) {
        this.id = id;
        this.name = name;
        this.application = application;
        this.script = script;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Application getApplication() {
        return application;
    }

    public Script getScript() {
        return script;
    }

    public Set<Execution> getExecutions() {
        return executions;
    }
}
