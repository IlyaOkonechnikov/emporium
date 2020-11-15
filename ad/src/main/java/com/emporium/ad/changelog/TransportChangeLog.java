package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class TransportChangeLog {

    @Changeset(order = 1,id = "initTransport",author = "afakherutdinov")
    public void initTransport(MongoTemplate mongoTemplate){
        Category category = new Category("Transport");

        Category cars = new Category(ObjectId.get(),"Cars");
        Category motorcycles = new Category(ObjectId.get(),"Motorcycles and mototechnics",Set.of(
                new Category(ObjectId.get(),"Cross-country vehicles"),
                new Category(ObjectId.get(),"Carting"),
                new Category(ObjectId.get(),"ATV's"),
                new Category(ObjectId.get(),"Mopeds and scooters"),
                new Category(ObjectId.get(),"Snowmobiles"),
                new Category(ObjectId.get(),"Motorcycles", Set.of(
                        new Category(ObjectId.get(),"Road bikes"),
                        new Category(ObjectId.get(),"Sport bikes"),
                        new Category(ObjectId.get(),"Choppers")
                ))
        ));

        Category trucks = new Category(ObjectId.get(),"Trucks and special equipment", Set.of(
                new Category(ObjectId.get(),"Buses"),
                new Category(ObjectId.get(),"Motorhomes"),
                new Category(ObjectId.get(),"Truck cranes"),
                new Category(ObjectId.get(),"Bulldozers"),
                new Category(ObjectId.get(),"Trucks"),
                new Category(ObjectId.get(),"Utility technology"),
                new Category(ObjectId.get(),"Light transport"),
                new Category(ObjectId.get(),"Loaders"),
                new Category(ObjectId.get(),"Trailers"),
                new Category(ObjectId.get(),"Agricultural machinery"),
                new Category(ObjectId.get(),"Construction machinery"),
                new Category(ObjectId.get(),"Logging equipment"),
                new Category(ObjectId.get(),"Tractor units"),
                new Category(ObjectId.get(),"Excavators")
        ));

        Category waterTransport = new Category(ObjectId.get(),"Water transport", Set.of(
                new Category(ObjectId.get(),"Rowing boats"),
                new Category(ObjectId.get(),"Jet skis"),
                new Category(ObjectId.get(),"Boat and yachts"),
                new Category(ObjectId.get(),"Kayak and canoes"),
                new Category(ObjectId.get(),"Motor boats"),
                new Category(ObjectId.get(),"Inflatable boats")
        ));

        Category partsAndAccessories = new Category(ObjectId.get(),"Parts and accessories");

        Category parts = new Category(ObjectId.get(),"Spare parst",Set.of(
                new Category(ObjectId.get(),"For cars"),
                new Category(ObjectId.get(),"For motor vehicles"),
                new Category(ObjectId.get(),"For special equipment"),
                new Category(ObjectId.get(),"For water transport")
        ));
        Category accessories = new Category(ObjectId.get(),"Accessories");
        Category GPSNavigators = new Category(ObjectId.get(),"GPS navigators");
        Category autocosmetics = new Category(ObjectId.get(),"Autocosmetics and autochemistry");
        Category ave = new Category(ObjectId.get(),"Audio and video equipment");
        Category roofRacksAndTowbars = new Category(ObjectId.get(),"Roof racks and towbars");
        Category tools = new Category(ObjectId.get(),"Tools");
        Category trailers = new Category(ObjectId.get(),"Trailers");
        Category antiTheft = new Category(ObjectId.get(),"Anti-theft devices", Set.of(
                new Category(ObjectId.get(),"Car alarm"),
                new Category(ObjectId.get(),"Immobilizers"),
                new Category(ObjectId.get(),"Mechanical interlocks"),
                new Category(ObjectId.get(),"Satellite systems")

        ));
        Category tuning = new Category(ObjectId.get(),"Tuning");
        Category wheels = new Category(ObjectId.get(),"Tires, disks and wheels", Set.of(
                new Category(ObjectId.get(),"Tires"),
                new Category(ObjectId.get(),"Motorcycle tires"),
                new Category(ObjectId.get(),"Discs"),
                new Category(ObjectId.get(),"Wheels"),
                new Category(ObjectId.get(),"Caps")
        ));
        Category equipments = new Category(ObjectId.get(),"Equipment");

        partsAndAccessories.setSubCategories(Set.of(
                parts,accessories,GPSNavigators,autocosmetics,ave,
                roofRacksAndTowbars,tools,trailers,antiTheft,tuning,
                wheels,equipments
        ));

        category.setSubCategories(Set.of(cars,motorcycles,trucks,waterTransport,parts));
        mongoTemplate.save(category);
    }
}
