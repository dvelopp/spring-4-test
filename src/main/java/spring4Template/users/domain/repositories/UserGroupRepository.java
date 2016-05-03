package spring4Template.users.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring4Template.users.domain.entities.UserGroup;

@Repository
public interface UserGroupRepository extends CrudRepository<UserGroup, String> {

}
