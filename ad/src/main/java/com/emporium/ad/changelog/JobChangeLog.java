package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class JobChangeLog {

    @Changeset(order = 1,id = "initJob",author = "afakherutdinov")
    public void initJob(MongoTemplate mongoTemplate) {
        Category category = new Category("Job");
        Set<Category> categories = Set.of(
                new Category(ObjectId.get(),"IT, internet, telecom"),
                new Category(ObjectId.get(),"Car business"),
                new Category(ObjectId.get(),"Administrative work"),
                new Category(ObjectId.get(),"No experience, students"),
                new Category(ObjectId.get(),"Civil service, NGO"),
                new Category(ObjectId.get(),"Home staff"),
                new Category(ObjectId.get(),"Medicine, pharmaceuticals"),
                new Category(ObjectId.get(),"Sales"),
                new Category(ObjectId.get(),"Insurance"),
                new Category(ObjectId.get(),"Construction"),
                new Category(ObjectId.get(),"Fitness, beauty salons"),
                new Category(ObjectId.get(),"Jurisprudence"),
                new Category(ObjectId.get(),"Other")
        );
        Category vacancies = new Category(ObjectId.get(),"Vacancies",categories);
        Category resume = new Category(ObjectId.get(),"Resume",categories);

        category.setSubCategories(Set.of(vacancies,resume));
        mongoTemplate.save(category);
    }
}
