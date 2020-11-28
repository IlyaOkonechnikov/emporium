package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class ServicesChangeLog {

    @Changeset(order = 1,id = "initServices",author = "afakherutdinov")
    public void initServices(MongoTemplate mongoTemplate) {
        Category category = new Category("Services");

        Category domestic = new Category(ObjectId.get(), "Domestic services", Set.of(
                new Category(ObjectId.get(),"Production of keys"),
                new Category(ObjectId.get(),"Clothing sewing and repair"),
                new Category(ObjectId.get(),"Watch repair"),
                new Category(ObjectId.get(),"Dry cleaning, washing"),
                new Category(ObjectId.get(),"Jewelry services")
        ));

        Category business = new Category(ObjectId.get(), "Business services", Set.of(
                new Category(ObjectId.get(),"Accounting, finance"),
                new Category(ObjectId.get(),"Consulting"),
                new Category(ObjectId.get(),"Typing and correcting text"),
                new Category(ObjectId.get(),"Transfer"),
                new Category(ObjectId.get(),"Legal services")
        ));
        Category delivery = new Category(ObjectId.get(), "Delivery, couriers");
        Category anHour = new Category(ObjectId.get(), "Master for an hour");
        Category art = new Category(ObjectId.get(), "Art");
        Category babysitters = new Category(ObjectId.get(), "Babysitters, nurses");
        Category transport = new Category(ObjectId.get(), "Transport, transportation", Set.of(
                new Category(ObjectId.get(),"Car service"),
                new Category(ObjectId.get(),"Rent a Car"),
                new Category(ObjectId.get(),"Commercial transportation"),
                new Category(ObjectId.get(),"Movers"),
                new Category(ObjectId.get(),"Legal services"),
                new Category(ObjectId.get(),"Moving"),
                new Category(ObjectId.get(),"Special equipment")
        ));
        Category other = new Category(ObjectId.get(), "Other");

        category.setSubCategories(Set.of(domestic, business, delivery, anHour, art, babysitters,transport,other));
        mongoTemplate.save(category);
    }
}
