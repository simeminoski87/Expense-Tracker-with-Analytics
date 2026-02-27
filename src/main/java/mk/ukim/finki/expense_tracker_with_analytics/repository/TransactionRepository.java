package mk.ukim.finki.expense_tracker_with_analytics.repository;

import mk.ukim.finki.expense_tracker_with_analytics.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
