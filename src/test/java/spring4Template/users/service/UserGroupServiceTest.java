package spring4Template.users.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import spring4Template.users.domain.entities.UserGroup;
import spring4Template.users.domain.model.IdNameCommand;
import spring4Template.users.domain.repositories.UserGroupRepository;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static spring4Template.users.domain.entities.UserGroupFixture.createDefaultUserGroup;

@RunWith(MockitoJUnitRunner.class)
public class UserGroupServiceTest {

    @InjectMocks private UserGroupService testee;
    @Mock private UserGroupRepository userGroupRepository;

    @Test
    public void getUserGroups_NoUserGroups_EmptyListHasBeenReturned() {
        when(userGroupRepository.findAll()).thenReturn(emptyList());

        List<IdNameCommand> actualUserGroups = testee.getUserGroups();

        assertThat(actualUserGroups).isEmpty();
    }

    @Test
    public void getUserGroups_TwoUserGroups_UserGroupsHaveBeenMappedToCommandsAndReturned() {
        UserGroup firstUserGroup = createDefaultUserGroup();
        UserGroup secondUserGroup = createDefaultUserGroup();
        IdNameCommand firstIdNameCommand = new IdNameCommand(firstUserGroup, UserGroup::getId, UserGroup::getName);
        IdNameCommand secondIdNameCommand = new IdNameCommand(secondUserGroup, UserGroup::getId, UserGroup::getName);
        when(userGroupRepository.findAll()).thenReturn(Arrays.asList(firstUserGroup, secondUserGroup));

        List<IdNameCommand> actualUserGroups = testee.getUserGroups();

        assertThat(actualUserGroups).containsExactly(firstIdNameCommand, secondIdNameCommand);
    }

}