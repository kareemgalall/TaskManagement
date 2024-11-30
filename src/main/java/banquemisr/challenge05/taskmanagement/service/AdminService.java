package banquemisr.challenge05.taskmanagement.service;

import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.dto.UserDto;
import banquemisr.challenge05.taskmanagement.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {
    UserEntity findById(long id) throws UserNotFoundException;

    Page<UserEntity> getAllUsers(Pageable pageable);
}
