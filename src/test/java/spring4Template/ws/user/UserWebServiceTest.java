package spring4Template.ws.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import spring4Template.domain.entities.UserGroup;
import spring4Template.domain.model.IdNameCommand;
import spring4Template.domain.model.UserCommand;
import spring4Template.service.AuthProvider;
import spring4Template.service.UserGroupService;
import spring4Template.service.UserService;
import spring4Template.utils.ValidationResultFactory;
import spring4Template.ws.user.schema.UserListResponse;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static spring4Template.domain.entities.UserAuthorities.ROLE_USER_DELETE;
import static spring4Template.domain.entities.UserAuthorities.ROLE_USER_EDIT;
import static spring4Template.domain.entities.UserGroupFixture.createDefaultUserGroup;
import static spring4Template.domain.model.UserCommandFixture.createDefaultUserCommand;

@RunWith(MockitoJUnitRunner.class)
public class UserWebServiceTest {

    @InjectMocks private UserWebService testee;
    @Mock private ValidationResultFactory validationResultFactory;
    @Mock private UserGroupService userGroupService;
    @Mock private UserValidator userValidator;
    @Mock private AuthProvider authProvider;
    @Mock private UserService userService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        mockMvc = standaloneSetup(testee).build();
    }

    @Test
    public void attributes_HasDeleteUserAccess_TrueHasBeenReturned() throws Exception {
        when(authProvider.hasRole(ROLE_USER_DELETE)).thenReturn(true);

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserListResponse actualUserListResponse = objectMapper.readValue(response.getContentAsString(), UserListResponse.class);
        assertThat(actualUserListResponse.isHasUserDeleteAccess()).isTrue();
    }

    @Test
    public void attributes_HasNoDeleteUserAccess_FalseHasBeenReturned() throws Exception {
        when(authProvider.hasRole(ROLE_USER_DELETE)).thenReturn(false);

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserListResponse actualUserListResponse = objectMapper.readValue(response.getContentAsString(), UserListResponse.class);
        assertThat(actualUserListResponse.isHasUserDeleteAccess()).isFalse();
    }

    @Test
    public void attributes_HasEditUserAccess_TrueHasBeenReturned() throws Exception {
        when(authProvider.hasRole(ROLE_USER_EDIT)).thenReturn(true);

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserListResponse actualUserListResponse = objectMapper.readValue(response.getContentAsString(), UserListResponse.class);
        assertThat(actualUserListResponse.isHasUserEditAccess()).isTrue();
    }

    @Test
    public void attributes_HasNoEditUserAccess_FalseHasBeenReturned() throws Exception {
        when(authProvider.hasRole(ROLE_USER_EDIT)).thenReturn(false);

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserListResponse actualUserListResponse = objectMapper.readValue(response.getContentAsString(), UserListResponse.class);
        assertThat(actualUserListResponse.isHasUserEditAccess()).isFalse();
    }


    @Test
    public void attributes_HasUserGroups_UserGroupsHaveBeenAdded() throws Exception {
        IdNameCommand command = new IdNameCommand(createDefaultUserGroup(), UserGroup::getId, UserGroup::getName);
        when(userGroupService.getUserGroups()).thenReturn(asList(command));

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserListResponse actualUserListResponse = objectMapper.readValue(response.getContentAsString(), UserListResponse.class);
        assertThat(actualUserListResponse.getUserGroups()).containsOnly(command);
    }

    @Test
    public void attributes_HasNoUserGroups_UserGroupsHaveNotBeenAdded() throws Exception {
        when(userGroupService.getUserGroups()).thenReturn(emptyList());

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserListResponse actualUserListResponse = objectMapper.readValue(response.getContentAsString(), UserListResponse.class);
        assertThat(actualUserListResponse.getUserGroups()).isEmpty();
    }

    @Test
    public void attributes_HasUsers_UsersHaveBeenAdded() throws Exception {
        UserCommand command = createDefaultUserCommand();
        when(userService.getUsers()).thenReturn(asList(command));

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserListResponse actualUserListResponse = objectMapper.readValue(response.getContentAsString(), UserListResponse.class);
        assertThat(actualUserListResponse.getUsers()).containsOnly(command);
    }

    @Test
    public void attributes_HasNoUsers_UsersHaveNotBeenAdded() throws Exception {
        when(userService.getUsers()).thenReturn(emptyList());

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserListResponse actualUserListResponse = objectMapper.readValue(response.getContentAsString(), UserListResponse.class);
        assertThat(actualUserListResponse.getUsers()).isEmpty();
    }

    private MockHttpServletResponse sendValidRequestForAttributes() throws Exception {
        return mockMvc.perform(get("/ws/users")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

}