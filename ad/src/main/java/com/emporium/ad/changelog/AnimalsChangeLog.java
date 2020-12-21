package com.emporium.ad.changelog;


import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class AnimalsChangeLog {

    @Changeset(order = 1, id = "initAnimals", author = "afakherutdinov")
    public void initAnimals(MongoTemplate mongoTemplate) {
        Category category = new Category("Animals", Set.of(
                new Category("Dogs"),
                new Category("Cats"),
                new Category("Birds"),
                new Category("Aquarium"),
                new Category("Other animals", Set.of(
                        new Category("Amphibians"),
                        new Category("Rodents"),
                        new Category("Rabbits"),
                        new Category("Horses"),
                        new Category("Reptiles"),
                        new Category("Agricultural animals"),
                        new Category("Ferrets"),
                        new Category("Other")
                )),
                new Category("Goods for pets")
        ));

        mongoTemplate.save(category);
    }
}
