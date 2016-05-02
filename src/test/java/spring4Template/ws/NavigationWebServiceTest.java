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
import spring4Template.ws.schema.NavigationResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static spring4Template.domain.entities.UserAuthorities.ROLE_CONFIGURATION_VIEW;

@RunWith(MockitoJUnitRunner.class)
public class NavigationWebServiceTest {

    @InjectMocks private NavigationWebService testee;
    @Mock private AuthProvider authProvider;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        mockMvc = standaloneSetup(testee).build();
    }

    @Test
    public void attributes_HasConfigurationViewAccess_TrueHasBeenReturned() throws Exception {
        when(authProvider.hasRole(ROLE_CONFIGURATION_VIEW)).thenReturn(true);

        MockHttpServletResponse response = sendValidRequestForAttributes();

        NavigationResponse actualNavigationResponse = objectMapper.readValue(response.getContentAsString(), NavigationResponse.class);
        assertThat(actualNavigationResponse.isHasConfigurationViewAccess()).isTrue();
    }

    @Test
    public void attributes_HasNoConfigurationViewAccess_FalseHasBeenReturned() throws Exception {
        when(authProvider.hasRole(ROLE_CONFIGURATION_VIEW)).thenReturn(false);

        MockHttpServletResponse response = sendValidRequestForAttributes();

        NavigationResponse actualNavigationResponse = objectMapper.readValue(response.getContentAsString(), NavigationResponse.class);
        assertThat(actualNavigationResponse.isHasConfigurationViewAccess()).isFalse();
    }

    private MockHttpServletResponse sendValidRequestForAttributes() throws Exception {
        return mockMvc.perform(get("/ws/navigation/attributes")
                    .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn().getResponse();
    }

}