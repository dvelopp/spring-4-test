package spring4Template.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import spring4Template.domain.entities.User;
import spring4Template.domain.entities.UserFixture;
import spring4Template.domain.model.UserCommand;
import spring4Template.domain.repositories.UserRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static spring4Template.domain.entities.UserFixture.createDefaultUser;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks private UserService testee;

    @Mock private UserCommandMapper userCommandMapper;
    @Mock private UserRepository userRepository;

    @Test
    public void save_ValidUserCommand_UserHasBeenSaved(){
        UserCommand userCommand = new UserCommand();
        User user = createDefaultUser();
        when(userCommandMapper.mapFromCommand(userCommand)).thenReturn(user);

        testee.save(userCommand);

        verify(userRepository).save(user);
    }

    @Test
    public void delete_GeneralUser_UserHasBeenDeleted() {
        User user = createDefaultUser();
        when(userRepository.findOne(user.getId())).thenReturn(user);

        testee.delete(user.getId());

        verify(userRepository).delete(user.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void delete_SystemUser_ExceptionHasBeenThrown(){
        User user = UserFixture.builder().setSystemUser(true).build();
        when(userRepository.findOne(user.getId())).thenReturn(user);

        testee.delete(user.getId());
    }

}