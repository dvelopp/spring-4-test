package spring4Template.ws.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import spring4Template.domain.model.UserCommand;
import spring4Template.ws.system.ValidationErrorsException;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static spring4Template.domain.entities.User.MIN_PASSWORD_LENGTH;
import static spring4Template.domain.entities.User.OLD_PASSWORD_MASK;
import static spring4Template.ws.user.UserValidationConstants.*;

@Component
public class UserValidator implements Validator {

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
            errors.rejectValue(USER_FIRST_NAME_FIELD, FIRST_NAME_IS_EMPTY_CODE, FIRST_NAME_IS_EMPTY_MESSAGE);
        }
        if (isBlank(userCommand.getLastName())) {
            errors.rejectValue(USER_LAST_NAME_FIELD, LAST_NAME_IS_EMPTY_CODE, LAST_NAME_IS_EMPTY_MESSAGE);
        }
        if (isBlank(userCommand.getUserGroupId())) {
            errors.rejectValue(USER_GROUP_FIELD, USER_GROUP_IS_EMPTY_CODE, USER_GROUP_IS_EMPTY_MESSAGE);
        }
        validatePassword(errors, userCommand);
        if (errors.hasErrors()) {
            throw new ValidationErrorsException(errors);
        }
    }

    private void validatePassword(Errors errors, UserCommand userCommand) {
        if (isBlank(userCommand.getPassword())) {
            errors.rejectValue(PASSWORD_FIELD, PASSWORD_IS_EMPTY_CODE, PASSWORD_IS_EMPTY_MESSAGE);
        } else {
            if (userCommand.getPassword().equals(OLD_PASSWORD_MASK)) {
                return;
            }
            if (userCommand.getPassword().length() < MIN_PASSWORD_LENGTH) {
                errors.rejectValue(PASSWORD_FIELD, PASSWORD_IS_WEAK_CODE, PASSWORD_IS_WEAK_MESSAGE);
            }
        }
    }

}
