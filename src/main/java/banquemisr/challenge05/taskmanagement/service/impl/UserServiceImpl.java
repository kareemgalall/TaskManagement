package banquemisr.challenge05.taskmanagement.service.impl;
import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.exception.AuthorizationException;
import banquemisr.challenge05.taskmanagement.exception.PasswordInCorrectException;
import banquemisr.challenge05.taskmanagement.exception.UserNotFoundException;
import banquemisr.challenge05.taskmanagement.repository.UserRepository;
import banquemisr.challenge05.taskmanagement.service.UserService;
import banquemisr.challenge05.taskmanagement.webtoken.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserEntityDetailsService userEntityDetailsService;
    public UserServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder,UserEntityDetailsService userEntityDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userEntityDetailsService=userEntityDetailsService;
    }

    public String authenticate(UserEntity userDto) throws UserNotFoundException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDto.getEmail(), userDto.getPassword()
        ));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userEntityDetailsService.loadUserByUsername(userDto.getEmail()));
        } else {
            throw new UserNotFoundException("Invalid email or password");
        }
    }

    public UserEntity register(UserEntity userEntity) {
        userEntity.setRole("USER");
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getProfile() throws UserNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email=auth.getPrincipal().toString();
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        return user;
    }

    @Override
    public UserEntity fullUpdate(Long id,UserEntity userEntity) throws UserNotFoundException, PasswordInCorrectException, AuthorizationException {
        Long authenticatedUserId = getAuthenticatedUserId();
        if(!authenticatedUserId.equals(id)){
            throw new AuthorizationException("you dont have the access to this function");
        }
        userEntity.setId(id);
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        //check if user sent correct old password
        verifyPassword(userEntity, existingUser);
        userEntity.setId(id);
        userEntity.setPassword(existingUser.getPassword()); // Keep the old password as-is
        userEntity.setRole("USER");
        return userRepository.save(userEntity);
    }

    private void verifyPassword(UserEntity userEntity, UserEntity existingUser) throws PasswordInCorrectException {
        if (!passwordEncoder.matches(userEntity.getPassword(), existingUser.getPassword())) {
            throw new PasswordInCorrectException("Old password is incorrect.");
        }
    }

    public Long getAuthenticatedUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            String email = auth.getPrincipal().toString();
            return userRepository.findByEmail(email).get().getId();
        }
        throw new SecurityException("User not authenticated or incorrect principal type.");
    }
}
