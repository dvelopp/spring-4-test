package spring4Template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring4Template.domain.model.UserGroupCommand;
import spring4Template.domain.repositories.UserGroupRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserGroupService {

    @Autowired private UserGroupRepository userGroupRepository;
    @Autowired private UserGroupCommandMapper userGroupCommandMapper;

    @Transactional(readOnly = true)
    public List<UserGroupCommand> getUserGroups(){
        List<UserGroupCommand> userGroups = new ArrayList<>();
        userGroupRepository.findAll().forEach(userGroup -> userGroups.add(userGroupCommandMapper.mapToCommand(userGroup)));
        return userGroups;
    }

}
