package uk.cooperca.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.cooperca.ManagementApplication;
import uk.cooperca.auth.token.TokenProvider;
import uk.cooperca.dto.CredentialsDTO;
import uk.cooperca.repository.UserRepository;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.cooperca.resource.LoginResource.TOKEN;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManagementApplication.class)
@ActiveProfiles("test")
public class LoginResourceIntegrationTest {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        LoginResource loginResource = new LoginResource();
        ReflectionTestUtils.setField(loginResource, "authenticationManager", authenticationManager);
        ReflectionTestUtils.setField(loginResource, "tokenProvider", tokenProvider);
        mockMvc = MockMvcBuilders.standaloneSetup(loginResource).build();
    }

    @Test
    @Transactional
    public void testSuccessfulLogin() throws Exception {
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createBody("charliec", "test")))
                .andExpect(status().isOk())
                .andExpect(header().string(TOKEN, notNullValue()));
    }

    @Test
    @Transactional
    public void testFailedLogin() throws Exception {
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createBody("greenrabbit948", "test")))
                .andExpect(status().isUnauthorized())
                .andExpect(header().string(TOKEN, nullValue()));

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createBody("charliec", "celeste2")))
                .andExpect(status().isUnauthorized())
                .andExpect(header().string(TOKEN, nullValue()));
    }

    private byte[] createBody(String username, String password) throws JsonProcessingException {
        CredentialsDTO dto = new CredentialsDTO();
        dto.setUsername(username);
        dto.setPassword(password);
        return new ObjectMapper().writeValueAsBytes(dto);
    }
}
