package banquemisr.challenge05.taskmanagement.controller;

import banquemisr.challenge05.taskmanagement.domain.model.TaskEntity;
import banquemisr.challenge05.taskmanagement.dto.TaskDto;
import banquemisr.challenge05.taskmanagement.exception.TaskNotFoundException;
import banquemisr.challenge05.taskmanagement.mapper.Mapper;
import banquemisr.challenge05.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskController {
    private Mapper<TaskEntity, TaskDto> mapper;
    private TaskService taskService;

    public TaskController(Mapper<TaskEntity, TaskDto> mapper, TaskService taskService) {
        this.taskService = taskService;
        this.mapper = mapper;
    }

    @PostMapping("/tasks/")
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto) {

        TaskEntity taskEntity = mapper.mapTo(taskDto);

        TaskEntity savedTask = taskService.createTask(taskEntity);
        return new ResponseEntity<>(mapper.mapFrom(savedTask), HttpStatus.CREATED);

    }

    @GetMapping("tasks/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable Long id) throws TaskNotFoundException {
        TaskEntity foundTask = taskService.findById(id);
        return new ResponseEntity<>(mapper.mapFrom(foundTask), HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks().stream()
                .map(mapper::mapFrom)
                .collect(Collectors.toList());
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> fullUpdateTask(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto) throws TaskNotFoundException {
        TaskEntity taskEntity = mapper.mapTo(taskDto);
        taskService.fullUpdateTask(id, taskEntity);
        return new ResponseEntity<>(mapper.mapFrom(taskEntity), HttpStatus.OK);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> partialUpdateTask(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto) throws TaskNotFoundException {
        TaskEntity taskEntity = mapper.mapTo(taskDto);
        TaskEntity savedTask = taskService.partialUpdateTask(id, taskEntity);
        return new ResponseEntity<>(mapper.mapFrom(savedTask), HttpStatus.OK);
    }

}
