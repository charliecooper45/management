package uk.cooperca.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * An application.
 *
 * @author Charlie Cooper
 */
@Entity
@Table(name = "application")
public class Application implements Serializable, IdentifiableEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator="application_id_seq")
    @SequenceGenerator(name = "application_id_seq", sequenceName = "application_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "applications")
    private Set<Server> servers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "application")
    private Set<Operation> operations = new HashSet<>();

    private Application() {
        // for Hibernate
    }

    public Application(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<Server> getServers() {
        return servers;
    }

    public Set<Operation> getOperations() {
        return operations;
    }
}

