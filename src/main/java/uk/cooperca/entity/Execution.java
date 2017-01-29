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
public class Execution implements Serializable, IdentifiableEntity {

    private static final long serialVersionUID = 1L;

    public enum Status {
        STARTED, FAILED, FINISHED
    }

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

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    private Execution() {
        // for Hibernate
    }

    public Execution(Long id, LocalDateTime startTime, LocalDateTime finishTime, Operation operation, User user, Status status) {
        this.id = id;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.operation = operation;
        this.user = user;
        this.status = status;
    }

    @Override
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

    public Status getStatus() {
        return status;
    }
}
