package uk.cooperca.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * An execution.
 *
 * @author Charlie Cooper
 */
@Entity
@Table(name = "execution")
public class Execution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator="execution_id_seq")
    @SequenceGenerator(name = "execution_id_seq", sequenceName = "execution_id_seq", allocationSize = 1)
    private Long id;

    @Column(name="start_time")
    private LocalDateTime startTime;

    @Column(name="finish_time")
    private LocalDateTime finishTime;

    @NotNull
    @ManyToOne
    private Operation operation;

    @NotNull
    @ManyToOne
    private User user;

    private Execution() {
        // for Hibernate
    }

    public Execution(LocalDateTime startTime, LocalDateTime finishTime, Operation operation, User user) {
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.operation = operation;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public Operation getOperation() {
        return operation;
    }

    public User getUser() {
        return user;
    }
}
