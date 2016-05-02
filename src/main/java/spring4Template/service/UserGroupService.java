package spring4Template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring4Template.domain.entities.UserGroup;
import spring4Template.domain.model.IdNameCommand;
import spring4Template.domain.repositories.UserGroupRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserGroupService {

    @Autowired private UserGroupRepository userGroupRepository;

    @Transactional(readOnly = true)
    public List<IdNameCommand> getUserGroups(){
        List<IdNameCommand> userGroups = new ArrayList<>();
        userGroupRepository.findAll().forEach(userGroup -> userGroups.add(new IdNameCommand(userGroup, UserGroup::getId, UserGroup::getName)));
        return userGroups;
    }

}
