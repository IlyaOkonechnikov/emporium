import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.repository.CategoryRepository;
import com.emporium.ad.repository.CategoryRepositoryTestContextConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CategoryRepositoryTestContextConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CategoryRepositoryTest {

  @Autowired
  private CategoryRepository categoryRepository;
  private static Category category = new Category(1, "test1", null);

  @Test
  void findAllTest() {
    fillDB();
    List<Category> categories = categoryRepository.findAll();
    Assertions.assertEquals(7, categories.size());
    Assertions.assertTrue(categories.stream().map(Category::getId).collect(Collectors.toList()).containsAll(List.of(1, 2, 3, 4, 5, 6, 7)));
  }

  @Test
  void findMainCategoriesTest() {
    categoryRepository.save(new Category(1, null));
    categoryRepository.save(new Category(2, null));
    categoryRepository.save(new Category(3, null));
    categoryRepository.save(new Category(4, null));
    List<Category> mainCategories = categoryRepository.findMainCategories();
    Assertions.assertEquals(4, mainCategories.size());
    Assertions.assertTrue(mainCategories.stream().map(Category::getId).collect(Collectors.toList()).containsAll(List.of(1, 2, 3, 4)));
  }

  @Test
  void findByIdTest() {
    fillDB();
    Optional<Category> optionalCategory = categoryRepository.findById(5);
    Category category = optionalCategory.orElseThrow(() -> new EntityExistsException("Category not found"));
    Assertions.assertEquals("test5", category.getName());
  }

  @Test
  void nameUpdateTest() {
    fillDB();
    Category category = new Category(1);
    category.setName("new name1");
    categoryRepository.save(category);
    Optional<Category> optionalCategory = categoryRepository.findById(1);
    Category findCategory = optionalCategory.orElseThrow(() -> new EntityExistsException("Category not found"));
    Assertions.assertEquals("new name1", findCategory.getName());
  }

  @Test
  void parentCategoryUpdateTest() {
    fillDB();
    Category category = new Category(5);
    category.setParentCategory(new Category(3));
    categoryRepository.save(category);
    Optional<Category> optionalCategory = categoryRepository.findById(5);
    Category findCategory = optionalCategory.orElseThrow(() -> new EntityExistsException("Category not found"));
    Assertions.assertEquals(3, findCategory.getParentCategory().getId());
  }

  @Test
  void deleteTest() {
    fillDB();
    categoryRepository.deleteById(4);
    Optional<Category> categoryOptional = categoryRepository.findById(4);
    Assertions.assertTrue(categoryOptional.isEmpty());
    categoryRepository.deleteById(6);
    categoryOptional = categoryRepository.findById(6);
    Assertions.assertTrue(categoryOptional.isEmpty());
    categoryRepository.deleteById(7);
    categoryOptional = categoryRepository.findById(7);
    Assertions.assertTrue(categoryOptional.isEmpty());
  }

  void fillDB() {
    categoryRepository.save(category);
    categoryRepository.save(new Category(2, "test2", category));
    categoryRepository.save(new Category(3, "test3", category));
    categoryRepository.save(new Category(4, "test4", new Category(2)));
    categoryRepository.save(new Category(5, "test5", new Category(2)));
    categoryRepository.save(new Category(6, "test6", new Category(3)));
    categoryRepository.save(new Category(7, "test7", new Category(3)));
  }
}