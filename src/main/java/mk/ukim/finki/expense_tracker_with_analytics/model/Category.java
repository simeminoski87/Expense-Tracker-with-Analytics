package mk.ukim.finki.expense_tracker_with_analytics.model;
import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.expense_tracker_with_analytics.model.enumerations.Type;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Type type;
    @ManyToOne
    User user;


    public Category(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Category() {
    }
}
