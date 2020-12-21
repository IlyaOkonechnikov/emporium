package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class HobbiesChangeLog {

    @Changeset(order = 1, id = "initHobbies", author = "afakherutdinov")
    public void initHobbies(MongoTemplate mongoTemplate) {
        Category category = new Category("Hobbies and leisure");

        Category ticketsAndTravel = new Category("Tickets and travel", Set.of(
                new Category("Cards, coupons"),
                new Category("Concerts"),
                new Category("Travels"),
                new Category("Sport"),
                new Category("Theater, opera, ballet"),
                new Category("Circus, cinema"),
                new Category("Show, musical")
        ));

        Category bicycles = new Category("Bicycles", Set.of(
                new Category("Mountain"),
                new Category("Road"),
                new Category("BMX"),
                new Category("Baby"),
                new Category("Parts and accessories")
        ));

        Category books = new Category("Books and magazines", Set.of(
                new Category("Magazines, newspapers, brochures"),
                new Category("Books"),
                new Category("Educational literature")
        ));

        Category collecting = new Category("Collecting", Set.of(
                new Category("Banknotes"),
                new Category("Tickets"),
                new Category("Tokens, medals, badges"),
                new Category("Paintings"),
                new Category("Stamps"),
                new Category("Coins"),
                new Category("Postcards"),
                new Category("Labels, bottles, corks"),
                new Category("Other")
        ));

        Category musicalInstruments = new Category("Musical instruments", Set.of(
                new Category("Accordions, accordions, button accordions"),
                new Category("Guitars and other strings"),
                new Category("Wind instruments"),
                new Category("Pianos and other keyboards"),
                new Category("Violins and other bows"),
                new Category("Drums"),
                new Category("For studio and concerts"),
                new Category("Accessories")
        ));

        Category huntingAndFishing = new Category("Hunting and fishing");

        Category sportsAndRecreation = new Category("Sports and recreation", Set.of(
                new Category("Billiards and bowling"),
                new Category("Diving and water sports"),
                new Category("Martial arts"),
                new Category("Winter sports"),
                new Category("Violins and other bows"),
                new Category("Ball games"),
                new Category("Board games"),
                new Category("Paintball and airsoft"),
                new Category("Rollers and skateboarding"),
                new Category("Tennis, badminton, ping-pong"),
                new Category("Tourism"),
                new Category("Fitness and exercise equipment"),
                new Category("Sports nutrition"),
                new Category("Other")
        ));

        category.setSubCategories(Set.of(ticketsAndTravel, bicycles, books, collecting,
                musicalInstruments, huntingAndFishing, sportsAndRecreation));
        mongoTemplate.save(category);
    }
}
