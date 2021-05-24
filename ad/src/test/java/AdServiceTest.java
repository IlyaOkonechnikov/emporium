import com.emporium.ad.model.jpa.Ad;
import com.emporium.ad.model.jpa.Category;
import com.emporium.lib.ad.AdDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdServiceTest extends BaseTest {

  private Category category;

  @BeforeEach
  void init(){
    this.category = categoryRepository.findAll().stream().findFirst().orElse(null);
    Assertions.assertNotNull(category);
  }

  @Test
  void adMustBeCreated(){
    //Given:
    AdDTO expected = mapper.toDTO(random.ad(true, category));
    //When:
    Long id = service.create(expected);
    //Then:
    Assertions.assertNotNull(id);
    Ad ad = repository.getOne(id);
    Assertions.assertNotNull(ad);
    AdDTO actual =  mapper.toDTO(ad);
    exchangeAdFields(expected, actual);
    Assertions.assertEquals(expected, mapper.toDTO(ad));
  }

  @Test
  void adMustBeObtainedById(){
    //Given:
    Ad ad = repository.save(random.ad(true, category));
    AdDTO expected = mapper.toDTO(ad);
    //When:
    AdDTO actual = mapper.toDTO(repository.getOne(ad.getId()));
    //Then:
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void adMustBeUpdated(){
    //Given:
    Ad ad = repository.save(random.ad(true, category));
    Assertions.assertNotNull(ad.getId());
    AdDTO expected = mapper.toDTO(random.ad(true, category));
    //When:
    service.update(ad.getId(), expected);
    //Then:
    AdDTO actual =  mapper.toDTO(repository.getOne(ad.getId()));
    exchangeAdFields(expected, actual);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void adMustBeDeleted(){
    //Given:
    Ad ad = repository.save(random.ad(true, category));
    Assertions.assertNotNull(ad.getId());
    //When:
    service.delete(ad.getId());
    //Then:
    Assertions.assertTrue(repository.findById(ad.getId()).isEmpty());
  }
}
