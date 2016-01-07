package spring4Template.ui;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import spring4Template.domain.model.UserCommand;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static spring4Template.domain.entities.User.OLD_PASSWORD_MASK;
import static spring4Template.ui.validation.UserValidationFieldsConstants.*;

@Component
public class UserValidator implements Validator {

    public static final String USER_NAME_IS_EMPTY_CODE = "user.empty-name";
    public static final String USER_NAME_IS_EMPTY_MESSAGE = "You have to specify user name";

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UserCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCommand userCommand = (UserCommand) target;
        if (isBlank(userCommand.getUserName())) {
            errors.rejectValue(USER_NAME_FIELD, USER_NAME_IS_EMPTY_CODE, USER_NAME_IS_EMPTY_MESSAGE);
        }
        if (isBlank(userCommand.getFirstName())) {
            errors.rejectValue(USER_FIRST_NAME_FIELD, "user.empty-first-name", "You have to specify first name");
        }
        if (isBlank(userCommand.getLastName())) {
            errors.rejectValue(USER_LAST_NAME_FIELD, "user.empty-last-name", "You have to specify last name");
        }
        if(isBlank(userCommand.getUserGroupId())){
            errors.rejectValue(USER_GROUP_FIELD, "user.empty-user-group", "You have to choose user group");
        }
        validatePassword(errors, userCommand);

    }

    private void validatePassword(Errors errors, UserCommand userCommand) {
        if (isBlank(userCommand.getPassword())) {
            errors.rejectValue("password", "user.empty-password-name", "You have to specify password");
        } else {
            if(userCommand.getPassword().equals(OLD_PASSWORD_MASK)){
                return;
            }
            if (userCommand.getPassword().length() < 6) {
                errors.rejectValue("password", "user.weak-password", "Password is too short");
            }
        }
    }

}
