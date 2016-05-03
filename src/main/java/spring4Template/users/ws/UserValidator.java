package spring4Template.users.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import spring4Template.system.ws.ValidationErrorsException;
import spring4Template.users.domain.model.UserCommand;
import spring4Template.users.domain.repositories.UserRepository;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static spring4Template.users.domain.entities.User.MIN_PASSWORD_LENGTH;
import static spring4Template.users.domain.entities.User.OLD_PASSWORD_MASK;
import static spring4Template.users.ws.UserValidationConstants.*;

@Component
public class UserValidator implements Validator {

    @Autowired private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UserCommand.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCommand userCommand = (UserCommand) target;
        validateUserName(errors, userCommand);
        if (isBlank(userCommand.getFirstName())) {
            errors.rejectValue(USER_FIRST_NAME_FIELD, FIRST_NAME_IS_EMPTY_CODE);
        }
        if (isBlank(userCommand.getLastName())) {
            errors.rejectValue(USER_LAST_NAME_FIELD, LAST_NAME_IS_EMPTY_CODE);
        }
        if (isBlank(userCommand.getUserGroupId())) {
            errors.rejectValue(USER_GROUP_FIELD, USER_GROUP_IS_EMPTY_CODE);
        }
        validatePassword(errors, userCommand);
        if (errors.hasErrors()) {
            throw new ValidationErrorsException(errors);
        }
    }

    private void validateUserName(Errors errors, UserCommand userCommand) {
        if (isBlank(userCommand.getUserName())) {
            errors.rejectValue(USER_NAME_FIELD, USER_NAME_IS_EMPTY_CODE);
        } else {
            if(userCommand.isNew()){
                if( userRepository.existsByName(userCommand.getUserName())){
                    errors.rejectValue(USER_NAME_FIELD, USER_NAME_EXISTS_CODE);
                }
            }
        }
    }

    private void validatePassword(Errors errors, UserCommand userCommand) {
        if (isBlank(userCommand.getPassword())) {
            errors.rejectValue(PASSWORD_FIELD, PASSWORD_IS_EMPTY_CODE);
        } else {
            if (userCommand.getPassword().equals(OLD_PASSWORD_MASK)) {
                return;
            }
            if (userCommand.getPassword().length() < MIN_PASSWORD_LENGTH) {
                errors.rejectValue(PASSWORD_FIELD, PASSWORD_IS_WEAK_CODE);
            }
        }
    }

}
