package banquemisr.challenge05.taskmanagement.repository;

import banquemisr.challenge05.taskmanagement.domain.model.HistoryEntity;
import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
    List<HistoryEntity> findByUser(UserEntity user);
}
