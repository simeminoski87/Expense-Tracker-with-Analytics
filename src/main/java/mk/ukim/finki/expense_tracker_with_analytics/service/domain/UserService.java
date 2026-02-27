package mk.ukim.finki.expense_tracker_with_analytics.service.domain;

import mk.ukim.finki.expense_tracker_with_analytics.model.User;
import mk.ukim.finki.expense_tracker_with_analytics.model.enumerations.Role;
import java.util.Optional;

public interface UserService {
     User register(String username, String name, String email, String password, Role role);
     User login(String username,String password);
     Optional<User> findByUsername(String username);
     User save(User user);
}
