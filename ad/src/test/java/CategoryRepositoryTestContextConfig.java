import com.emporium.ad.repository.CategoryRepository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@AutoConfigureDataJpa
@EntityScan(basePackages = {"com.emporium.ad.model.jpa"})
@EnableJpaRepositories(basePackages = "com.emporium.ad.repository", includeFilters =
@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = CategoryRepository.class))
public class CategoryRepositoryTestContextConfig {
}