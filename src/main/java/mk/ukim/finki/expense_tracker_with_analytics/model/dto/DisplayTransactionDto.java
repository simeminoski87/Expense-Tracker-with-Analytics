package mk.ukim.finki.expense_tracker_with_analytics.model.dto;

import mk.ukim.finki.expense_tracker_with_analytics.model.Category;
import mk.ukim.finki.expense_tracker_with_analytics.model.Transaction;
import mk.ukim.finki.expense_tracker_with_analytics.model.User;
import mk.ukim.finki.expense_tracker_with_analytics.model.enumerations.Type;
import mk.ukim.finki.expense_tracker_with_analytics.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record DisplayTransactionDto(Long id,BigDecimal amount, Type type, String description,
                                    Long categoryId, LocalDateTime createdAt) {
    private static TransactionRepository transactionRepository;

    public static DisplayTransactionDto from(Transaction transaction){
        return new DisplayTransactionDto(
                transaction.getId(),transaction.getAmount(),transaction.getType(),
                transaction.getDescription(),transaction.getCategory().getId(),
                transaction.getCreatedAt()
        );
    }

    public static List<DisplayTransactionDto> findAllByUser(User user){
        return transactionRepository.findAllByUser(user).stream()
                .map(DisplayTransactionDto::from).toList();
    }
}
