package spring4Template.ws.schema;


import spring4Template.domain.model.UserCommand;
import spring4Template.domain.model.UserGroupCommand;

import java.util.List;

public class UserListResponse {

    private List<UserCommand> users;
    private List<UserGroupCommand> userGroups;
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

    public void setUserGroups(List<UserGroupCommand> userGroups) {
        this.userGroups = userGroups;
    }

    public List<UserGroupCommand> getUserGroups() {
        return userGroups;
    }
}
