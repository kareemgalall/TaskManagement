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

public class UserDto {
    Long id;
    String name;
    String email;
}
