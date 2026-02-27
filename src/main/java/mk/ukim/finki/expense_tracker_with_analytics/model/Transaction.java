package mk.ukim.finki.expense_tracker_with_analytics.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.expense_tracker_with_analytics.model.enumerations.Type;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    BigDecimal amount;
    Type type;
    String description;

    @ManyToOne
    Category category;
    @ManyToOne
    User user;
    LocalDateTime createdAt;

    public Transaction(BigDecimal amount, Type type, String description, Category category, User user, LocalDateTime createdAt) {
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.category = category;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Transaction() {
    }
}
