package uk.cooperca.resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.cooperca.ManagementApplication;
import uk.cooperca.entity.Execution;
import uk.cooperca.service.ExecutionService;
import uk.cooperca.service.OperationService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManagementApplication.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class OperationResourceIntegrationTest {

    private static final String TEST_USER = "charliec";

    private MockMvc mockMvc;

    @Autowired
    private OperationService operationService;

    @Autowired
    private ExecutionService executionService;

    @Autowired
    private Runtime runtime;

    @Before
    public void setup() {
        OperationResource operationResource = new OperationResource();
        ReflectionTestUtils.setField(operationResource, "operationService", operationService);
        mockMvc = MockMvcBuilders.standaloneSetup(operationResource).build();
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get("/api/operations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testGetOne() throws Exception {
        mockMvc.perform(get("/api/operations/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("test_op1"));
    }

    @Test
    @WithMockUser(TEST_USER)
    public void testPerformOperationSuccess() throws Exception {
        int size = executionService.findAll().size();

        Process process = mock(Process.class);
        when(process.waitFor()).thenReturn(0);
        when(runtime.exec("/tmp/management_copy.sh -h")).thenReturn(process);

        mockMvc.perform(put("/api/operations/1"))
                .andExpect(status().isOk());

        List<Execution> executions = executionService.findAll();
        assertThat(executions.get(executions.size() - 1).getStatus()).isEqualTo(Execution.Status.FINISHED);
        assertThat((size + 1)).isEqualTo(executions.size());
    }

    @Test
    @WithMockUser(TEST_USER)
    public void testPerformOperationFail() throws Exception {
        int size = executionService.findAll().size();

        Process process = mock(Process.class);
        when(process.waitFor()).thenReturn(1);
        when(runtime.exec("/tmp/management_copy.sh -h")).thenReturn(process);

        mockMvc.perform(put("/api/operations/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("execution of operation failed"));

        List<Execution> executions = executionService.findAll();
        assertThat(executions.get(executions.size() - 1).getStatus()).isEqualTo(Execution.Status.FAILED);
        assertThat((size + 1)).isEqualTo(executions.size());
    }

    @Test
    public void testPerformOperationNoAuthenticatedUser() throws Exception {
        int size = executionService.findAll().size();

        mockMvc.perform(put("/api/operations/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("no authenticated user found"));

        assertThat(size).isEqualTo(executionService.findAll().size());
    }
}
