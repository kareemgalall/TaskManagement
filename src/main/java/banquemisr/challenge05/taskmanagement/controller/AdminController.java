package banquemisr.challenge05.taskmanagement.controller;

import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.dto.UserDto;
import banquemisr.challenge05.taskmanagement.exception.UserNotFoundException;
import banquemisr.challenge05.taskmanagement.mapper.Mapper;
import banquemisr.challenge05.taskmanagement.service.AdminService;
import banquemisr.challenge05.taskmanagement.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdminController {
    private AdminService adminService;
    private Mapper<UserEntity,UserDto> modelMapper;
    public AdminController(AdminService adminService, Mapper<UserEntity,UserDto> modelMapper) {
        this.adminService = adminService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("admin/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable long id) throws UserNotFoundException {
        UserEntity foundUser=adminService.findById(id);
        UserDto userDto=modelMapper.mapFrom(foundUser);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("admin/users")
    public Page<UserDto> getAllUsers(Pageable pageable,@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
       return adminService.getAllUsers(pageable)
                .map(modelMapper::mapFrom);
    }
}
