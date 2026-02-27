package mk.ukim.finki.expense_tracker_with_analytics.service.domain;
import mk.ukim.finki.expense_tracker_with_analytics.model.Transaction;
import java.util.List;
import java.util.Optional;

public interface TransactionService {
     List<Transaction> findAll();
     Optional<Transaction> update(Long id,Transaction transaction);
     Optional<Transaction> save(Transaction transaction);
     void deleteById(Long id);
}
