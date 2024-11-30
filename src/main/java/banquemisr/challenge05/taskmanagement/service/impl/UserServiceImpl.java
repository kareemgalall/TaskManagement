package banquemisr.challenge05.taskmanagement.service.impl;
import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.repository.UserRepository;
import banquemisr.challenge05.taskmanagement.service.UserService;
import banquemisr.challenge05.taskmanagement.webtoken.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public String authenticate(UserEntity userDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDto.getEmail(), userDto.getPassword()
        ));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userEntityDetailsService.loadUserByUsername(userDto.getEmail()));
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }

    public UserEntity register(UserEntity userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(userDto);
    }

}
