package spring4Template.service;

import org.springframework.stereotype.Component;
import spring4Template.domain.entities.UserGroup;
import spring4Template.domain.model.UserGroupCommand;

@Component
public class UserGroupCommandMapper {

    public UserGroupCommand mapToCommand(UserGroup userGroup){
        UserGroupCommand command = new UserGroupCommand();
        command.setName(userGroup.getName());
        command.setId(userGroup.getId());
        return command;
    }

}
