package spring4Template.utils;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;

import static java.util.stream.Collectors.toMap;

@Component
public class ValidationResultFactory {

    public Map<String, String> getFiledValidationResult(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream().collect(getFieldValidationCollector());
    }

    private Collector<FieldError, ?, Map<String, String>> getFieldValidationCollector() {
        return toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage);
    }

}
