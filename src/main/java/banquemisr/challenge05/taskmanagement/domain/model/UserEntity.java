package banquemisr.challenge05.taskmanagement.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue
    Long id;
    @NotEmpty(message = "name cannot be empty")
    String name;
    @Email(message = "email not valid")
    @Column(unique = true)
    @NotEmpty(message = "email cannot be empty")
    String email;
    @NotEmpty(message = "password cannot be empty")
    String password;
    String role;
}
