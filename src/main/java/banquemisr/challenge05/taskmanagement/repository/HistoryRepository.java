package banquemisr.challenge05.taskmanagement.repository;

import banquemisr.challenge05.taskmanagement.domain.model.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
}
