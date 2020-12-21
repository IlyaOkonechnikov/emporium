package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class BusinessChangeLog {

    @Changeset(order = 1, id = "initBusinessAndEquipment", author = "afakherutdinov")
    public void initBusinessAndEquipment(MongoTemplate mongoTemplate) {
        Category category = new Category("Ready business and equipment");

        Category readyBusiness = new Category("Ready business", Set.of(
                new Category("Online store"),
                new Category("Catering"),
                new Category("Production"),
                new Category("Entertainment"),
                new Category("Agriculture"),
                new Category("Construction"),
                new Category("Services sector"),
                new Category("Trade"),
                new Category("Other")
        ));

        Category equipment = new Category("Business equipment", Set.of(
                new Category("For shop"),
                new Category("For office"),
                new Category("For restaurant"),
                new Category("For a beauty salon"),
                new Category("Industrial"),
                new Category("Other")
        ));

        category.setSubCategories(Set.of(readyBusiness, equipment));
        mongoTemplate.save(category);
    }
}
