package uk.cooperca.resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.cooperca.ManagementTestApplication;
import uk.cooperca.entity.Operation;
import uk.cooperca.entity.OperationBuilder;
import uk.cooperca.repository.OperationRepository;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManagementTestApplication.class)
public class OperationResourceIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private OperationRepository operationRepository;

    @Before
    public void setup() {
        OperationResource operationResource = new OperationResource();
        ReflectionTestUtils.setField(operationResource, "operationRepository", operationRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(operationResource).build();

        // create an operation
        Operation operation = new OperationBuilder().setName("Test One").createOperation();
        operationRepository.saveAndFlush(operation);
    }

    @Test
    @Transactional
    public void testGet() throws Exception {
        mockMvc.perform(get("/api/operations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @Transactional
    public void testGetOne() throws Exception {
        mockMvc.perform(get("/api/operations/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("test 1"));
    }

}
