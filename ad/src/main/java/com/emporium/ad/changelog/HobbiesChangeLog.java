package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class HobbiesChangeLog {

    @Changeset(order = 1,id = "initHobbies",author = "afakherutdinov")
    public void initHobbies(MongoTemplate mongoTemplate){
        Category category = new Category("Hobbies and leisure");

        Category ticketsAndTravel = new Category(ObjectId.get(), "Tickets and travel", Set.of(
                new Category(ObjectId.get(),"Cards, coupons"),
                new Category(ObjectId.get(),"Concerts"),
                new Category(ObjectId.get(),"Travels"),
                new Category(ObjectId.get(),"Sport"),
                new Category(ObjectId.get(),"Theater, opera, ballet"),
                new Category(ObjectId.get(),"Circus, cinema"),
                new Category(ObjectId.get(),"Show, musical")
        ));

        Category bicycles = new Category(ObjectId.get(), "Bicycles", Set.of(
                new Category(ObjectId.get(),"Mountain"),
                new Category(ObjectId.get(),"Road"),
                new Category(ObjectId.get(),"BMX"),
                new Category(ObjectId.get(),"Baby"),
                new Category(ObjectId.get(),"Parts and accessories")
        ));

        Category books = new Category(ObjectId.get(), "Books and magazines", Set.of(
                new Category(ObjectId.get(),"Magazines, newspapers, brochures"),
                new Category(ObjectId.get(),"Books"),
                new Category(ObjectId.get(),"Educational literature")
        ));

        Category collecting = new Category(ObjectId.get(), "Collecting", Set.of(
                new Category(ObjectId.get(),"Banknotes"),
                new Category(ObjectId.get(),"Tickets"),
                new Category(ObjectId.get(),"Tokens, medals, badges"),
                new Category(ObjectId.get(),"Paintings"),
                new Category(ObjectId.get(),"Stamps"),
                new Category(ObjectId.get(),"Coins"),
                new Category(ObjectId.get(),"Postcards"),
                new Category(ObjectId.get(),"Labels, bottles, corks"),
                new Category(ObjectId.get(),"Other")
        ));

        Category musicalInstruments = new Category(ObjectId.get(), "Musical instruments", Set.of(
                new Category(ObjectId.get(),"Accordions, accordions, button accordions"),
                new Category(ObjectId.get(),"Guitars and other strings"),
                new Category(ObjectId.get(),"Wind instruments"),
                new Category(ObjectId.get(),"Pianos and other keyboards"),
                new Category(ObjectId.get(),"Violins and other bows"),
                new Category(ObjectId.get(),"Drums"),
                new Category(ObjectId.get(),"For studio and concerts"),
                new Category(ObjectId.get(),"Accessories")
        ));

        Category huntingAndFishing = new Category(ObjectId.get(), "Hunting and fishing");

        Category sportsAndRecreation = new Category(ObjectId.get(), "Sports and recreation", Set.of(
                new Category(ObjectId.get(),"Billiards and bowling"),
                new Category(ObjectId.get(),"Diving and water sports"),
                new Category(ObjectId.get(),"Martial arts"),
                new Category(ObjectId.get(),"Winter sports"),
                new Category(ObjectId.get(),"Violins and other bows"),
                new Category(ObjectId.get(),"Ball games"),
                new Category(ObjectId.get(),"Board games"),
                new Category(ObjectId.get(),"Paintball and airsoft"),
                new Category(ObjectId.get(),"Rollers and skateboarding"),
                new Category(ObjectId.get(),"Tennis, badminton, ping-pong"),
                new Category(ObjectId.get(),"Tourism"),
                new Category(ObjectId.get(),"Fitness and exercise equipment"),
                new Category(ObjectId.get(),"Sports nutrition"),
                new Category(ObjectId.get(),"Other")
        ));

        category.setSubCategories(Set.of(ticketsAndTravel, bicycles, books, collecting,
                musicalInstruments, huntingAndFishing, sportsAndRecreation));
        mongoTemplate.save(category);
    }
}
