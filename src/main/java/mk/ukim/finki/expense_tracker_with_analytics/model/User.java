package mk.ukim.finki.expense_tracker_with_analytics.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.expense_tracker_with_analytics.model.enumerations.Role;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "finance_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String name;
    String email;
    String password;
    Role role;
    LocalDateTime createdAt;

    public User(String username,String name, String email, String password, Role role, LocalDateTime createdAt) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    public User() {
    }
}
