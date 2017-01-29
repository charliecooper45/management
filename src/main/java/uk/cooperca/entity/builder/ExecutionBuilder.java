package uk.cooperca.entity.builder;

import uk.cooperca.entity.Execution;
import uk.cooperca.entity.Operation;
import uk.cooperca.entity.User;

import java.time.LocalDateTime;

import static uk.cooperca.entity.Execution.Status;

/**
 * Builder for the {@link Execution} class.
 *
 * @author Charlie Cooper
 */
public class ExecutionBuilder extends AbstractBuilder<Execution> {

    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private Operation operation;
    private User user;
    private Status status;

    public ExecutionBuilder() {}

    public ExecutionBuilder(Execution execution) {
        this.id = execution.getId();
        this.startTime = execution.getStartTime();
        this.finishTime = execution.getFinishTime();
        this.operation = execution.getOperation();
        this.user = execution.getUser();
        this.status = execution.getStatus();
    }

    public ExecutionBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ExecutionBuilder startTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public ExecutionBuilder finishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    public ExecutionBuilder operation(Operation operation) {
        this.operation = operation;
        return this;
    }

    public ExecutionBuilder user(User user) {
        this.user = user;
        return this;
    }

    public ExecutionBuilder status(Status status) {
        this.status = status;
        return this;
    }

    @Override
    public Execution build() {
        return new Execution(id, startTime, finishTime, operation, user, status);
    }
}
