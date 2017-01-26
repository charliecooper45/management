package uk.cooperca.resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.cooperca.ManagementApplication;
import uk.cooperca.entity.Application;
import uk.cooperca.entity.Script;
import uk.cooperca.entity.builder.ApplicationBuilder;
import uk.cooperca.entity.builder.OperationBuilder;
import uk.cooperca.entity.builder.ScriptBuilder;
import uk.cooperca.repository.ApplicationRepository;
import uk.cooperca.repository.OperationRepository;
import uk.cooperca.repository.ScriptRepository;
import uk.cooperca.service.OperationService;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManagementApplication.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class OperationResourceIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ScriptRepository scriptRepository;

    @Autowired
    private OperationService operationService;

    @Before
    public void setup() {
        // create operations
        Application application = applicationRepository.saveAndFlush(new ApplicationBuilder().name("Test Application").build());
        Script script = scriptRepository.saveAndFlush(new ScriptBuilder().path("/tmp/script.sh").build());
        operationRepository.saveAndFlush(new OperationBuilder().name("Test One").application(application).script(script).build());
        operationRepository.saveAndFlush(new OperationBuilder().name("Test Two").application(application).script(script).build());
        operationRepository.saveAndFlush(new OperationBuilder().name("Test Three").application(application).script(script).build());

        OperationResource operationResource = new OperationResource();
        ReflectionTestUtils.setField(operationResource, "operationService", operationService);
        mockMvc = MockMvcBuilders.standaloneSetup(operationResource).build();
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
                .andExpect(jsonPath("$.name").value("Test One"));
    }

}
