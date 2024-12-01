package banquemisr.challenge05.taskmanagement.service;

import banquemisr.challenge05.taskmanagement.domain.model.TaskEntity;
import banquemisr.challenge05.taskmanagement.exception.AuthorizationException;
import banquemisr.challenge05.taskmanagement.exception.TaskNotFoundException;
import banquemisr.challenge05.taskmanagement.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface TaskService {
    public TaskEntity createTask(TaskEntity task) throws UserNotFoundException;

    TaskEntity findById(Long id) throws TaskNotFoundException;

    TaskEntity fullUpdateTask(Long id, TaskEntity taskEntity) throws TaskNotFoundException, AuthorizationException;

    TaskEntity partialUpdateTask(Long id, TaskEntity taskEntity) throws TaskNotFoundException, AuthorizationException;

    Page<TaskEntity> getAllTasks(Pageable pageable) throws UserNotFoundException;

    void deleteTask(Long id) throws TaskNotFoundException, AuthorizationException;

    TaskEntity changeTaskStatus(Long id,String newStatus) throws TaskNotFoundException, AuthorizationException;

    List<TaskEntity> searchTasks(String title, String description, String status, Date dueDate,String priority);
}
