import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.repository.CategoryRepository;
import com.emporium.ad.repository.CategoryRepositoryTestContextConfig;

import org.junit.jupiter.api.Assertions;
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

import javax.persistence.EntityExistsException;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CategoryRepositoryTestContextConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CategoryRepositoryTest {

  @Autowired
  private CategoryRepository categoryRepository;

  @Test
  void findAllTest() {
    Category category = Category.builder().id(1).name("test1").parentCategory(null).build();
    categoryRepository.save(category);
    categoryRepository.save(Category.builder().id(2).name("test2").parentCategory(category).build());
    categoryRepository.save(Category.builder().id(3).name("test3").parentCategory(category).build());
    List<Category> categories = categoryRepository.findAll();
    Assertions.assertEquals(3, categories.size());
    Assertions.assertTrue(categories.stream().map(Category::getId).collect(Collectors.toList()).containsAll(List.of(1, 2, 3)));
  }

  @Test
  void findMainCategoriesTest() {
    categoryRepository.save(Category.builder().id(1).name("test1").parentCategory(null).build());
    categoryRepository.save(Category.builder().id(2).name("test2").parentCategory(null).build());
    categoryRepository.save(Category.builder().id(3).name("test3").parentCategory(null).build());
    categoryRepository.save(Category.builder().id(4).name("test4").parentCategory(Category.builder().id(2).build()).build());
    List<Category> mainCategories = categoryRepository.findMainCategories();
    Assertions.assertEquals(3, mainCategories.size());
    Assertions.assertTrue(mainCategories.stream().map(Category::getId).collect(Collectors.toList()).containsAll(List.of(1, 2, 3)));
  }

  @Test
  void findByIdTest() {
    categoryRepository.save(Category.builder().id(1).name("test1").parentCategory(null).build());
    categoryRepository.save(Category.builder().id(2).name("test2").parentCategory(null).build());
    categoryRepository.save(Category.builder().id(3).name("test3").parentCategory(null).build());
    Optional<Category> optionalCategory = categoryRepository.findById(2);
    Category category = optionalCategory.orElseThrow(() -> new EntityExistsException("Category not found"));
    Assertions.assertEquals("test2", category.getName());
  }

  @Test
  void nameUpdateTest() {
    categoryRepository.save(Category.builder().id(1).name("test1").parentCategory(null).build());
    Category category = Category.builder().id(1).build();
    category.setName("new name1");
    categoryRepository.save(category);
    Optional<Category> optionalCategory = categoryRepository.findById(1);
    Category findCategory = optionalCategory.orElseThrow(() -> new EntityExistsException("Category not found"));
    Assertions.assertEquals("new name1", findCategory.getName());
  }

  @Test
  void parentCategoryUpdateTest() {
    categoryRepository.save(Category.builder().id(1).name("test1").parentCategory(null).build());
    categoryRepository.save(Category.builder().id(2).name("test2").parentCategory(Category.builder().id(1).build()).build());
    categoryRepository.save(Category.builder().id(3).name("test3").parentCategory(null).build());
    Category category = Category.builder().id(2).build();
    category.setParentCategory(Category.builder().id(3).build());
    categoryRepository.save(category);
    Optional<Category> optionalCategory = categoryRepository.findById(2);
    Category findCategory = optionalCategory.orElseThrow(() -> new EntityExistsException("Category not found"));
    Assertions.assertEquals(3, findCategory.getParentCategory().getId());
  }

  @Test
  void deleteTest() {
    categoryRepository.save(Category.builder().id(1).name("test1").parentCategory(null).build());
    categoryRepository.save(Category.builder().id(2).name("test2").parentCategory(Category.builder().id(1).build()).build());
    categoryRepository.save(Category.builder().id(3).name("test3").parentCategory(null).build());
    categoryRepository.deleteById(3);
    Optional<Category> categoryOptional = categoryRepository.findById(3);
    Assertions.assertTrue(categoryOptional.isEmpty());
    categoryRepository.deleteById(1);
    categoryOptional = categoryRepository.findById(1);
    Assertions.assertTrue(categoryOptional.isEmpty());
    categoryOptional = categoryRepository.findById(2);
    Assertions.assertTrue(categoryOptional.isEmpty());
  }
}