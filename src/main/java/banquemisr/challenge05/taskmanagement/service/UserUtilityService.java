package banquemisr.challenge05.taskmanagement.service;

import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.exception.PasswordInCorrectException;

public interface UserUtilityService {
    public Long getAuthenticatedUserId();

    void verifyPassword(UserEntity userEntity, UserEntity existingUser) throws PasswordInCorrectException;
}
