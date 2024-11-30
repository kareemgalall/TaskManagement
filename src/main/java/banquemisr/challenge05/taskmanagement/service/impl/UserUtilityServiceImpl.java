package banquemisr.challenge05.taskmanagement.service.impl;

import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.exception.PasswordInCorrectException;
import banquemisr.challenge05.taskmanagement.repository.UserRepository;
import banquemisr.challenge05.taskmanagement.service.UserUtilityService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserUtilityServiceImpl implements UserUtilityService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public  UserUtilityServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Long getAuthenticatedUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            String email = auth.getPrincipal().toString();
            return userRepository.findByEmail(email).get().getId();
        }
        throw new SecurityException("User not authenticated or incorrect principal type.");
    }
    public void verifyPassword(UserEntity userEntity, UserEntity existingUser) throws PasswordInCorrectException {
        if (!passwordEncoder.matches(userEntity.getPassword(), existingUser.getPassword())) {
            throw new PasswordInCorrectException("Old password is incorrect.");
        }
    }

}
