package banquemisr.challenge05.taskmanagement.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tasks")
public class TaskEntity {
    @Id
    Long id;
    String title;
    String description;
    String priority;
    String status;
    Date dueDate;
    @ManyToOne
    UserEntity user;
}
