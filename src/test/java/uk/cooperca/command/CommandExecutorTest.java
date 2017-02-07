package uk.cooperca.command;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import uk.cooperca.auth.util.SecurityUtils;
import uk.cooperca.entity.Execution;
import uk.cooperca.entity.Script;
import uk.cooperca.entity.builder.ExecutionBuilder;
import uk.cooperca.entity.builder.OperationBuilder;
import uk.cooperca.entity.builder.ScriptBuilder;
import uk.cooperca.entity.builder.UserBuilder;
import uk.cooperca.service.ExecutionService;
import uk.cooperca.service.OperationService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class CommandExecutorTest {

    private CommandExecutor commandExecutor;
    private OperationService operationService;
    private ExecutionService executionService;
    private SecurityUtils securityUtils;
    private Runtime runtime;
    private Process process;

    // TODO: improve tests

    @Before
    public void setup() throws IOException {
        commandExecutor = new CommandExecutor();
        operationService = mock(OperationService.class);
        Script script = new ScriptBuilder().command("/tmp/management_copy.sh -h").build();
        when(operationService.findOne(1L)).thenReturn(
                new OperationBuilder().script(script).build()
        );
        executionService = mock(ExecutionService.class);
        when(executionService.saveOrUpdate(any(Execution.class))).thenReturn(
                new ExecutionBuilder().startTime(LocalDateTime.now()).finishTime(LocalDateTime.now()).build()
        );
        securityUtils = mock(SecurityUtils.class);
        when(securityUtils.getCurrentAuthenticatedUser()).thenReturn(Optional.of(new UserBuilder().build()));
        runtime = mock(Runtime.class);
        process = mock(Process.class);
        when(runtime.exec(anyString())).thenReturn(process);

        ReflectionTestUtils.setField(commandExecutor, "operationService", operationService);
        ReflectionTestUtils.setField(commandExecutor, "executionService", executionService);
        ReflectionTestUtils.setField(commandExecutor, "securityUtils", securityUtils);
        ReflectionTestUtils.setField(commandExecutor, "runtime", runtime);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailureInvalidOperation() {
        when(operationService.findOne(2L)).thenReturn(null);
        commandExecutor.executeOperation(2L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailureNotAuthenticatedUser() {
        when(securityUtils.getCurrentAuthenticatedUser()).thenReturn(Optional.empty());
        commandExecutor.executeOperation(1L);
    }

    @Test
    public void testFailureInterruptedException() throws InterruptedException {
        when(process.waitFor()).thenThrow(new InterruptedException());

        commandExecutor.executeOperation(1L);

        verify(executionService, times(2)).saveOrUpdate(any(Execution.class));
        verifyNoMoreInteractions(executionService);
    }

    @Test
    public void testFailureIOException() throws InterruptedException, IOException {
        when(runtime.exec(anyString())).thenThrow(new IOException());

        commandExecutor.executeOperation(1L);

        verify(executionService, times(2)).saveOrUpdate(any(Execution.class));
        verifyNoMoreInteractions(executionService);
    }

    @Test
    public void testFailureFailedProcess() throws InterruptedException {
        when(process.waitFor()).thenReturn(1);

        commandExecutor.executeOperation(1L);

        verify(executionService, times(2)).saveOrUpdate(any(Execution.class));
        verifyNoMoreInteractions(executionService);
    }

    @Test
    public void testSuccess() {
        commandExecutor.executeOperation(1L);

        verify(executionService, times(2)).saveOrUpdate(any(Execution.class));
        verifyNoMoreInteractions(executionService);
    }
}
