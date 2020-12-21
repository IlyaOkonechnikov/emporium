package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class ForHomeChangeLog {

    @Changeset(order = 1, id = "initForHomeAndSummerHome", author = "afakherutdinov")
    public void initForHomeAndSummerHome(MongoTemplate mongoTemplate) {
        Category category = new Category("For home and summer home");

        Category appliances = new Category("Appliances");

        Category forHome = new Category("For home", Set.of(
                new Category("Vacuum cleaners"),
                new Category("Washing machines"),
                new Category("Irons"),
                new Category("Seeing machines")
        ));

        Category individualCare = new Category("For individual care", Set.of(
                new Category("Razors and trimmers"),
                new Category("Clippers"),
                new Category("Hair dryers and styling devices"),
                new Category("Epilators")
        ));

        Category forKitchen = new Category("For kitchen", Set.of(
                new Category("Hoods"),
                new Category("Small kitchen appliances"),
                new Category("Microwave ovens"),
                new Category("Slabs"),
                new Category("Dishwashers"),
                new Category("Refrigerators and freezers")
        ));

        Category climaticEquipment = new Category("Climatic equipment", Set.of(
                new Category("Fans"),
                new Category("Air conditioner"),
                new Category("Heaters"),
                new Category("Air purifiers"),
                new Category("Thermometers and weather stations")
        ));

        Category other = new Category("Other");

        appliances.setSubCategories(Set.of(forHome, individualCare, forKitchen, climaticEquipment, other));

        Category furnitureAndInterior = new Category("Furniture and interior", Set.of(
                new Category("Computer tables and chairs"),
                new Category("Kitchen sets"),
                new Category("Lighting"),
                new Category("Stands and pedestals"),
                new Category("Interior items, art"),
                new Category("Tables and chairs"),
                new Category("Wardrobes and dressers"),
                new Category("Beds, sofas and armchairs", Set.of(
                        new Category("Beds"),
                        new Category("Sofas"),
                        new Category("Armchair"),
                        new Category("Mattresses"),
                        new Category("Other")
                )),
                new Category("Текстиль и ковры", Set.of(
                        new Category("Carpets"),
                        new Category("Curtains, curtains, tulles"),
                        new Category("Fabrics and materials"),
                        new Category("Linens"),
                        new Category("Blankets and bedspreads"),
                        new Category("Cushions"),
                        new Category("Towels"),
                        new Category("Blankets"),
                        new Category("Tablecloths and napkins"),
                        new Category("Furniture Covers"),
                        new Category("Other")
                )),
                new Category("Other")
        ));

        Category dishes = new Category("Dishes and goods for the kitchen");

        Category tableSetting = new Category("Table setting", Set.of(
                new Category("Sets of dishes and dinner services"),
                new Category("Mugs, saucers, pairs"),
                new Category("Glasses and glasses"),
                new Category("Serving items"),
                new Category("Dishes"),
                new Category("Vases"),
                new Category("Cutlery"),
                new Category("Dishes and salad bowls"),
                new Category("Jugs and Decanters"),
                new Category("Shot glasses and glasses")
        ));

        Category cooking = new Category("Cooking", Set.of(
                new Category("Cauldrons, pans, saucepans"),
                new Category("Pots and ladles"),
                new Category("Dishes and dishes for baking and roasting"),
                new Category("Cooking accessories"),
                new Category("Grills, barbecues, picnic items"),
                new Category("Kitchen appliances")
        ));

        Category foodStorage = new Category("Food storage", Set.of(
                new Category("Jars, cans, bottles"),
                new Category("Containers and lunch boxes"),
                new Category("Other")
        ));

        Category forDrinks = new Category("Preparation of drinks", Set.of(
                new Category("Dishes for preparing drinks"),
                new Category("Moonshine stills"),
                new Category("Thermo cups, thermoses, flasks"),
                new Category("Other")
        ));

        Category houseHold = new Category("Household goods", Set.of(
                new Category("Household chemicals"),
                new Category("Cleaning and storage equipment")
        ));

        Category kitchenAccessories = new Category("Kitchen accessories", Set.of(
                new Category("Kitchen textiles"),
                new Category("Stands and Holders"),
                new Category("Other")
        ));

        Category otherInDishes = new Category("Other");

        dishes.setSubCategories(Set.of(tableSetting, cooking, foodStorage,
                forDrinks, houseHold, kitchenAccessories, otherInDishes));

        Category food = new Category("Food");

        Category repair = new Category("Repair and construction", Set.of(
                new Category("Doors"),
                new Category("Tools"),
                new Category("Fireplaces and heaters"),
                new Category("Windows and balconies"),
                new Category("Ceilings"),
                new Category("Garden equipment"),
                new Category("Plumbing and sauna"),
                new Category("Building materials", Set.of(
                        new Category("Insulation"),
                        new Category("Fasteners"),
                        new Category("Roof and gutter"),
                        new Category("Varnishes and paints"),
                        new Category("Sheet materials"),
                        new Category("Rolled metal"),
                        new Category("General construction materials"),
                        new Category("Finishing"),
                        new Category("Lumber"),
                        new Category("Building mixtures"),
                        new Category("Building walls"),
                        new Category("Electrician"),
                        new Category("Other")
                ))
        ));

        Category plants = new Category("Plants");

        category.setSubCategories(Set.of(appliances, furnitureAndInterior, dishes, food, repair, plants));
        mongoTemplate.save(category);
    }
}
