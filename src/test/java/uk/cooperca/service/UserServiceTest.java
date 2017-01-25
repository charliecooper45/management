package uk.cooperca.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import uk.cooperca.repository.UserRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    private UserService service;
    private UserRepository userRepository;

    @Before
    public void setup() {
        service = new UserService();
        userRepository = mock(UserRepository.class);
        ReflectionTestUtils.setField(service, "userRepository", userRepository);
    }

    @Test
    public void testFindOneByUsername() {
        service.findOneByUsername("bob");
        verify(userRepository).findOneByUsername("bob");
    }
}
