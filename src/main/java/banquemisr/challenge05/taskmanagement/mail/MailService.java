package banquemisr.challenge05.taskmanagement.mail;

import banquemisr.challenge05.taskmanagement.domain.model.TaskEntity;
import banquemisr.challenge05.taskmanagement.repository.TaskRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

public class MailService {
    private JavaMailSender mailSender;
    private TaskRepository taskRepository;
    public MailService(JavaMailSender mailSender,TaskRepository taskRepository) {
        this.mailSender = mailSender;
        this.taskRepository = taskRepository;
    }
    @Scheduled(fixedRate = 18000000, zone = "Africa/Cairo")
    public void sendTaskNotifications() {
        List<TaskEntity> tasks = taskRepository.findAll(); // Retrieve all tasks from the database

        for (TaskEntity task : tasks) {
                sendMail(task);
                System.out.println("mail sent");
        }

    }

    public void sendMail(TaskEntity task) {
        SimpleMailMessage message = new SimpleMailMessage();
        String toMail=task.getUser().getEmail();
        message.setFrom("kareemgalal1890@gmail.com");
        message.setTo(toMail);
        message.setSubject("task notification");
        String messageText = "Task: " + task.getTitle() + "\n"
                + "Description: " + task.getDescription() + "\n"
                + "Due Date: " + task.getDueDate();
        message.setText(messageText);
        mailSender.send(message);
        System.out.println("Sent message successfully");
    }
}
