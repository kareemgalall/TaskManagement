package banquemisr.challenge05.taskmanagement.service.impl;

import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.dto.UserDto;
import banquemisr.challenge05.taskmanagement.exception.UserNotFoundException;
import banquemisr.challenge05.taskmanagement.repository.UserRepository;
import banquemisr.challenge05.taskmanagement.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    UserRepository userRepository;
    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserEntity findById(long id) throws UserNotFoundException {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        return user;
    }

    @Override
    public Page<UserEntity> getAllUsers(Pageable pageable) {
        return userRepository.findByRole("USER",pageable);
    }


}
