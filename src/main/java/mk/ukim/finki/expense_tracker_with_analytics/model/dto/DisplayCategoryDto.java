package mk.ukim.finki.expense_tracker_with_analytics.model.dto;

import mk.ukim.finki.expense_tracker_with_analytics.model.Category;
import mk.ukim.finki.expense_tracker_with_analytics.model.User;
import mk.ukim.finki.expense_tracker_with_analytics.model.enumerations.Type;
import mk.ukim.finki.expense_tracker_with_analytics.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCategoryDto(Long id,String name) {
    private static CategoryRepository categoryRepository;

    public static DisplayCategoryDto from(Category category){
        return new DisplayCategoryDto(category.getId(), category.getName());
    }

    public List<DisplayCategoryDto> findAllByUser(User user) {
        return categoryRepository.findAllByUser(user)
                .stream()
                .map(DisplayCategoryDto::from)
                .toList();
    }
}
