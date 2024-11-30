package banquemisr.challenge05.taskmanagement.repository;

import banquemisr.challenge05.taskmanagement.domain.model.TaskEntity;
import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long>, PagingAndSortingRepository<TaskEntity, Long> {
    public Page<TaskEntity> findByUser(UserEntity user, Pageable pageable);
    @Query("SELECT t FROM TaskEntity t WHERE t.user.id = :userId AND " +
            "(LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%')) OR " +
            "LOWER(t.description) LIKE LOWER(CONCAT('%', :description, '%')) OR " +
            "t.status = :status OR " +
            "t.dueDate = :dueDate)")
    List<TaskEntity> searchTasks(
            @Param("userId") Long userId,
            @Param("title") String title,
            @Param("description") String description,
            @Param("status") String status,
            @Param("dueDate") Date dueDate
    );
}
