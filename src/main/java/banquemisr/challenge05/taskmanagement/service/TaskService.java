package banquemisr.challenge05.taskmanagement.service;

import banquemisr.challenge05.taskmanagement.domain.model.TaskEntity;
import banquemisr.challenge05.taskmanagement.exception.TaskNotFoundException;

import java.util.List;

public interface TaskService {
    public TaskEntity createTask(TaskEntity task);

    TaskEntity findById(Long id) throws TaskNotFoundException;

    TaskEntity fullUpdateTask(Long id, TaskEntity taskEntity) throws TaskNotFoundException;

    TaskEntity partialUpdateTask(Long id, TaskEntity taskEntity) throws TaskNotFoundException;

    List<TaskEntity> getAllTasks();

    void deleteTask(Long id) throws TaskNotFoundException;
}
