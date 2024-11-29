package banquemisr.challenge05.taskmanagement.exception;

import java.util.NoSuchElementException;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
