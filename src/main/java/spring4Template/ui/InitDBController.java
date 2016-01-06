package spring4Template.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import spring4Template.domain.entities.User;
import spring4Template.domain.entities.UserAuthority;
import spring4Template.domain.entities.UserBuilder;
import spring4Template.domain.entities.UserGroup;
import spring4Template.domain.repositories.UserAuthorityRepository;
import spring4Template.domain.repositories.UserGroupRepository;
import spring4Template.domain.repositories.UserRepository;

import static spring4Template.domain.entities.UserAuthorities.*;

@Controller
public class InitDBController {

    @Autowired private UserAuthorityRepository userAuthorityRepository;
    @Autowired private UserGroupRepository userGroupRepository;
    @Autowired private UserRepository userRepository;

    @Transactional
    @RequestMapping(value = "/initDB")
    public String greeting() {
        userRepository.deleteAll();
        userGroupRepository.deleteAll();
        userAuthorityRepository.deleteAll();
        UserGroup adminGroup = new UserGroup("Administrator");
        UserGroup defaultUserGroup = new UserGroup("Default user");
        UserAuthority roleDefault = new UserAuthority(ROLE_DEFAULT);
        UserAuthority roleUserEdit = new UserAuthority(ROLE_USER_EDIT);
        UserAuthority roleUserView = new UserAuthority(ROLE_USER_VIEW);
        UserAuthority roleUserDelete = new UserAuthority(ROLE_USER_DELETE);
        adminGroup.getAuthorities().add(roleDefault);
        adminGroup.getAuthorities().add(roleUserEdit);
        adminGroup.getAuthorities().add(roleUserView);
        adminGroup.getAuthorities().add(roleUserDelete);
        defaultUserGroup.getAuthorities().add(roleDefault);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User admin = new UserBuilder().setName("admin").setPassword(encoder.encode("password")).setGroup(adminGroup).createUser();
        admin.setSystemUser(true);
        admin.setFirstName("Bob");
        admin.setLastName("Smith");
        User userDefault = new UserBuilder().setName("user").setPassword(encoder.encode("password")).setGroup(defaultUserGroup).createUser();
        userAuthorityRepository.save(roleDefault);
        userAuthorityRepository.save(roleUserEdit);
        userAuthorityRepository.save(roleUserView);
        userAuthorityRepository.save(roleUserDelete);
        userGroupRepository.save(adminGroup);
        userGroupRepository.save(defaultUserGroup);
        userRepository.save(admin);
        userRepository.save(userDefault);
        return "hello";
    }

}
