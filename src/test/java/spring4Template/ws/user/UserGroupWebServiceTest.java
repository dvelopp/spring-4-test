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
import spring4Template.service.UserGroupService;
import spring4Template.ws.user.schema.UserGroupsResponse;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static spring4Template.domain.entities.UserGroupFixture.createDefaultUserGroup;

@RunWith(MockitoJUnitRunner.class)
public class UserGroupWebServiceTest {

    @InjectMocks private UserGroupWebService testee;
    @Mock private UserGroupService userGroupService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        mockMvc = standaloneSetup(testee).build();
    }

    @Test
    public void groups_HasGroups_GroupsHaveBeenAdded() throws Exception {
        IdNameCommand command = new IdNameCommand(createDefaultUserGroup(), UserGroup::getId, UserGroup::getName);
        when(userGroupService.getUserGroups()).thenReturn(asList(command));

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserGroupsResponse actualUserGroupsResponse = objectMapper.readValue(response.getContentAsString(), UserGroupsResponse.class);
        assertThat(actualUserGroupsResponse.getUserGroups()).containsOnly(command);
    }

    @Test
    public void groups_HasNoGroups_NoGroupsHaveBeenAdded() throws Exception {
        when(userGroupService.getUserGroups()).thenReturn(Collections.<IdNameCommand>emptyList());

        MockHttpServletResponse response = sendValidRequestForAttributes();

        UserGroupsResponse actualUserGroupsResponse = objectMapper.readValue(response.getContentAsString(), UserGroupsResponse.class);
        assertThat(actualUserGroupsResponse.getUserGroups()).isEmpty();
    }

    private MockHttpServletResponse sendValidRequestForAttributes() throws Exception {
        return mockMvc.perform(get("/ws/users/groups")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }

}