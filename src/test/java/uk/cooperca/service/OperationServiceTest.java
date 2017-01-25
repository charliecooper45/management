package uk.cooperca.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import uk.cooperca.repository.OperationRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OperationServiceTest {

    private OperationService service;
    private OperationRepository operationRepository;

    @Before
    public void setup() {
        service = new OperationService();
        operationRepository = mock(OperationRepository.class);
        ReflectionTestUtils.setField(service, "operationRepository", operationRepository);
    }

    @Test
    public void testFindOne() {
        service.findOne(1L);
        verify(operationRepository).findOne(1L);
    }
}
