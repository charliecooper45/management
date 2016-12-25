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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
