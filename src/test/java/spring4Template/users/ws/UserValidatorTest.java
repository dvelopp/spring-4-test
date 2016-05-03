package spring4Template.users.ws;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;
import spring4Template.users.domain.model.UserCommand;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static spring4Template.users.domain.entities.User.OLD_PASSWORD_MASK;
import static spring4Template.users.ws.UserValidationConstants.*;

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {

    @InjectMocks private UserValidator testee;
    @Mock private Errors errors;

    @Test
    public void validate_NameIsNotEmpty_NoErrorsHaveBeenAdded(){
        UserCommand userCommand = new UserCommand();
        userCommand.setUserName("Test User Name");

        testee.validate(userCommand, errors);

        verify(errors, times(0)).rejectValue(USER_NAME_FIELD, USER_NAME_IS_EMPTY_CODE, USER_NAME_IS_EMPTY_MESSAGE);
    }

    @Test
    public void validate_NameIsEmpty_ErrorsHaveBeenAdded(){
        UserCommand userCommand = new UserCommand();

        testee.validate(userCommand, errors);

        verify(errors, times(1)).rejectValue(USER_NAME_FIELD, USER_NAME_IS_EMPTY_CODE, USER_NAME_IS_EMPTY_MESSAGE);
    }

    @Test
    public void validate_FirstNameIsNotEmpty_NoErrorsHaveBeenAdded(){
        UserCommand userCommand = new UserCommand();
        userCommand.setFirstName("Test First Name");

        testee.validate(userCommand, errors);

        verify(errors, times(0)).rejectValue(USER_FIRST_NAME_FIELD, FIRST_NAME_IS_EMPTY_CODE, FIRST_NAME_IS_EMPTY_MESSAGE);
    }

    @Test
    public void validate_FirstNameIsEmpty_ErrorsHaveBeenAdded(){
        UserCommand userCommand = new UserCommand();

        testee.validate(userCommand, errors);

        verify(errors, times(1)).rejectValue(USER_FIRST_NAME_FIELD, FIRST_NAME_IS_EMPTY_CODE, FIRST_NAME_IS_EMPTY_MESSAGE);
    }

    @Test
    public void validate_LastNameIsNotEmpty_NoErrorsHaveBeenAdded(){
        UserCommand userCommand = new UserCommand();
        userCommand.setLastName("Test Last Name");

        testee.validate(userCommand, errors);

        verify(errors, times(0)).rejectValue(USER_LAST_NAME_FIELD, LAST_NAME_IS_EMPTY_CODE, LAST_NAME_IS_EMPTY_MESSAGE);
    }

    @Test
    public void validate_LastNameIsEmpty_ErrorsHaveBeenAdded(){
        UserCommand userCommand = new UserCommand();

        testee.validate(userCommand, errors);

        verify(errors, times(1)).rejectValue(USER_LAST_NAME_FIELD, LAST_NAME_IS_EMPTY_CODE, LAST_NAME_IS_EMPTY_MESSAGE);
    }

    @Test
    public void validate_UserGroupIsNotEmpty_NoErrorsHaveBeenAdded(){
        UserCommand userCommand = new UserCommand();
        userCommand.setUserGroupId("Test User Group Id");

        testee.validate(userCommand, errors);

        verify(errors, times(0)).rejectValue(USER_GROUP_FIELD, USER_GROUP_IS_EMPTY_CODE, USER_GROUP_IS_EMPTY_MESSAGE);
    }

    @Test
    public void validate_UserGroupIsEmpty_ErrorsHaveBeenAdded(){
        UserCommand userCommand = new UserCommand();

        testee.validate(userCommand, errors);

        verify(errors, times(1)).rejectValue(USER_GROUP_FIELD, USER_GROUP_IS_EMPTY_CODE, USER_GROUP_IS_EMPTY_MESSAGE);
    }

    @Test
    public void validate_PasswordIsNotEmpty_NoErrorsHaveBeenAdded(){
        UserCommand userCommand = new UserCommand();
        userCommand.setPassword("Test Password");

        testee.validate(userCommand, errors);

        verify(errors, times(0)).rejectValue(PASSWORD_FIELD, PASSWORD_IS_EMPTY_CODE, PASSWORD_IS_EMPTY_MESSAGE);
    }

    @Test
    public void validate_PasswordIsEmpty_ErrorsHaveBeenAdded(){
        UserCommand userCommand = new UserCommand();

        testee.validate(userCommand, errors);

        verify(errors, times(1)).rejectValue(PASSWORD_FIELD, PASSWORD_IS_EMPTY_CODE, PASSWORD_IS_EMPTY_MESSAGE);
    }

    @Test
    public void validate_PasswordIsTooShort_ErrorHaveBeenAdded(){
        UserCommand userCommand = new UserCommand();
        String fiveCharactersLengthPassword = RandomStringUtils.random(5);
        userCommand.setPassword(fiveCharactersLengthPassword);

        testee.validate(userCommand, errors);

        verify(errors).rejectValue(PASSWORD_FIELD, PASSWORD_IS_WEAK_CODE, PASSWORD_IS_WEAK_MESSAGE);
    }

    @Test
    public void validate_PasswordSizeIsMinimum_ErrorHaveNotBeenAdded(){
        UserCommand userCommand = new UserCommand();
        String sixCharactersLengthPassword = RandomStringUtils.random(6);
        userCommand.setPassword(sixCharactersLengthPassword);

        testee.validate(userCommand, errors);

        verify(errors, times(0)).rejectValue(PASSWORD_FIELD, PASSWORD_IS_WEAK_CODE, PASSWORD_IS_WEAK_MESSAGE);
    }

    @Test
    public void validate_PasswordSizeIsBiggerThanMinimum_ErrorHaveNotBeenAdded(){
        UserCommand userCommand = new UserCommand();
        String sevenCharactersLengthPassword = RandomStringUtils.random(7);
        userCommand.setPassword(sevenCharactersLengthPassword);

        testee.validate(userCommand, errors);

        verify(errors, times(0)).rejectValue(PASSWORD_FIELD, PASSWORD_IS_WEAK_CODE, PASSWORD_IS_WEAK_MESSAGE);
    }

    @Test
    public void validate_PasswordSizeIsTooSmallButThePasswordEqualsOldPasswordMask_ErrorHaveNotBeenAdded(){
        UserCommand userCommand = new UserCommand();
        userCommand.setPassword(OLD_PASSWORD_MASK);

        testee.validate(userCommand, errors);

        verify(errors, times(0)).rejectValue(PASSWORD_FIELD, PASSWORD_IS_WEAK_CODE, PASSWORD_IS_WEAK_MESSAGE);
    }

}