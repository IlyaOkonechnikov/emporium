import com.emporium.ad.model.Category;
import com.emporium.ad.repository.CategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CategoryRepositoryTestContextConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CategoryRepositoryTest {

  @Autowired
  private CategoryRepository categoryRepository;
  private final Category category = Category.builder().id(1).name("test1").parentCategory(null).build();

  @BeforeEach
  void setUp() {
    categoryRepository.save(category);
  }

  @Test
  void findAllTest() {
    categoryRepository.save(Category.builder().id(2).name("test2").parentCategory(category).build());
    List<Category> categories = categoryRepository.findAll();
    assertEquals(2, categories.size());
    assertTrue(categories.stream().map(Category::getId).collect(Collectors.toList()).containsAll(List.of(1, 2)));
  }

  @Test
  void findMainCategoriesTest() {
    categoryRepository.save(Category.builder().id(2).name("test2").parentCategory(null).build());
    categoryRepository.save(Category.builder().id(3).name("test3").parentCategory(Category.builder().id(1).build()).build());
    List<Category> mainCategories = categoryRepository.findMainCategories();
    assertEquals(2, mainCategories.size());
    assertTrue(mainCategories.stream().map(Category::getId).collect(Collectors.toList()).containsAll(List.of(1, 2)));
  }

  @Test
  void findByIdTest() {
    Optional<Category> optionalCategory = categoryRepository.findById(1);
    assertTrue(optionalCategory.isPresent());
    assertEquals("test1", optionalCategory.get().getName());
  }

  @Test
  void nameUpdateTest() {
    category.setName("new name1");
    Category savedCategory = categoryRepository.save(category);
    assertEquals("new name1", savedCategory.getName());
  }

  @Test
  void parentCategoryUpdateTest() {
    Category childCategory = categoryRepository.save(Category.builder().id(2).name("test2").parentCategory(category).build());
    Category parentCategory = categoryRepository.save(Category.builder().id(3).name("test3").parentCategory(null).build());
    childCategory.setParentCategory(parentCategory);
    Category savedCategory = categoryRepository.save(childCategory);
    assertEquals(3, savedCategory.getParentCategory().getId());
  }

  @Test
  void deleteTest() {
    categoryRepository.deleteById(1);
    Optional<Category> categoryOptional = categoryRepository.findById(1);
    assertTrue(categoryOptional.isEmpty());
  }
}