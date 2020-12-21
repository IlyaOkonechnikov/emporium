package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class RealEstateChangeLog {

    @Changeset(order = 1, id = "initRealEstate", author = "afakherutdinov")
    public void initRealEstate(MongoTemplate mongoTemplate) {
        Category category = new Category("Real Estate", Set.of(
                new Category("All apartments"),
                new Category("Apartments in new buildings"),
                new Category("Apartments for rent"),
                new Category("Daily rate apartments"),
                new Category("Houses, summer cottages, cottages"),
                new Category("Rooms"),
                new Category("Commercial property"),
                new Category("Land"),
                new Category("Garages and parking spaces"),
                new Category("Real estate abroad"),
                new Category("Buyers of apartments"),
                new Category("Apartment tenants")
        ));

        mongoTemplate.save(category);
    }
}
