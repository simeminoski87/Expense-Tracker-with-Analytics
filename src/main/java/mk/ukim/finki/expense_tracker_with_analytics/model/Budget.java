package mk.ukim.finki.expense_tracker_with_analytics.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Month;
@Entity
@Data
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    BigDecimal amountLimit;
    Month month;
    int year;
    @ManyToOne
    Category category;
    @ManyToOne
    User user;

    public Budget(BigDecimal amountLimit, Month month, int year, Category category, User user) {
        this.amountLimit = amountLimit;
        this.month = month;
        this.year = year;
        this.category = category;
        this.user = user;
    }

    public Budget() {
    }
}
