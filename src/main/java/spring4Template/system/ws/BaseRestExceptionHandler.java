package spring4Template.system.ws;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import spring4Template.system.ws.schema.ExceptionResponse;
import spring4Template.system.ws.schema.ValidationErrorsResponse;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice(annotations = RestController.class)
public class BaseRestExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionResponse exception(Throwable exception) {
        return new ExceptionResponse("There is an error while processing the action. " + exception.getMessage());
    }

    @ExceptionHandler(ValidationErrorsException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ValidationErrorsResponse exception(ValidationErrorsException exception) {

        Map<String, String> fieldErrors = exception.getErrors().getFieldErrors().stream()
                .collect(toMap(FieldError::getField, DefaultMessageSourceResolvable::getCode));

        List<String> globalErrors = exception.getErrors().getGlobalErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(toList());

        return new ValidationErrorsResponse(fieldErrors, globalErrors);
    }
}
