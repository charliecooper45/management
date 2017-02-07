package uk.cooperca.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import uk.cooperca.entity.Execution;
import uk.cooperca.entity.builder.ExecutionBuilder;
import uk.cooperca.repository.ExecutionRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ExecutionServiceTest {

    private ExecutionService service;
    private ExecutionRepository executionRepository;

    @Before
    public void setup() {
        service = new ExecutionService();
        executionRepository = mock(ExecutionRepository.class);
        ReflectionTestUtils.setField(service, "executionRepository", executionRepository);
    }

    @Test
    public void testSaveOrUpdate() {
        Execution execution = new ExecutionBuilder().build();
        service.saveOrUpdate(execution);
        verify(executionRepository).saveAndFlush(execution);
    }
}
