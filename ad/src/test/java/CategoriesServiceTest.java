import com.emporium.ad.AdApplication;
import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.service.CategoryService;
import com.emporium.lib.category.CategoryDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AdApplication.class})
class CategoriesServiceTest {

  @Autowired
  private CategoryService categoryService;

  @Test
  void findAllTest() {
    List<Category> mainCategories = categoryService.findAll();
    Assertions.assertEquals(505, mainCategories.size());
  }

  @Test
  void findMainCategoriesTest() {
    List<Category> mainCategories = categoryService.findMainCategories();
    Assertions.assertEquals(10, mainCategories.size());
    List<String> checkList = List.of("Consumer Electronics", "For home and summer home", "Job", "Hobbies and leisure",
        "Transport", "Ready business and equipment", "Personal belongings", "Real estate", "Services", "Animals");
    Assertions.assertTrue(mainCategories.stream().map(Category::getName).collect(Collectors.toSet()).containsAll(checkList));
  }

  @Test
  void findByIdTest() {
    Category category = categoryService.findById(474);
    Assertions.assertEquals("Water transport", category.getName());
    Assertions.assertThrows(ResponseStatusException.class, () -> categoryService.findById(-1));
  }

  @Test
  void createTest() {
    String id = categoryService.create(new CategoryDTO("test", 2));
    Assertions.assertNotNull(id);
    categoryService.delete(Integer.parseInt(id));
  }

  @Test
  void updateTest() {
    int id = Integer.parseInt(categoryService.create(new CategoryDTO("test", 2)));
    Category category = categoryService.findById(id);
    category.setName("new name");
    category.setParentCategory(categoryService.findById(10));
    categoryService.update(category);
    Category check = categoryService.findById(id);
    Assertions.assertEquals("new name", check.getName());
    Assertions.assertEquals(category.getParentCategory(), check.getParentCategory());
    categoryService.delete(id);
  }

  @Test
  void deleteTest() {
    int id = Integer.parseInt(categoryService.create(new CategoryDTO("test", 2)));
    categoryService.delete(id);
    Assertions.assertThrows(ResponseStatusException.class, () -> categoryService.findById(id));
  }
}