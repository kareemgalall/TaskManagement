package banquemisr.challenge05.taskmanagement;

import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import banquemisr.challenge05.taskmanagement.mail.MailService;
import banquemisr.challenge05.taskmanagement.repository.UserRepository;
import banquemisr.challenge05.taskmanagement.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Optional;

@SpringBootApplication
@EnableScheduling
public class TaskManagementApplication {
    @Autowired
    private UserRepository userService;
    public static void main(String[] args) {
        SpringApplication.run(TaskManagementApplication.class, args);
    }
    @EventListener(ApplicationReadyEvent.class)
    public void addAdminUser() throws MessagingException {
        if(Optional.ofNullable(userService.findByEmail("admin@gmail.com")).isPresent())
        {
            return;
        }
        UserEntity user = new UserEntity();
        user.setName("admin");
        user.setEmail("admin@gmail.com");
        user.setPassword("$2a$10$gn/xPjpjqqeb2XWa.dumau1ymf5CYmcZZKWBLlYHDMgjgr9OBPdCS");
        user.setRole("ADMIN");
        System.out.println("admin user created");
        userService.save(user);
    }
}
