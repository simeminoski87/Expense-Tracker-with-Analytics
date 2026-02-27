package mk.ukim.finki.expense_tracker_with_analytics.model.exceptions;

public class UsernameNotAvailableException extends RuntimeException {
    public UsernameNotAvailableException() {
        super("Username is Already Taken");
    }
}
