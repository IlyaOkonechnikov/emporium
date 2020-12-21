package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class ServicesChangeLog {

    @Changeset(order = 1, id = "initServices", author = "afakherutdinov")
    public void initServices(MongoTemplate mongoTemplate) {
        Category category = new Category("Services");

        Category domestic = new Category("Domestic services", Set.of(
                new Category("Production of keys"),
                new Category("Clothing sewing and repair"),
                new Category("Watch repair"),
                new Category("Dry cleaning, washing"),
                new Category("Jewelry services")
        ));

        Category business = new Category("Business services", Set.of(
                new Category("Accounting, finance"),
                new Category("Consulting"),
                new Category("Typing and correcting text"),
                new Category("Transfer"),
                new Category("Legal services")
        ));
        Category delivery = new Category("Delivery, couriers");
        Category anHour = new Category("Master for an hour");
        Category art = new Category("Art");
        Category babysitters = new Category("Babysitters, nurses");
        Category transport = new Category("Transport, transportation", Set.of(
                new Category("Car service"),
                new Category("Rent a Car"),
                new Category("Commercial transportation"),
                new Category("Movers"),
                new Category("Legal services"),
                new Category("Moving"),
                new Category("Special equipment")
        ));
        Category other = new Category("Other");

        category.setSubCategories(Set.of(domestic, business, delivery, anHour, art, babysitters, transport, other));
        mongoTemplate.save(category);
    }
}
