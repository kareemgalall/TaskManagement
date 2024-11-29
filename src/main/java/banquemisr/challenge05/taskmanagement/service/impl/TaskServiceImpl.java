package banquemisr.challenge05.taskmanagement.service.impl;

import banquemisr.challenge05.taskmanagement.domain.model.TaskEntity;
import banquemisr.challenge05.taskmanagement.exception.TaskNotFoundException;
import banquemisr.challenge05.taskmanagement.repository.TaskRepository;
import banquemisr.challenge05.taskmanagement.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public TaskEntity findById(Long id) throws TaskNotFoundException {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    }

    @Override
    public TaskEntity fullUpdateTask(Long id, TaskEntity taskEntity) throws TaskNotFoundException {
        if(!taskRepository.existsById(id))
        {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
        taskEntity.setId(id);
        return taskRepository.save(taskEntity);
    }

    @Override
    public TaskEntity partialUpdateTask(Long id, TaskEntity taskEntity) throws TaskNotFoundException {
        TaskEntity taskEntity1= taskRepository.findById(id).map(
                existingTask -> {
                    Optional.ofNullable(taskEntity.getTitle()).ifPresent(existingTask::setTitle);
                    Optional.ofNullable(taskEntity.getDescription()).ifPresent(existingTask::setDescription);
                    Optional.ofNullable(taskEntity.getDueDate()).ifPresent(existingTask::setDueDate);
                    Optional.ofNullable(taskEntity.getPriority()).ifPresent(existingTask::setPriority);
                    Optional.ofNullable(taskEntity.getStatus()).ifPresent(existingTask::setStatus);
                    return existingTask;
                }
        ).orElseThrow(()->new TaskNotFoundException("task not found"));
        taskEntity1.setId(id);
        return taskRepository.save(taskEntity1);
    }

    @Override
    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }
}
