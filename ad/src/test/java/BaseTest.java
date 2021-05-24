import com.emporium.ad.model.mapper.AdMapper;
import com.emporium.ad.repository.AdRepository;
import com.emporium.ad.repository.CategoryRepository;
import com.emporium.ad.service.AdService;
import com.emporium.ad.util.RandomData;
import com.emporium.lib.ad.AdDTO;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import config.TestConfig;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public abstract class BaseTest {

  @Autowired
  AdService service;

  @Autowired
  AdRepository repository;

  @Autowired
  AdMapper mapper;

  @Autowired
  CategoryRepository categoryRepository;

  @Autowired
  RandomData random;

  protected void exchangeAdFields(AdDTO expected, AdDTO actual) {
    expected.setId(actual.getId());
    expected.setActive(true);
    expected.setCreateDate(actual.getCreateDate());
    expected.setUpdateDate(actual.getUpdateDate());
  }
}
