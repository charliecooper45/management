package uk.cooperca.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Represents a user of the application.
 *
 * @author Charlie Cooper
 */
@Entity
@Table(name = "user")
public class User implements Serializable, IdentifiableEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator="user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Size(min = 4, max = 50)
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password")
    private String password;

    @NotNull
    @Size(max = 50)
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 50)
    private String lastName;

    @OneToMany(mappedBy = "user")
    private Set<Execution> executions = new HashSet<>();

    private User() {
        // for Hibernate
    }

    public User(Long id, String username, String password, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<Execution> getExecutions() {
        return executions;
    }
}
