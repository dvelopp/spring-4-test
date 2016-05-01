package spring4Template.ws.schema;


import spring4Template.domain.model.UserGroupCommand;

import java.util.List;

public class UserGroupsResponse {

    private List<UserGroupCommand> userGroups;

    public void setUserGroups(List<UserGroupCommand> userGroups) {
        this.userGroups = userGroups;
    }

    public List<UserGroupCommand> getUserGroups() {
        return userGroups;
    }
}
