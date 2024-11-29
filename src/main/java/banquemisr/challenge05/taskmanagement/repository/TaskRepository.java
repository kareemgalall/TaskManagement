package banquemisr.challenge05.taskmanagement.repository;

import banquemisr.challenge05.taskmanagement.domain.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
