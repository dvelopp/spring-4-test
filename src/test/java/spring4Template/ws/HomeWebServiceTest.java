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
import spring4Template.ws.schema.HomeResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static spring4Template.domain.entities.UserAuthorities.ROLE_USER_VIEW;
import static spring4Template.ws.JsonUtils.toJSON;

@RunWith(MockitoJUnitRunner.class)
public class HomeWebServiceTest {

    @InjectMocks private HomeWebService testee;
    @Mock private AuthProvider authProvider;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        mockMvc = standaloneSetup(testee).build();
    }

    @Test
    public void home_HasUserViewAccess_TrueHasBeenReturned() throws Exception {
        when(authProvider.hasRole(ROLE_USER_VIEW)).thenReturn(true);
        HomeResponse expectedResponse = new HomeResponse();
        expectedResponse.setHasUserViewAccess(true);

        MockHttpServletResponse response = mockMvc.perform(get("/ws/home")
                .contentType(APPLICATION_JSON))
                .andExpect(content().string(toJSON(expectedResponse)))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        HomeResponse actualHomeResponse = objectMapper.readValue(response.getContentAsString(), HomeResponse.class);
        assertThat(actualHomeResponse.isHasUserViewAccess()).isTrue();
    }

    @Test
    public void home_HasNoUserViewAccess_FalseHasBeenReturned() throws Exception {
        when(authProvider.hasRole(ROLE_USER_VIEW)).thenReturn(false);
        HomeResponse expectedResponse = new HomeResponse();
        expectedResponse.setHasUserViewAccess(false);

        MockHttpServletResponse response = mockMvc.perform(get("/ws/home")
                .contentType(APPLICATION_JSON))
                .andExpect(content().string(toJSON(expectedResponse)))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        HomeResponse actualHomeResponse = objectMapper.readValue(response.getContentAsString(), HomeResponse.class);
        assertThat(actualHomeResponse.isHasUserViewAccess()).isFalse();
    }

}