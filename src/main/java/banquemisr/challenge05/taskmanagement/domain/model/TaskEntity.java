package banquemisr.challenge05.taskmanagement.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @GeneratedValue
    Long id;
    @NotEmpty(message = "title cannot be empty")
    String title;
    @NotEmpty(message="description cannot be empty")
    String description;
    @Pattern(regexp = "LOW|MEDIUM|HIGH", message = "Status must be one of: LOW, MEDIUM, or HIGH")
    String priority;
    @NotNull(message = "status cannot be null")
    @Pattern(regexp = "NEW|IN_PROGRESS|COMPLETED", message = "Status must be one of: NEW, IN_PROGRESS, or COMPLETED")
    String status;
    @Temporal(TemporalType.DATE)
    Date dueDate;
    @ManyToOne
    UserEntity user;
}
