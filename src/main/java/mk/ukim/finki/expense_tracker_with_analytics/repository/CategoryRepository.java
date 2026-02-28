package mk.ukim.finki.expense_tracker_with_analytics.repository;

import mk.ukim.finki.expense_tracker_with_analytics.model.Category;
import mk.ukim.finki.expense_tracker_with_analytics.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByNameAndUser(String name, User user);

    boolean existsByName(String name);

    List<Category> findAllByUser(User user);

}
