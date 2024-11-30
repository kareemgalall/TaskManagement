package banquemisr.challenge05.taskmanagement.service.impl;

import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserEntityDetailsService implements UserDetailsService {
    UserRepository userRepository;
    public UserEntityDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByEmail(username);
        if(userEntity.isPresent()) {
            return User.builder()
                    .username(userEntity.get().getEmail())
                    .roles(userEntity.get().getRole())
                    .password(userEntity.get().getPassword())
                    .build();
        }
        else
        {
            throw new UsernameNotFoundException(username);
        }
    }
}
