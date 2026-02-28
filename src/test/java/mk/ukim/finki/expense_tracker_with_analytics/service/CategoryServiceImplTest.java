package mk.ukim.finki.expense_tracker_with_analytics.service;

import mk.ukim.finki.expense_tracker_with_analytics.model.Category;
import mk.ukim.finki.expense_tracker_with_analytics.model.User;
import mk.ukim.finki.expense_tracker_with_analytics.model.enumerations.Type;
import mk.ukim.finki.expense_tracker_with_analytics.repository.CategoryRepository;
import mk.ukim.finki.expense_tracker_with_analytics.repository.UserRepository;
import mk.ukim.finki.expense_tracker_with_analytics.service.domain.impl.CategoryServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private User user;
    private Category category;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testuser");

        category = new Category("Food");
    }

    // ---------------- FIND ALL ----------------
    @Test
    @Order(1)
    void testFindAll() {
        when(categoryRepository.findAll()).thenReturn(List.of(category));

        List<Category> result = categoryService.findAll();

        assertEquals(1, result.size());
        verify(categoryRepository).findAll();
    }

    // ---------------- FIND BY ID ----------------
    @Test
    @Order(2)
    void testFindById_WhenExists() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Optional<Category> result = categoryService.findById(1L);

        assertTrue(result.isPresent());
        verify(categoryRepository).findById(1L);
    }

    @Test
    @Order(3)
    void testFindById_WhenNotExists() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Category> result = categoryService.findById(1L);

        assertFalse(result.isPresent());
    }

    // ---------------- CREATE ----------------
    @Test
    @Order(4)
    void testCreate_Success() {
        // Mock security context to return authenticated username
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testuser");

        // Mock user repository to return our user
        when(userRepository.findUserByUsername("testuser")).thenReturn(Optional.of(user));

        // Prepare the category to save
        Category newCategory = new Category("Food");

        // When saving, return the category with the user attached
        Category savedCategory = new Category("Food");
        savedCategory.setUser(user);
        when(categoryRepository.save(any(Category.class))).thenReturn(savedCategory);

        // Call the service
        Optional<Category> result = categoryService.create(newCategory);

        // Assertions
        assertTrue(result.isPresent(), "Category should be created successfully");
        assertEquals("Food", result.get().getName());
        assertEquals(user, result.get().getUser());

        verify(categoryRepository).save(any(Category.class));
    }



    // ---------------- UPDATE ----------------
    @Test
    @Order(5)
    void testUpdate_Success() {
        Category existing = new Category("Old");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(categoryRepository.save(any(Category.class))).thenReturn(existing);

        Category updatedData = new Category("New");

        Optional<Category> result = categoryService.update(1L, updatedData);

        assertTrue(result.isPresent());
        assertEquals("New", result.get().getName());
    }

    @Test
    @Order(6)
    void testUpdate_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Category> result = categoryService.update(1L, category);

        assertTrue(result.isEmpty());
    }

    // ---------------- DELETE ----------------
    @Test
    @Order(7)
    void testDeleteById() {
        categoryService.deleteById(1L);
        verify(categoryRepository).deleteById(1L);
    }
}