package banquemisr.challenge05.taskmanagement.repository;

import banquemisr.challenge05.taskmanagement.domain.model.TaskEntity;
import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long>, PagingAndSortingRepository<TaskEntity, Long> {
    public Page<TaskEntity> findByUser(UserEntity user, Pageable pageable);
}
