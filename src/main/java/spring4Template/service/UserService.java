package spring4Template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring4Template.domain.entities.User;
import spring4Template.domain.repositories.UserRepository;
import spring4Template.domain.model.UserCommand;

import java.util.ArrayList;
import java.util.List;

import static spring4Template.domain.entities.UserAuthorities.ROLE_USER_DELETE;
import static spring4Template.domain.entities.UserAuthorities.ROLE_USER_EDIT;
import static spring4Template.domain.entities.UserAuthorities.ROLE_USER_VIEW;

@Service
public class UserService {

    @Autowired private UserCommandMapper userCommandMapper;
    @Autowired private UserRepository userRepository;

    @Secured(ROLE_USER_VIEW)
    @Transactional(readOnly = true)
    public List<UserCommand> getUsers() {
        List<UserCommand> userCommands = new ArrayList<>();
        userRepository.findAll().forEach(user -> userCommands.add(userCommandMapper.mapToCommand(user)));
        return userCommands;
    }

    @Secured(ROLE_USER_EDIT)
    @Transactional
    public void save(UserCommand userCommand) {
        User user = userCommandMapper.mapFromCommand(userCommand);
        userRepository.save(user);
    }

    @Secured(ROLE_USER_DELETE)
    @Transactional
    public void delete(String userId) {
        User user = userRepository.findOne(userId);
        if (user.isSystemUser()) {
            throw new IllegalArgumentException("System user can't be deleted");
        }
        userRepository.delete(userId);
    }

}
