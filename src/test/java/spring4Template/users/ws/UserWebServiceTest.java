package spring4Template.users.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import spring4Template.users.domain.entities.UserGroup;
import spring4Template.users.domain.model.IdNameCommand;
import spring4Template.users.domain.model.UserCommand;
import spring4Template.system.service.AuthProvider;
import spring4Template.users.service.UserGroupService;
import spring4Template.users.service.UserService;
import spring4Template.users.ws.schema.UserListResponse;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static spring4Template.users.domain.entities.UserAuthorities.ROLE_USER_DELETE;
import static spring4Template.users.domain.entities.UserAuthorities.ROLE_USER_EDIT;
import static spring4Template.users.domain.entities.UserGroupFixture.createDefaultUserGroup;
import static spring4Template.users.domain.model.UserCommandFixture.createDefaultUserCommand;
import static spring4Template.users.ws.UserWebService.COMMAND_NAME;

@RunWith(MockitoJUnitRunner.class)
public class UserWebServiceTest {

    @InjectMocks private UserWebService testee;
    @Mock private UserGroupService userGroupService;
    @Mock private UserValidator userValidator;
    @Mock private AuthProvider authProvider;
    @Mock private UserService userService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        when(userValidator.supports(any())).thenReturn(true);
        mockMvc = standaloneSetup(testee).build();
    }

    @Test
    public void attributes_HasDeleteUserAccess_DeleteAccessIsTrueInResponse() throws Exception {
        when(authProvider.hasRole(ROLE_USER_DELETE)).thenReturn(true);

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserListResponse actualUserListResponse = getUserListResponse(response);
        assertThat(actualUserListResponse.isHasUserDeleteAccess()).isTrue();
    }

    @Test
    public void attributes_HasNoDeleteUserAccess_DeleteAccessIsFalseInResponse() throws Exception {
        when(authProvider.hasRole(ROLE_USER_DELETE)).thenReturn(false);

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserListResponse actualUserListResponse = getUserListResponse(response);
        assertThat(actualUserListResponse.isHasUserDeleteAccess()).isFalse();
    }

    @Test
    public void attributes_HasEditUserAccess_EditAccessIsTrueInResponse() throws Exception {
        when(authProvider.hasRole(ROLE_USER_EDIT)).thenReturn(true);

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserListResponse actualUserListResponse = getUserListResponse(response);
        assertThat(actualUserListResponse.isHasUserEditAccess()).isTrue();
    }

    @Test
    public void attributes_HasNoEditUserAccess_EditAccessIsFalseInResponse() throws Exception {
        when(authProvider.hasRole(ROLE_USER_EDIT)).thenReturn(false);

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserListResponse actualUserListResponse = getUserListResponse(response);
        assertThat(actualUserListResponse.isHasUserEditAccess()).isFalse();
    }

    @Test
    public void attributes_HasUserGroups_UserGroupsHaveBeenAdded() throws Exception {
        IdNameCommand command = new IdNameCommand(createDefaultUserGroup(), UserGroup::getId, UserGroup::getName);
        when(userGroupService.getUserGroups()).thenReturn(asList(command));

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserListResponse actualUserListResponse = getUserListResponse(response);
        assertThat(actualUserListResponse.getUserGroups()).containsOnly(command);
    }

    @Test
    public void attributes_HasNoUserGroups_UserGroupsHaveNotBeenAdded() throws Exception {
        when(userGroupService.getUserGroups()).thenReturn(emptyList());

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserListResponse actualUserListResponse = getUserListResponse(response);
        assertThat(actualUserListResponse.getUserGroups()).isEmpty();
    }

    @Test
    public void attributes_HasUsers_UsersHaveBeenAdded() throws Exception {
        UserCommand command = createDefaultUserCommand();
        when(userService.getUsers()).thenReturn(asList(command));

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserListResponse actualUserListResponse = getUserListResponse(response);
        assertThat(actualUserListResponse.getUsers()).containsOnly(command);
    }

    @Test
    public void attributes_HasNoUsers_UsersHaveNotBeenAdded() throws Exception {
        when(userService.getUsers()).thenReturn(emptyList());

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserListResponse actualUserListResponse = getUserListResponse(response);
        assertThat(actualUserListResponse.getUsers()).isEmpty();
    }

    @Test
    public void delete_UserHasBeenDeleted() throws Exception {
        UserCommand user = createDefaultUserCommand();

        mockMvc.perform(delete("/ws/users/{userId}", user.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        verify(userService).delete(user.getId());
    }

    @Test
    public void save_UserHasBeenSaved() throws Exception {
        UserCommand user = createDefaultUserCommand();

        mockMvc.perform(post("/ws/users").flashAttr(COMMAND_NAME, user)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        verify(userService).save(user);
    }

    private UserListResponse getUserListResponse(MockHttpServletResponse response) throws java.io.IOException {
        return objectMapper.readValue(response.getContentAsString(), UserListResponse.class);
    }

    private MockHttpServletResponse sendValidRequestForAttributes() throws Exception {
        return mockMvc.perform(get("/ws/users")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

}