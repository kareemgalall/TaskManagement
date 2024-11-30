package banquemisr.challenge05.taskmanagement.service;

import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;

public interface UserService {
    String authenticate(UserEntity userDto);

    UserEntity register(UserEntity userDto);
}
