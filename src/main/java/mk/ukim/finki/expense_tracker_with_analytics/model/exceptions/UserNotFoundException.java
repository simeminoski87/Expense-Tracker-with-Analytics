package mk.ukim.finki.expense_tracker_with_analytics.model.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User Not Found!!!");
    }
}
