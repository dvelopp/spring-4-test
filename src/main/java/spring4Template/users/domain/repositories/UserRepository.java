package spring4Template.users.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring4Template.users.domain.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    User findByName(String name);
    List<User> findByOrderByFirstNameAsc();

}
