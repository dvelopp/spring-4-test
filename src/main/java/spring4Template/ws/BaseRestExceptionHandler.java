package spring4Template.ws;

import org.springframework.web.bind.annotation.*;
import spring4Template.ws.system.schema.ExceptionResponse;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice(annotations = RestController.class)
public class BaseRestExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionResponse exception(Throwable exception) {
        return new ExceptionResponse("There is an error while processing the action. " + exception.getMessage());
    }

}
