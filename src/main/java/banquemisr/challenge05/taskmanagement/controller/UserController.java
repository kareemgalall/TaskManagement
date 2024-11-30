package banquemisr.challenge05.taskmanagement.controller;

import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody UserEntity userDto) {
        return userService.authenticate(userDto);
    }

    @PostMapping("/register/user")
    public UserEntity register(@RequestBody UserEntity userDto) {
        return userService.register(userDto);
    }
}
