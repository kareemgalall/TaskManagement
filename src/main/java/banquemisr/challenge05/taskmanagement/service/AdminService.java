package banquemisr.challenge05.taskmanagement.service;

import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.exception.UserNotFoundException;

public interface AdminService {
    UserEntity findById(long id) throws UserNotFoundException;
}
