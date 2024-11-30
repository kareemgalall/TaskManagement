package banquemisr.challenge05.taskmanagement.repository;

import banquemisr.challenge05.taskmanagement.domain.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> , PagingAndSortingRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);

    Page<UserEntity> findByRole(String role, Pageable pageable);
}
