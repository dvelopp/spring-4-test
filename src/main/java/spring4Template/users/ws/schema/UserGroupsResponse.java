package spring4Template.users.ws.schema;


import spring4Template.system.domain.model.IdNameCommand;

import java.util.List;

public class UserGroupsResponse {

    private List<IdNameCommand> userGroups;

    public void setUserGroups(List<IdNameCommand> userGroups) {
        this.userGroups = userGroups;
    }

    public List<IdNameCommand> getUserGroups() {
        return userGroups;
    }
}
