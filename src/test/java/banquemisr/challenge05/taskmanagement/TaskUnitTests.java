package banquemisr.challenge05.taskmanagement;

import banquemisr.challenge05.taskmanagement.domain.model.HistoryEntity;
import banquemisr.challenge05.taskmanagement.domain.model.TaskEntity;
import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.exception.AuthorizationException;
import banquemisr.challenge05.taskmanagement.exception.TaskNotFoundException;
import banquemisr.challenge05.taskmanagement.exception.UserNotFoundException;
import banquemisr.challenge05.taskmanagement.repository.HistoryRepository;
import banquemisr.challenge05.taskmanagement.repository.TaskRepository;
import banquemisr.challenge05.taskmanagement.repository.UserRepository;
import banquemisr.challenge05.taskmanagement.service.UserUtilityService;
import banquemisr.challenge05.taskmanagement.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserUtilityService userUtilityService;

    @Mock
    private HistoryRepository historyRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void createTask_ShouldCreateTaskSuccessfully() throws UserNotFoundException {
        TaskEntity task = new TaskEntity();
        task.setTitle("Test Task");

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setEmail("test@example.com");

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(taskRepository.save(task)).thenReturn(task);

        TaskEntity result = taskService.createTask(task);

        assertNotNull(result);
        assertEquals(task, result);
        verify(taskRepository).save(task);
    }

    @Test
    void findById_ShouldReturnTask() throws TaskNotFoundException {
        TaskEntity task = new TaskEntity();
        task.setId(1L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        TaskEntity result = taskService.findById(1L);

        assertNotNull(result);
        assertEquals(task, result);
        verify(taskRepository).findById(1L);
    }

    @Test
    void findById_ShouldThrowTaskNotFoundException() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.findById(1L));
    }

    @Test
    void deleteTask_ShouldDeleteTaskSuccessfully() throws TaskNotFoundException, AuthorizationException {
        TaskEntity task = new TaskEntity();
        task.setId(1L);
        UserEntity user = new UserEntity();
        user.setId(1L);
        task.setUser(user);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(userUtilityService.getAuthenticatedUserId()).thenReturn(1L);

        taskService.deleteTask(1L);

        verify(taskRepository).deleteById(1L);
    }

    @Test
    void changeTaskStatus_ShouldUpdateStatusSuccessfully() throws TaskNotFoundException, AuthorizationException {
        TaskEntity task = new TaskEntity();
        task.setId(1L);
        UserEntity user = new UserEntity();
        user.setId(1L);
        task.setUser(user);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(userUtilityService.getAuthenticatedUserId()).thenReturn(1L);
        when(taskRepository.save(task)).thenReturn(task);

        TaskEntity result = taskService.changeTaskStatus(1L, "IN_PROGRESS");

        assertNotNull(result);
        assertEquals("IN_PROGRESS", result.getStatus());
        verify(taskRepository).save(task);
    }

    @Test
    void getAllTasks_ShouldReturnTasksPage() throws UserNotFoundException {
        UserEntity user = new UserEntity();
        user.setId(1L);
        Pageable pageable = PageRequest.of(0, 10);
        TaskEntity task = new TaskEntity();
        task.setId(1L);
        Page<TaskEntity> tasksPage = new PageImpl<>(Arrays.asList(task));

        when(userUtilityService.getAuthenticatedUserId()).thenReturn(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(taskRepository.findByUser(user, pageable)).thenReturn(tasksPage);

        Page<TaskEntity> result = taskService.getAllTasks(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(taskRepository).findByUser(user, pageable);
    }
}
