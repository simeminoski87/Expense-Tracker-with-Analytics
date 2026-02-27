package mk.ukim.finki.expense_tracker_with_analytics.service.domain.impl;

import mk.ukim.finki.expense_tracker_with_analytics.model.Category;
import mk.ukim.finki.expense_tracker_with_analytics.model.User;
import mk.ukim.finki.expense_tracker_with_analytics.model.exceptions.UserNotFoundException;
import mk.ukim.finki.expense_tracker_with_analytics.repository.CategoryRepository;
import mk.ukim.finki.expense_tracker_with_analytics.repository.UserRepository;
import mk.ukim.finki.expense_tracker_with_analytics.service.domain.CategoryService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> create(Category category) {
        String username= SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        User user=userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
        Optional<Category> savedCategory=Optional.empty();
        if(category.getType()!=null && !categoryRepository.existsByName(category.getName())
                && !category.getName().isEmpty()){
            category.setUser(user);
            savedCategory = Optional.of(categoryRepository.save(category));
        }
        return savedCategory;
    }

    @Override
    public Optional<Category> update(Long id, Category category) {
        return categoryRepository.findById(id).map(existingCategory->{
            if(category.getName()!=null && !categoryRepository.existsByName(category.getName())){
                existingCategory.setName(category.getName());
            }
            if(category.getType()!=null){
                existingCategory.setType(category.getType());
            }
            return categoryRepository.save(existingCategory);
        });
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
