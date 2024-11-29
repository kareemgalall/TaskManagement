package banquemisr.challenge05.taskmanagement.advice;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleInvalidArgument(ConstraintViolationException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getConstraintViolations().forEach(constraintViolation -> {
            errorMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        });
        return errorMap;
    }


}