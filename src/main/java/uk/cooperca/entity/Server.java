package uk.cooperca.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * A server.
 *
 * @author Charlie Cooper
 */
@Entity
@Table(name = "server")
public class Server implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator="server_id_seq")
    @SequenceGenerator(name = "server_id_seq", sequenceName = "server_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "server_application",
            joinColumns = @JoinColumn(name="server_id"),
            inverseJoinColumns = @JoinColumn(name="application_id")
    )
    private Set<Application> applications = new HashSet<>();

    private Server() {
        // for Hibernate
    }

    public Server(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Application> getApplications() {
        return applications;
    }
}
