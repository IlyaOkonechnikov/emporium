package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class RealEstateChangeLog {

    @Changeset(order = 1,id = "initRealEstate",author = "afakherutdinov")
    public void initRealEstate(MongoTemplate mongoTemplate) {
        Category category = new Category("Real Estate", Set.of(
                new Category(ObjectId.get(),"All apartments"),
                new Category(ObjectId.get(),"Apartments in new buildings"),
                new Category(ObjectId.get(),"Apartments for rent"),
                new Category(ObjectId.get(),"Daily rate apartments"),
                new Category(ObjectId.get(),"Houses, summer cottages, cottages"),
                new Category(ObjectId.get(),"Rooms"),
                new Category(ObjectId.get(),"Commercial property"),
                new Category(ObjectId.get(),"Land"),
                new Category(ObjectId.get(),"Garages and parking spaces"),
                new Category(ObjectId.get(),"Real estate abroad"),
                new Category(ObjectId.get(),"Buyers of apartments"),
                new Category(ObjectId.get(),"Apartment tenants")
        ));

        mongoTemplate.save(category);
    }
}
