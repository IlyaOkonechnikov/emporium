package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class BusinessChangeLog {

    @Changeset(order = 1,id = "initBusinessAndEquipment",author = "afakherutdinov")
    public void initBusinessAndEquipment(MongoTemplate mongoTemplate) {
        Category category = new Category("Ready business and equipment");

        Category readyBusiness = new Category(ObjectId.get(), "Ready business", Set.of(
                new Category(ObjectId.get(), "Online store"),
                new Category(ObjectId.get(), "Catering"),
                new Category(ObjectId.get(), "Production"),
                new Category(ObjectId.get(), "Entertainment"),
                new Category(ObjectId.get(), "Agriculture"),
                new Category(ObjectId.get(), "Construction"),
                new Category(ObjectId.get(), "Services sector"),
                new Category(ObjectId.get(), "Trade"),
                new Category(ObjectId.get(), "Other")
        ));

        Category equipment = new Category(ObjectId.get(), "Business equipment", Set.of(
                new Category(ObjectId.get(), "For shop"),
                new Category(ObjectId.get(), "For office"),
                new Category(ObjectId.get(), "For restaurant"),
                new Category(ObjectId.get(), "For a beauty salon"),
                new Category(ObjectId.get(), "Industrial"),
                new Category(ObjectId.get(), "Other")
        ));

        category.setSubCategories(Set.of(readyBusiness,equipment));
        mongoTemplate.save(category);
    }
}
