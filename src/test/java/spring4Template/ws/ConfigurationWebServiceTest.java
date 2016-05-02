package spring4Template.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import spring4Template.service.AuthProvider;
import spring4Template.ws.schema.ConfigurationResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static spring4Template.domain.entities.UserAuthorities.ROLE_USER_VIEW;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationWebServiceTest {

    @InjectMocks private ConfigurationWebService testee;
    @Mock private AuthProvider authProvider;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        mockMvc = standaloneSetup(testee).build();
    }

    @Test
    public void attributes_HasConfigurationViewAccess_TrueHasBeenReturned() throws Exception {
        when(authProvider.hasRole(ROLE_USER_VIEW)).thenReturn(true);

        MockHttpServletResponse response = sendValidRequestForAttributes();

        ConfigurationResponse actualConfigurationResponse = objectMapper.readValue(response.getContentAsString(), ConfigurationResponse.class);
        assertThat(actualConfigurationResponse.isHasUserViewAccess()).isTrue();
    }

    @Test
    public void attributes_HasNoConfigurationViewAccess_FalseHasBeenReturned() throws Exception {
        when(authProvider.hasRole(ROLE_USER_VIEW)).thenReturn(false);

        MockHttpServletResponse response = sendValidRequestForAttributes();

        ConfigurationResponse actualConfigurationResponse = objectMapper.readValue(response.getContentAsString(), ConfigurationResponse.class);
        assertThat(actualConfigurationResponse.isHasUserViewAccess()).isFalse();
    }

    private MockHttpServletResponse sendValidRequestForAttributes() throws Exception {
        return mockMvc.perform(get("/ws/configuration/attributes")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }


}