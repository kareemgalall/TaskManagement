package banquemisr.challenge05.taskmanagement.service.impl;

import banquemisr.challenge05.taskmanagement.domain.model.TaskEntity;
import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.exception.AuthorizationException;
import banquemisr.challenge05.taskmanagement.exception.TaskNotFoundException;
import banquemisr.challenge05.taskmanagement.exception.UserNotFoundException;
import banquemisr.challenge05.taskmanagement.repository.TaskRepository;
import banquemisr.challenge05.taskmanagement.repository.UserRepository;
import banquemisr.challenge05.taskmanagement.service.TaskService;
import banquemisr.challenge05.taskmanagement.service.UserUtilityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final UserUtilityService userUtilityService;
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository,UserUtilityService userUtilityService) {
        this.taskRepository = taskRepository;
        this.userRepository=userRepository;
        this.userUtilityService = userUtilityService;
    }
    @Override
    public TaskEntity createTask(TaskEntity task) throws UserNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email=auth.getPrincipal().toString();
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        task.setUser(user);
        return taskRepository.save(task);
    }

    @Override
    public TaskEntity findById(Long id) throws TaskNotFoundException {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    }

    @Override
    public TaskEntity fullUpdateTask(Long id, TaskEntity taskEntity) throws TaskNotFoundException, AuthorizationException {
        TaskEntity existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        authorizationCheck(existingTask);
        taskEntity.setUser(existingTask.getUser());
        taskEntity.setId(id);
        return taskRepository.save(taskEntity);
    }

    @Override
    public TaskEntity partialUpdateTask(Long id, TaskEntity taskEntity)
            throws TaskNotFoundException, AuthorizationException {

        // Fetch the existing task and verify ownership
        TaskEntity existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        authorizationCheck(existingTask);
        // Update the task fields
        Optional.ofNullable(taskEntity.getTitle()).ifPresent(existingTask::setTitle);
        Optional.ofNullable(taskEntity.getDescription()).ifPresent(existingTask::setDescription);
        Optional.ofNullable(taskEntity.getDueDate()).ifPresent(existingTask::setDueDate);
        Optional.ofNullable(taskEntity.getPriority()).ifPresent(existingTask::setPriority);
        Optional.ofNullable(taskEntity.getStatus()).ifPresent(existingTask::setStatus);
        existingTask.setId(id);
        // Save and return the updated task
        return taskRepository.save(existingTask);
    }

    @Override
    public Page<TaskEntity> getAllTasks(Pageable pageable) throws UserNotFoundException {
        Long userId=userUtilityService.getAuthenticatedUserId();
        UserEntity user=userRepository.findById(userId).orElseThrow(
                ()->new UserNotFoundException("user not found")
        );
        return taskRepository.findByUser(user,pageable);
    }

    @Override
    public void deleteTask(Long id) throws TaskNotFoundException, AuthorizationException {
        TaskEntity existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        authorizationCheck(existingTask);
        taskRepository.deleteById(id);
    }

    private void authorizationCheck(TaskEntity existingTask) throws AuthorizationException {
        //Get the authenticated user's ID
        Long authenticatedUserId = userUtilityService.getAuthenticatedUserId();
        // Check if the authenticated user owns the task
        if (!existingTask.getUser().getId().equals(authenticatedUserId)) {
            throw new AuthorizationException("You do not have access to update this task.");
        }
    }

}
