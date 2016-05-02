package spring4Template.ws.system.schema;

import java.util.List;
import java.util.Map;

public class ValidationErrorsResponse {

    private Map<String, String> fieldErrors;
    private List<String> globalErrors;

    public ValidationErrorsResponse(Map<String, String> fieldErrors, List<String> globalErrors) {
        this.fieldErrors = fieldErrors;
        this.globalErrors = globalErrors;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public List<String> getGlobalErrors() {
        return globalErrors;
    }
}
