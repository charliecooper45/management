package uk.cooperca.entity.builder;

import uk.cooperca.entity.Execution;
import uk.cooperca.entity.Operation;
import uk.cooperca.entity.User;

import java.time.LocalDateTime;

/**
 * Builder for the {@link Execution} class.
 *
 * @author Charlie Cooper
 */
public class ExecutionBuilder {

    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private Operation operation;
    private User user;

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

    public Execution build() {
        return new Execution(startTime, finishTime, operation, user);
    }
}
