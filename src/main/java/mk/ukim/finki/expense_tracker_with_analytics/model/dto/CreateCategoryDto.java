package mk.ukim.finki.expense_tracker_with_analytics.model.dto;

import mk.ukim.finki.expense_tracker_with_analytics.model.Category;
import mk.ukim.finki.expense_tracker_with_analytics.model.enumerations.Type;

public record CreateCategoryDto(String name) {
    public CreateCategoryDto from(Category category){
        return new CreateCategoryDto(
                category.getName()
        );
    }
    public Category toCategory(){
        return new Category(name);
    }
}
