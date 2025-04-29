package th.ac.mahidol.ict.gemini_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import th.ac.mahidol.ict.gemini_backend.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByEmail(String email);
    boolean existsByUserId(String userId);
    boolean existsByEmail(String email);
}