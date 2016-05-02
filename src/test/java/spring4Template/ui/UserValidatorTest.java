package spring4Template.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;
import spring4Template.domain.model.UserCommand;
import spring4Template.ws.user.UserValidator;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static spring4Template.ws.user.UserValidator.USER_NAME_IS_EMPTY_CODE;
import static spring4Template.ws.user.UserValidator.USER_NAME_IS_EMPTY_MESSAGE;
import static spring4Template.ws.user.UserValidationFieldsConstants.USER_NAME_FIELD;

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {

    @InjectMocks private UserValidator testee;
    @Mock private Errors errors;

    @Test
    public void validate_NotEmptyName_NoErrorsHaveBeenAdded(){
        UserCommand userCommand = new UserCommand();
        userCommand.setUserName("Test User Name");

        testee.validate(userCommand, errors);

        verify(errors, times(0)).rejectValue(USER_NAME_FIELD, USER_NAME_IS_EMPTY_CODE, USER_NAME_IS_EMPTY_MESSAGE);
    }

    @Test
    public void validate_EmptyName_ErrorsHaveBeenAdded(){
        UserCommand userCommand = new UserCommand();

        testee.validate(userCommand, errors);

        verify(errors, times(1)).rejectValue(USER_NAME_FIELD, USER_NAME_IS_EMPTY_CODE, USER_NAME_IS_EMPTY_MESSAGE);
    }

}