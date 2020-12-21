package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class TransportChangeLog {

    @Changeset(order = 1, id = "initTransport", author = "afakherutdinov")
    public void initTransport(MongoTemplate mongoTemplate) {
        Category category = new Category("Transport");

        Category cars = new Category("Cars");
        Category motorcycles = new Category("Motorcycles and mototechnics", Set.of(
                new Category("Cross-country vehicles"),
                new Category("Carting"),
                new Category("ATV's"),
                new Category("Mopeds and scooters"),
                new Category("Snowmobiles"),
                new Category("Motorcycles", Set.of(
                        new Category("Road bikes"),
                        new Category("Sport bikes"),
                        new Category("Choppers")
                ))
        ));

        Category trucks = new Category("Trucks and special equipment", Set.of(
                new Category("Buses"),
                new Category("Motorhomes"),
                new Category("Truck cranes"),
                new Category("Bulldozers"),
                new Category("Trucks"),
                new Category("Utility technology"),
                new Category("Light transport"),
                new Category("Loaders"),
                new Category("Trailers"),
                new Category("Agricultural machinery"),
                new Category("Construction machinery"),
                new Category("Logging equipment"),
                new Category("Tractor units"),
                new Category("Excavators")
        ));

        Category waterTransport = new Category("Water transport", Set.of(
                new Category("Rowing boats"),
                new Category("Jet skis"),
                new Category("Boat and yachts"),
                new Category("Kayak and canoes"),
                new Category("Motor boats"),
                new Category("Inflatable boats")
        ));

        Category partsAndAccessories = new Category("Parts and accessories");

        Category parts = new Category("Spare parst", Set.of(
                new Category("For cars"),
                new Category("For motor vehicles"),
                new Category("For special equipment"),
                new Category("For water transport")
        ));
        Category accessories = new Category("Accessories");
        Category GPSNavigators = new Category("GPS navigators");
        Category autocosmetics = new Category("Autocosmetics and autochemistry");
        Category ave = new Category("Audio and video equipment");
        Category roofRacksAndTowbars = new Category("Roof racks and towbars");
        Category tools = new Category("Tools");
        Category trailers = new Category("Trailers");
        Category antiTheft = new Category("Anti-theft devices", Set.of(
                new Category("Car alarm"),
                new Category("Immobilizers"),
                new Category("Mechanical interlocks"),
                new Category("Satellite systems")

        ));
        Category tuning = new Category("Tuning");
        Category wheels = new Category("Tires, disks and wheels", Set.of(
                new Category("Tires"),
                new Category("Motorcycle tires"),
                new Category("Discs"),
                new Category("Wheels"),
                new Category("Caps")
        ));
        Category equipments = new Category("Equipment");

        partsAndAccessories.setSubCategories(Set.of(
                parts, accessories, GPSNavigators, autocosmetics, ave,
                roofRacksAndTowbars, tools, trailers, antiTheft, tuning,
                wheels, equipments
        ));

        category.setSubCategories(Set.of(cars, motorcycles, trucks, waterTransport, parts));
        mongoTemplate.save(category);
    }
}
