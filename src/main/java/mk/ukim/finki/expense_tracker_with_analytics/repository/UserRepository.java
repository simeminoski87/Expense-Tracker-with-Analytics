package mk.ukim.finki.expense_tracker_with_analytics.repository;

import mk.ukim.finki.expense_tracker_with_analytics.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);
}
