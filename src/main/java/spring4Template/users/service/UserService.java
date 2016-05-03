package spring4Template.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring4Template.users.domain.entities.User;
import spring4Template.users.domain.model.UserCommand;
import spring4Template.users.domain.repositories.UserRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static spring4Template.users.domain.entities.UserAuthorities.*;

@Service
public class UserService {

    @Autowired private UserCommandMapper userCommandMapper;
    @Autowired private UserRepository userRepository;

    @Secured(ROLE_USER_VIEW)
    @Transactional(readOnly = true)
    public List<UserCommand> getUsers() {
        return userRepository.findByOrderByFirstNameAsc().stream()
                .map(userCommandMapper::mapToCommand)
                .collect(toList());
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
