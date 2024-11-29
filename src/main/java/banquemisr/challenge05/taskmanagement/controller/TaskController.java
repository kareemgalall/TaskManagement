package banquemisr.challenge05.taskmanagement.controller;

import banquemisr.challenge05.taskmanagement.domain.model.TaskEntity;
import banquemisr.challenge05.taskmanagement.dto.TaskDto;
import banquemisr.challenge05.taskmanagement.dto.UserDto;
import banquemisr.challenge05.taskmanagement.mapper.Mapper;
import banquemisr.challenge05.taskmanagement.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    private Mapper<TaskEntity, TaskDto> mapper;
    private TaskService taskService;
    public TaskController(Mapper<TaskEntity, TaskDto> mapper, TaskService taskService) {
        this.taskService = taskService;
        this.mapper = mapper;
    }
    @PostMapping("/tasks/")
    public ResponseEntity<TaskDto>createTask(@RequestBody TaskDto taskDto){
        TaskEntity taskEntity=mapper.mapTo(taskDto);
        TaskEntity savedTask=taskService.createTask(taskEntity);
        return new ResponseEntity<>(mapper.mapFrom(savedTask),HttpStatus.CREATED);
    }
    @GetMapping("/hello")
    public ResponseEntity<String>hello(){
        return new ResponseEntity<>("Hello World",HttpStatus.OK);
    }
}
