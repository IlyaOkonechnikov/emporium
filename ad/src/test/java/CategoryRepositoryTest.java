import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.repository.CategoryRepository;
import com.emporium.ad.repository.CategoryRepositoryTestContextConfig;

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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CategoryRepositoryTestContextConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CategoryRepositoryTest {

  @Autowired
  private CategoryRepository categoryRepository;
  private final Category category = Category.builder().id(1).name("test1").parentCategory(null).build();

  @Test
  void findAllTest() {
    categoryRepository.save(category);
    categoryRepository.save(Category.builder().id(2).name("test2").parentCategory(category).build());
    List<Category> categories = categoryRepository.findAll();
    assertEquals(2, categories.size());
    assertTrue(categories.stream().map(Category::getId).collect(Collectors.toList()).containsAll(List.of(1, 2)));
  }

  @Test
  void findMainCategoriesTest() {
    categoryRepository.save(category);
    categoryRepository.save(Category.builder().id(2).name("test2").parentCategory(null).build());
    categoryRepository.save(Category.builder().id(3).name("test3").parentCategory(Category.builder().id(1).build()).build());
    List<Category> mainCategories = categoryRepository.findMainCategories();
    assertEquals(2, mainCategories.size());
    assertTrue(mainCategories.stream().map(Category::getId).collect(Collectors.toList()).containsAll(List.of(1, 2)));
  }

  @Test
  void findByIdTest() {
    categoryRepository.save(category);
    Optional<Category> optionalCategory = categoryRepository.findById(1);
    assertNotNull(optionalCategory.get());
    assertEquals("test1", optionalCategory.get().getName());
  }

  @Test
  void nameUpdateTest() {
    categoryRepository.save(category);
    category.setName("new name1");
    categoryRepository.save(category);
    Optional<Category> optionalCategory = categoryRepository.findById(1);
    assertNotNull(optionalCategory.get());
    assertEquals("new name1", optionalCategory.get().getName());
  }

  @Test
  void parentCategoryUpdateTest() {
    categoryRepository.save(category);
    Category childCategory = categoryRepository.save(Category.builder().id(2).name("test2").parentCategory(category).build());
    Category parentCategory = categoryRepository.save(Category.builder().id(3).name("test3").parentCategory(null).build());
    childCategory.setParentCategory(parentCategory);
    categoryRepository.save(childCategory);
    Optional<Category> optionalCategory = categoryRepository.findById(2);
    assertNotNull(optionalCategory.get());
    assertEquals(3, optionalCategory.get().getParentCategory().getId());
  }

  @Test
  void deleteTest() {
    categoryRepository.save(category);
    categoryRepository.deleteById(1);
    Optional<Category> categoryOptional = categoryRepository.findById(1);
    assertTrue(categoryOptional.isEmpty());
  }
}