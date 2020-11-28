package com.emporium.ad.changelog;


import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class AnimalsChangeLog {

    @Changeset(order = 1,id = "initAnimals",author = "afakherutdinov")
    public void initAnimals(MongoTemplate mongoTemplate) {
        Category category = new Category("Animals", Set.of(
                new Category(ObjectId.get(), "Dogs"),
                new Category(ObjectId.get(), "Cats"),
                new Category(ObjectId.get(), "Birds"),
                new Category(ObjectId.get(), "Aquarium"),
                new Category(ObjectId.get(), "Other animals", Set.of(
                        new Category(ObjectId.get(), "Amphibians"),
                        new Category(ObjectId.get(), "Rodents"),
                        new Category(ObjectId.get(), "Rabbits"),
                        new Category(ObjectId.get(), "Horses"),
                        new Category(ObjectId.get(), "Reptiles"),
                        new Category(ObjectId.get(), "Agricultural animals"),
                        new Category(ObjectId.get(), "Ferrets"),
                        new Category(ObjectId.get(), "Other")
                )),
                new Category(ObjectId.get(), "Goods for pets")
        ));

        mongoTemplate.save(category);
    }
}
