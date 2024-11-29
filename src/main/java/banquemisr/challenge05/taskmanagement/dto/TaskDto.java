package banquemisr.challenge05.taskmanagement.dto;

import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TaskDto {
    Long id;
    String title;
    String description;
    String priority;
    String status;
    Date dueDate;
    UserDto user;
}
