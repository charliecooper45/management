package uk.cooperca.command;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.cooperca.auth.util.SecurityUtils;
import uk.cooperca.entity.Execution;
import uk.cooperca.entity.Operation;
import uk.cooperca.entity.Script;
import uk.cooperca.entity.User;
import uk.cooperca.entity.builder.ExecutionBuilder;
import uk.cooperca.service.OperationService;
import uk.cooperca.service.ExecutionService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.MILLIS;
import static uk.cooperca.entity.Execution.Status.*;

/**
 * Executes the script for the given operation.
 *
 * @author Charlie Cooper
 */
@Component
public class CommandExecutor {

    private final Logger logger = LoggerFactory.getLogger(CommandExecutor.class);

    @Autowired
    private OperationService operationService;

    @Autowired
    private ExecutionService executionService;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private Runtime runtime;

    /**
     * Executes the given operation.
     *
     * @param operationId the id of the operation.
     *
     * @return true if the execution was a success and false otherwise.
     */
    public boolean executeOperation(long operationId) {
        Operation operation = Optional.ofNullable(operationService.findOne(operationId))
                .orElseThrow(() -> new IllegalArgumentException("operation with id " + operationId + " does not exist"));
        User user = securityUtils.getCurrentAuthenticatedUser()
                .orElseThrow(() -> new IllegalArgumentException("no authenticated user found"));
        return execute(user, operation);
    }

    private boolean execute(User user, Operation operation) {
        Execution execution = null;
        LocalDateTime finishTime;
        try {
            execution = executionService.saveOrUpdate(new ExecutionBuilder()
                .startTime(LocalDateTime.now(ZoneId.of("UTC")))
                .operation(operation)
                .status(STARTED)
                .user(user)
                .build()
            );

            Script script = operation.getScript();
            Process process = runtime.exec(script.getCommand());
            logger.info("Executing command {}", script.getCommand() + "");
            // TODO timeout if process takes too long
            int exitValue = process.waitFor();
            if (exitValue != 0) {
                throw new FailedProcessException(exitValue);
            }

            finishTime = LocalDateTime.now(ZoneId.of("UTC"));
            executionService.saveOrUpdate(new ExecutionBuilder((execution))
                    .status(FINISHED)
                    .finishTime(finishTime)
                    .build()
            );
            logger.info("Command executed with status FINISHED in {} milliseconds", MILLIS.between(execution.getStartTime(), finishTime));
        } catch (Exception e) {
            if (execution != null) {
                finishTime = LocalDateTime.now(ZoneId.of("UTC"));
                executionService.saveOrUpdate(new ExecutionBuilder((execution))
                        .status(FAILED)
                        .finishTime(finishTime)
                        .build()
                );
                logger.info("Command executed with status FAILED in {} milliseconds", MILLIS.between(execution.getStartTime(), finishTime));
            }
            logger.warn("Command threw exception", e);
            return false;
        }
        return true;
    }
}
