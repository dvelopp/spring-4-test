package spring4Template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring4Template.domain.entities.UserGroup;
import spring4Template.domain.model.IdNameCommand;
import spring4Template.domain.repositories.UserGroupRepository;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

@Service
public class UserGroupService {

    @Autowired private UserGroupRepository userGroupRepository;

    @Transactional(readOnly = true)
    public List<IdNameCommand> getUserGroups() {
        return newArrayList(userGroupRepository.findAll()).stream()
                .map(userGroup -> new IdNameCommand(userGroup, UserGroup::getId, UserGroup::getName))
                .collect(toList());
    }

}
