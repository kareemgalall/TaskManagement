package banquemisr.challenge05.taskmanagement.service;

import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.exception.AuthorizationException;
import banquemisr.challenge05.taskmanagement.exception.PasswordInCorrectException;
import banquemisr.challenge05.taskmanagement.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;

public interface UserService {
    String authenticate(UserEntity userDto) throws UserNotFoundException;

    UserEntity register(UserEntity userDto);

    UserEntity getProfile() throws UserNotFoundException;

    UserEntity fullUpdate(Long id,UserEntity userEntity) throws UserNotFoundException, PasswordInCorrectException, AuthorizationException;

    void deleteUser(long id) throws UserNotFoundException;

    UserEntity partialUpdate(Long id, UserEntity userEntity) throws UserNotFoundException, PasswordInCorrectException, AuthorizationException;
}
