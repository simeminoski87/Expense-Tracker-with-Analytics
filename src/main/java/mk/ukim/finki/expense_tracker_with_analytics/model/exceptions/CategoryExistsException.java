package mk.ukim.finki.expense_tracker_with_analytics.model.exceptions;

public class CategoryExistsException extends RuntimeException {
    public CategoryExistsException() {
        super("You have already created this Category!!!");
    }
}
