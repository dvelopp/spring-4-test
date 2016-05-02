package spring4Template.domain.repositories;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spring4Template.domain.HibernateIntegrationTest;
import spring4Template.domain.entities.User;

import static org.assertj.core.api.Assertions.assertThat;
import static spring4Template.domain.entities.UserFixture.createUserWithFirstName;

public class UserRepositoryTest extends HibernateIntegrationTest<User> {

    @Autowired private UserRepository userRepository;

    @Test
    public void findByOrderByFirstNameAsc_TwoUsers_TwoUsersHaveBeenReturned(){
        User firstUser = createUserWithFirstName("A");
        User secondUser = createUserWithFirstName("B");
        saveAll(firstUser, secondUser).flush();

        Iterable<User> actualUsers = userRepository.findAll();

        assertThat(actualUsers).hasSize(2);
    }

    @Test
    public void findByOrderByFirstNameAsc_TwoUsersSavedInWrongOrder_UsersHaveBeenReturnedInTheCorrectOrder(){
        User firstUser = createUserWithFirstName("B");
        User secondUser = createUserWithFirstName("A");
        saveAll(firstUser, secondUser).flush();

        Iterable<User> actualUsers = userRepository.findByOrderByFirstNameAsc();

        assertThat(actualUsers).containsExactly(secondUser, firstUser);
    }

    @Test
    public void findByOrderByFirstNameAsc_TwoUsersSavedInCorrectOrder_UsersHaveBeenReturnedInTheCorrectOrder(){
        User firstUser = createUserWithFirstName("A");
        User secondUser = createUserWithFirstName("B");
        saveAll(firstUser, secondUser).flush();

        Iterable<User> actualUsers = userRepository.findByOrderByFirstNameAsc();

        assertThat(actualUsers).containsExactly(firstUser, secondUser);
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
