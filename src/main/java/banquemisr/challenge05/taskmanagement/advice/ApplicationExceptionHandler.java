package banquemisr.challenge05.taskmanagement.advice;

import banquemisr.challenge05.taskmanagement.exception.AuthorizationException;
import banquemisr.challenge05.taskmanagement.exception.PasswordInCorrectException;
import banquemisr.challenge05.taskmanagement.exception.TaskNotFoundException;
import banquemisr.challenge05.taskmanagement.exception.UserNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;

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

    @ExceptionHandler(AuthorizationException.class)
    public Map<String, String> handleAuthorizationException(AuthorizationException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TaskNotFoundException.class)
    public Map<String, String> handleTaskNotFoundException(TaskNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, String> errorMap = new HashMap<>();
        String message = ex.getMessage().toLowerCase();
        if (message.contains("email")&&message.contains("unique")) { // Simple check for the word "unique" in the error message
            errorMap.put("errorMessage", "Email already exists. Please use another email.");
        } else {
            errorMap.put("errorMessage", ex.getMessage());
        }

        return errorMap;
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(PasswordInCorrectException.class)
    public Map<String, String> IllegalArgumentException(PasswordInCorrectException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", "old password in correct.");
        return errorMap;
    }

    @ExceptionHandler(Exception.class)
    public Map<String, String> handleSecurityException(Exception exception) {
        Map<String, String> errorMap = new HashMap<>();

        if (exception instanceof BadCredentialsException) {
            errorMap.put("description", "The username or password is incorrect");
        }

        if (exception instanceof AccountStatusException) {
            errorMap.put("description", "The account is locked");
        }

        if (exception instanceof AccessDeniedException) {
            errorMap.put("description", "You are not authorized to access this resource");
        }

        if (exception instanceof SignatureException) {
            errorMap.put("description", "The JWT signature is invalid");
        }

        if (errorMap == null) {
            errorMap.put("description", "Unknown internal server error.");
        }

        return errorMap;
    }
}