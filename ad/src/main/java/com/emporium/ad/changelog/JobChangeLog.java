package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class JobChangeLog {

    @Changeset(order = 1, id = "initJob", author = "afakherutdinov")
    public void initJob(MongoTemplate mongoTemplate) {
        Category category = new Category("Job");
        Set<Category> categories = Set.of(
                new Category("IT, internet, telecom"),
                new Category("Car business"),
                new Category("Administrative work"),
                new Category("No experience, students"),
                new Category("Civil service, NGO"),
                new Category("Home staff"),
                new Category("Medicine, pharmaceuticals"),
                new Category("Sales"),
                new Category("Insurance"),
                new Category("Construction"),
                new Category("Fitness, beauty salons"),
                new Category("Jurisprudence"),
                new Category("Other")
        );
        Category vacancies = new Category("Vacancies", categories);
        Category resume = new Category("Resume", categories);

        category.setSubCategories(Set.of(vacancies, resume));
        mongoTemplate.save(category);
    }
}
