package mk.ukim.finki.expense_tracker_with_analytics.service.domain.impl;

import mk.ukim.finki.expense_tracker_with_analytics.model.Transaction;
import mk.ukim.finki.expense_tracker_with_analytics.model.User;
import mk.ukim.finki.expense_tracker_with_analytics.model.exceptions.UserNotFoundException;
import mk.ukim.finki.expense_tracker_with_analytics.repository.CategoryRepository;
import mk.ukim.finki.expense_tracker_with_analytics.repository.TransactionRepository;
import mk.ukim.finki.expense_tracker_with_analytics.repository.UserRepository;
import mk.ukim.finki.expense_tracker_with_analytics.service.domain.TransactionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> update(Long id, Transaction transaction) {
        return transactionRepository.findById(id).map(existingTransaction -> {
            if (transaction.getCategory() != null && categoryRepository.findById(transaction.getCategory().getId()).isPresent()
                    && transaction.getType() != null && transaction.getAmount() != null && transaction.getDescription() != null
            ) {
                existingTransaction.setAmount(transaction.getAmount());
                existingTransaction.setType(transaction.getType());
                existingTransaction.setDescription(transaction.getDescription());
                existingTransaction.setCategory(transaction.getCategory());
                existingTransaction.setCreatedAt(LocalDateTime.now());
            }
            return transactionRepository.save(existingTransaction);
        });
    }

    @Override
    public Optional<Transaction> save(Transaction transaction) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
        Optional<Transaction> savedTransaction = Optional.empty();
        if (transaction.getCategory() != null && categoryRepository.findById(transaction.getCategory().getId()).isPresent()
                && transaction.getType() != null && transaction.getAmount() != null && transaction.getDescription() != null
        ) {
            savedTransaction = Optional.of(transactionRepository.save(new Transaction(
                    transaction.getAmount(),
                    transaction.getType(),
                    transaction.getDescription(),
                    transaction.getCategory(),
                    user,
                    LocalDateTime.now()
            )));

        }
        return savedTransaction;
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}
