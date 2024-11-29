package banquemisr.challenge05.taskmanagement.service.impl;

import banquemisr.challenge05.taskmanagement.domain.model.TaskEntity;
import banquemisr.challenge05.taskmanagement.repository.TaskRepository;
import banquemisr.challenge05.taskmanagement.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    TaskRepository taskRepository;
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public TaskEntity createTask(TaskEntity task) {
        return taskRepository.save(task);
    }
}
