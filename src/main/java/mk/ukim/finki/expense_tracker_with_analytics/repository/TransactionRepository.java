package mk.ukim.finki.expense_tracker_with_analytics.repository;

import mk.ukim.finki.expense_tracker_with_analytics.model.Transaction;
import mk.ukim.finki.expense_tracker_with_analytics.model.User;
import mk.ukim.finki.expense_tracker_with_analytics.model.dto.DisplayTransactionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByUser(User user);
}
