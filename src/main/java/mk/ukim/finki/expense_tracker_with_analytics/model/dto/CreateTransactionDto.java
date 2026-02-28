package mk.ukim.finki.expense_tracker_with_analytics.model.dto;

import mk.ukim.finki.expense_tracker_with_analytics.model.Category;
import mk.ukim.finki.expense_tracker_with_analytics.model.Transaction;
import mk.ukim.finki.expense_tracker_with_analytics.model.User;
import mk.ukim.finki.expense_tracker_with_analytics.model.enumerations.Type;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record CreateTransactionDto(BigDecimal amount, Type type, String description,
                                   Long categoryId, LocalDateTime createdAt) {

    public static CreateTransactionDto from(Transaction transaction){
        return new CreateTransactionDto(transaction.getAmount(),transaction.getType(),
                transaction.getDescription(),transaction.getCategory().getId(),
                transaction.getCreatedAt());
    }

    public static List<CreateTransactionDto> from(List<Transaction> transactions){
        return transactions.stream().map(CreateTransactionDto::from).collect(Collectors.toList());
    }

}
