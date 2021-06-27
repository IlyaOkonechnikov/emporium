import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.emporium.ad.model.jpa.Ad;
import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.model.mapper.AdMapper;
import com.emporium.ad.repository.AdRepository;
import com.emporium.ad.repository.CategoryRepository;
import com.emporium.ad.service.AdService;
import com.emporium.ad.util.RandomData;
import com.emporium.lib.ad.AdDTO;
import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class AdServiceTest {

  @Autowired AdService service;

  @Autowired AdRepository adRepository;

  @Autowired AdMapper mapper;

  @Autowired CategoryRepository categoryRepository;

  @Autowired RandomData random;

  Category category;

  @BeforeEach
  void init() {
    category = categoryRepository.save(random.category("test"));
  }

  @Test
  void whenValidParams_thenAdShouldBeCreated() {
    // Given:
    AdDTO expected = mapper.toDTO(random.ad(true, category));
    // When:
    Long id = service.create(expected);
    // Then:
    assertNotNull(id);
    Ad ad = adRepository.getOne(id);
    assertNotNull(ad);
    AdDTO actual = mapper.toDTO(ad);
    expected.setId(actual.getId());
    assertEquals(expected, mapper.toDTO(ad));
  }

  @Test
  void whenValidAdId_thenAdMustBeObtained() {
    // Given:
    Ad ad = adRepository.save(random.ad(true, category));
    AdDTO expected = mapper.toDTO(ad);
    // When:
    AdDTO actual = mapper.toDTO(adRepository.getOne(ad.getId()));
    // Then:
    assertEquals(expected, actual);
  }

  @Test
  void whenValidData_thenAdMustBeUpdated() {
    // Given:
    Ad ad = adRepository.save(random.ad(true, category));
    assertNotNull(ad.getId());
    AdDTO expected = mapper.toDTO(random.ad(true, category));
    // When:
    service.update(ad.getId(), expected);
    // Then:
    AdDTO actual = mapper.toDTO(adRepository.getOne(ad.getId()));
    expected.setId(actual.getId());
    assertEquals(expected, actual);
  }

  @Test
  void whenValidAdId_thenAdMustBeDeleted() {
    // Given:
    Ad ad = adRepository.save(random.ad(true, category));
    assertNotNull(ad.getId());
    // When:
    service.delete(ad.getId());
    // Then:
    assertTrue(adRepository.findById(ad.getId()).isEmpty());
  }
}
