package spring4Template.ws.schema;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.util.List;

public class UserSavingResponse {

    private HttpStatus httpStatus;
    private List<ObjectError> errors;

    public UserSavingResponse(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public UserSavingResponse(HttpStatus httpStatus, List<ObjectError> errors) {
        this.httpStatus = httpStatus;
        this.errors = errors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

    public void setErrors(List<ObjectError> errors) {
        this.errors = errors;
    }
}
