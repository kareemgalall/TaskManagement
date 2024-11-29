package banquemisr.challenge05.taskmanagement.service;

import banquemisr.challenge05.taskmanagement.domain.model.TaskEntity;
import banquemisr.challenge05.taskmanagement.exception.TaskNotFoundException;

public interface TaskService {
    public TaskEntity createTask(TaskEntity task);

    TaskEntity findById(Long id) throws TaskNotFoundException;
}
