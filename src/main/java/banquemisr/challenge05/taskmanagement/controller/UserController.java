package banquemisr.challenge05.taskmanagement.controller;

import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.dto.ChangePasswordRequest;
import banquemisr.challenge05.taskmanagement.dto.UserDto;
import banquemisr.challenge05.taskmanagement.exception.AuthorizationException;
import banquemisr.challenge05.taskmanagement.exception.PasswordInCorrectException;
import banquemisr.challenge05.taskmanagement.exception.UserNotFoundException;
import banquemisr.challenge05.taskmanagement.mapper.Mapper;
import banquemisr.challenge05.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private UserService userService;
    private Mapper<UserEntity,UserDto> modelMapper;
    @Autowired
    public UserController(UserService userService, Mapper<UserEntity,UserDto> modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/users/authenticate")
    public String authenticate(@RequestBody UserEntity userDto) throws UserNotFoundException {
        return userService.authenticate(userDto);
    }

    @PostMapping("/users/register")
    public UserEntity register(@RequestBody UserEntity userDto) {
        return userService.register(userDto);
    }

    @GetMapping("/users/me")
    public ResponseEntity<UserDto>getProfile() throws UserNotFoundException {
        UserEntity currentUser=userService.getProfile();
        return new ResponseEntity<>(modelMapper.mapFrom(currentUser), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) throws UserNotFoundException, PasswordInCorrectException, AuthorizationException {
        UserEntity userEntity=modelMapper.mapTo(userDto);
        UserEntity updatedUser = userService.fullUpdate(id,userEntity);
        return new ResponseEntity<>(modelMapper.mapFrom(updatedUser), HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UserDto>partialUpdate(@PathVariable("id") Long id, @RequestBody UserDto userDto) throws UserNotFoundException, PasswordInCorrectException, AuthorizationException {
        UserEntity userEntity=modelMapper.mapTo(userDto);
        UserEntity updatedUser=userService.partialUpdate(id,userEntity);
        return new ResponseEntity<>(modelMapper.mapFrom(updatedUser), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) throws UserNotFoundException {
        userService.changePassword(request.getEmail(), request.getOldPassword(), request.getNewPassword());
        return ResponseEntity.ok("Password changed successfully.");
    }

}
