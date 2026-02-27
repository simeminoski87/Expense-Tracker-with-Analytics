package mk.ukim.finki.expense_tracker_with_analytics.service.domain;

import mk.ukim.finki.expense_tracker_with_analytics.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
     Optional<Category> findById(Long id);
     Optional<Category> create(Category category);
     Optional<Category> update(Long id, Category category);
     void deleteById(Long id);
}
