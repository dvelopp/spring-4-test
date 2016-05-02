package spring4Template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring4Template.domain.entities.User;
import spring4Template.domain.model.UserCommand;
import spring4Template.domain.repositories.UserGroupRepository;
import spring4Template.domain.repositories.UserRepository;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static spring4Template.domain.entities.User.OLD_PASSWORD_MASK;

@Component
public class UserCommandMapper {

    @Autowired private UserGroupRepository userGroupRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public UserCommand mapToCommand(User user) {
        UserCommand userCommand = new UserCommand();
        userCommand.setId(user.getId());
        userCommand.setFirstName(user.getFirstName());
        userCommand.setLastName(user.getLastName());
        userCommand.setUserName(user.getName());
        userCommand.setUserGroupId(user.getGroup().getId());
        userCommand.setUserGroupName(user.getGroup().getName());
        userCommand.setPassword(user.getPassword());
        userCommand.setSystemUser(user.isSystemUser());
        return userCommand;
    }

    public User mapFromCommand(UserCommand userCommand) {
        User user;
        if (isNotBlank(userCommand.getId())) {
            user = userRepository.findOne(userCommand.getId());
            user.setGroup(userGroupRepository.findOne(userCommand.getUserGroupId()));
        } else {
            user = new User(userCommand.getUserName(), userCommand.getPassword(), userGroupRepository.findOne(userCommand.getUserGroupId()));
        }
        user.setFirstName(userCommand.getFirstName());
        user.setLastName(userCommand.getLastName());
        user.setName(userCommand.getUserName());
        if (!OLD_PASSWORD_MASK.equals(userCommand.getPassword())) {
            user.setPassword(passwordEncoder.encode(userCommand.getPassword()));
        }
        return user;
    }

}
