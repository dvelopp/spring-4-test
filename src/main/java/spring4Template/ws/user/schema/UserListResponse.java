package spring4Template.ws.user.schema;


import spring4Template.domain.model.UserCommand;
import spring4Template.domain.model.IdNameCommand;

import java.util.List;

public class UserListResponse {

    private List<UserCommand> users;
    private List<IdNameCommand> userGroups;
    boolean hasUserDeleteAccess;
    boolean hasUserEditAccess;

    public List<UserCommand> getUsers() {
        return users;
    }

    public void setUsers(List<UserCommand> users) {
        this.users = users;
    }

    public boolean isHasUserDeleteAccess() {
        return hasUserDeleteAccess;
    }

    public void setHasUserDeleteAccess(boolean hasUserDeleteAccess) {
        this.hasUserDeleteAccess = hasUserDeleteAccess;
    }

    public boolean isHasUserEditAccess() {
        return hasUserEditAccess;
    }

    public void setHasUserEditAccess(boolean hasUserEditAccess) {
        this.hasUserEditAccess = hasUserEditAccess;
    }

    public void setUserGroups(List<IdNameCommand> userGroups) {
        this.userGroups = userGroups;
    }

    public List<IdNameCommand> getUserGroups() {
        return userGroups;
    }
}
