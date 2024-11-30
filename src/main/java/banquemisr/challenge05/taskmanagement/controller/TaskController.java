package banquemisr.challenge05.taskmanagement.controller;

import banquemisr.challenge05.taskmanagement.domain.model.HistoryEntity;
import banquemisr.challenge05.taskmanagement.domain.model.TaskEntity;
import banquemisr.challenge05.taskmanagement.dto.TaskDto;
import banquemisr.challenge05.taskmanagement.exception.AuthorizationException;
import banquemisr.challenge05.taskmanagement.exception.TaskNotFoundException;
import banquemisr.challenge05.taskmanagement.exception.UserNotFoundException;
import banquemisr.challenge05.taskmanagement.mapper.Mapper;
import banquemisr.challenge05.taskmanagement.repository.TaskRepository;
import banquemisr.challenge05.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TaskController {
    private final TaskRepository taskRepository;
    private Mapper<TaskEntity, TaskDto> mapper;
    private TaskService taskService;

    public TaskController(Mapper<TaskEntity, TaskDto> mapper, TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.mapper = mapper;
        this.taskRepository = taskRepository;
    }

    @PostMapping("/tasks/")
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto) throws UserNotFoundException {

        TaskEntity taskEntity = mapper.mapTo(taskDto);

        TaskEntity savedTask = taskService.createTask(taskEntity);
        return new ResponseEntity<>(mapper.mapFrom(savedTask), HttpStatus.CREATED);

    }

    @GetMapping("tasks/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable Long id) throws TaskNotFoundException {
        TaskEntity foundTask = taskService.findById(id);
        return new ResponseEntity<>(mapper.mapFrom(foundTask), HttpStatus.OK);
    }

    @GetMapping("/tasks/")
    public Page<TaskDto> getAllTasks(Pageable pageable) throws UserNotFoundException {
        return taskService.getAllTasks(pageable)
                .map(mapper::mapFrom);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> fullUpdateTask(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto) throws TaskNotFoundException, AuthorizationException {
        TaskEntity taskEntity = mapper.mapTo(taskDto);
        taskService.fullUpdateTask(id, taskEntity);
        return new ResponseEntity<>(mapper.mapFrom(taskEntity), HttpStatus.OK);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> partialUpdateTask(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto) throws TaskNotFoundException, AuthorizationException {
        TaskEntity taskEntity = mapper.mapTo(taskDto);
        TaskEntity savedTask = taskService.partialUpdateTask(id, taskEntity);
        return new ResponseEntity<>(mapper.mapFrom(savedTask), HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) throws TaskNotFoundException, AuthorizationException {
        taskService.deleteTask(id);
        String message = "Task with id " + id + " was deleted successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @PutMapping("tasks/{id}/changestatus")
    public ResponseEntity<String> changeTaskStatus(@PathVariable Long id,@RequestBody Map<String, String> requestBody) throws TaskNotFoundException, AuthorizationException  {
        String status= requestBody.get("status");
        taskService.changeTaskStatus(id, status);
        return new ResponseEntity<>("status updated", HttpStatus.OK);
   }

   @GetMapping("/tasks/search")
   public ResponseEntity<List<TaskDto>> searchTasks(
           @RequestParam(required = false) String title,
           @RequestParam(required = false) String description,
           @RequestParam(required = false) String status,
           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dueDate
   ) {
       List<TaskEntity> tasks = taskService.searchTasks(title, description, status, dueDate);

       // Map the results to DTOs
       List<TaskDto> taskDtos = tasks.stream()
               .map(mapper::mapFrom)
               .collect(Collectors.toList());

       return new ResponseEntity<>(taskDtos, HttpStatus.OK);

   }
}
