package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class ForHomeChangeLog {

    @Changeset(order = 1,id = "initForHomeAndSummerHome",author = "afakherutdinov")
    public void initForHomeAndSummerHome(MongoTemplate mongoTemplate){
        Category category = new Category("For home and summer home");

        Category appliances = new Category(ObjectId.get(),"Appliances");

        Category forHome = new Category(ObjectId.get(),"For home", Set.of(
                new Category(ObjectId.get(),"Vacuum cleaners"),
                new Category(ObjectId.get(),"Washing machines"),
                new Category(ObjectId.get(),"Irons"),
                new Category(ObjectId.get(),"Seeing machines")
        ));

        Category individualCare = new Category(ObjectId.get(),"For individual care", Set.of(
                new Category(ObjectId.get(),"Razors and trimmers"),
                new Category(ObjectId.get(),"Clippers"),
                new Category(ObjectId.get(),"Hair dryers and styling devices"),
                new Category(ObjectId.get(),"Epilators")
        ));

        Category forKitchen = new Category(ObjectId.get(),"For kitchen", Set.of(
                new Category(ObjectId.get(),"Hoods"),
                new Category(ObjectId.get(),"Small kitchen appliances"),
                new Category(ObjectId.get(),"Microwave ovens"),
                new Category(ObjectId.get(),"Slabs"),
                new Category(ObjectId.get(),"Dishwashers"),
                new Category(ObjectId.get(),"Refrigerators and freezers")
        ));

        Category climaticEquipment = new Category(ObjectId.get(),"Climatic equipment", Set.of(
                new Category(ObjectId.get(),"Fans"),
                new Category(ObjectId.get(),"Air conditioner"),
                new Category(ObjectId.get(),"Heaters"),
                new Category(ObjectId.get(),"Air purifiers"),
                new Category(ObjectId.get(),"Thermometers and weather stations")
        ));

        Category other = new Category(ObjectId.get(),"Other");

        appliances.setSubCategories(Set.of(forHome,individualCare, forKitchen,climaticEquipment,other));

        Category furnitureAndInterior = new Category(ObjectId.get(),"Furniture and interior", Set.of(
                new Category(ObjectId.get(),"Computer tables and chairs"),
                new Category(ObjectId.get(),"Kitchen sets"),
                new Category(ObjectId.get(),"Lighting"),
                new Category(ObjectId.get(),"Stands and pedestals"),
                new Category(ObjectId.get(),"Interior items, art"),
                new Category(ObjectId.get(),"Tables and chairs"),
                new Category(ObjectId.get(),"Wardrobes and dressers"),
                new Category(ObjectId.get(),"Beds, sofas and armchairs", Set.of(
                        new Category(ObjectId.get(),"Beds"),
                        new Category(ObjectId.get(),"Sofas"),
                        new Category(ObjectId.get(),"Armchair"),
                        new Category(ObjectId.get(),"Mattresses"),
                        new Category(ObjectId.get(),"Other")
                )),
                new Category(ObjectId.get(),"Текстиль и ковры",Set.of(
                        new Category(ObjectId.get(),"Carpets"),
                        new Category(ObjectId.get(),"Curtains, curtains, tulles"),
                        new Category(ObjectId.get(),"Fabrics and materials"),
                        new Category(ObjectId.get(),"Linens"),
                        new Category(ObjectId.get(),"Blankets and bedspreads"),
                        new Category(ObjectId.get(),"Cushions"),
                        new Category(ObjectId.get(),"Towels"),
                        new Category(ObjectId.get(),"Blankets"),
                        new Category(ObjectId.get(),"Tablecloths and napkins"),
                        new Category(ObjectId.get(),"Furniture Covers"),
                        new Category(ObjectId.get(),"Other")
                )),
                new Category(ObjectId.get(),"Other")
        ));

        Category dishes = new Category(ObjectId.get(),"Dishes and goods for the kitchen");

        Category tableSetting = new Category(ObjectId.get(),"Table setting",Set.of(
                new Category(ObjectId.get(),"Sets of dishes and dinner services"),
                new Category(ObjectId.get(),"Mugs, saucers, pairs"),
                new Category(ObjectId.get(),"Glasses and glasses"),
                new Category(ObjectId.get(),"Serving items"),
                new Category(ObjectId.get(),"Dishes"),
                new Category(ObjectId.get(),"Vases"),
                new Category(ObjectId.get(),"Cutlery"),
                new Category(ObjectId.get(),"Dishes and salad bowls"),
                new Category(ObjectId.get(),"Jugs and Decanters"),
                new Category(ObjectId.get(),"Shot glasses and glasses")
        ));

        Category cooking = new Category(ObjectId.get(),"Cooking",Set.of(
                new Category(ObjectId.get(),"Cauldrons, pans, saucepans"),
                new Category(ObjectId.get(),"Pots and ladles"),
                new Category(ObjectId.get(),"Dishes and dishes for baking and roasting"),
                new Category(ObjectId.get(),"Cooking accessories"),
                new Category(ObjectId.get(),"Grills, barbecues, picnic items"),
                new Category(ObjectId.get(),"Kitchen appliances")
        ));

        Category foodStorage = new Category(ObjectId.get(),"Food storage",Set.of(
                new Category(ObjectId.get(),"Jars, cans, bottles"),
                new Category(ObjectId.get(),"Containers and lunch boxes"),
                new Category(ObjectId.get(),"Other")
        ));

        Category forDrinks = new Category(ObjectId.get(),"Preparation of drinks",Set.of(
                new Category(ObjectId.get(),"Dishes for preparing drinks"),
                new Category(ObjectId.get(),"Moonshine stills"),
                new Category(ObjectId.get(),"Thermo cups, thermoses, flasks"),
                new Category(ObjectId.get(),"Other")
        ));

        Category houseHold = new Category(ObjectId.get(),"Household goods",Set.of(
                new Category(ObjectId.get(),"Household chemicals"),
                new Category(ObjectId.get(),"Cleaning and storage equipment")
        ));

        Category kitchenAccessories = new Category(ObjectId.get(),"Kitchen accessories",Set.of(
                new Category(ObjectId.get(),"Kitchen textiles"),
                new Category(ObjectId.get(),"Stands and Holders"),
                new Category(ObjectId.get(),"Other")
        ));

        Category otherInDishes = new Category(ObjectId.get(),"Other");

        dishes.setSubCategories(Set.of(tableSetting,cooking,foodStorage,
                forDrinks,houseHold,kitchenAccessories,otherInDishes));

        Category food = new Category(ObjectId.get(),"Food");

        Category repair = new Category(ObjectId.get(),"Repair and construction", Set.of(
                new Category(ObjectId.get(),"Doors"),
                new Category(ObjectId.get(),"Tools"),
                new Category(ObjectId.get(),"Fireplaces and heaters"),
                new Category(ObjectId.get(),"Windows and balconies"),
                new Category(ObjectId.get(),"Ceilings"),
                new Category(ObjectId.get(),"Garden equipment"),
                new Category(ObjectId.get(),"Plumbing and sauna"),
                new Category(ObjectId.get(),"Building materials",Set.of(
                        new Category(ObjectId.get(),"Insulation"),
                        new Category(ObjectId.get(),"Fasteners"),
                        new Category(ObjectId.get(),"Roof and gutter"),
                        new Category(ObjectId.get(),"Varnishes and paints"),
                        new Category(ObjectId.get(),"Sheet materials"),
                        new Category(ObjectId.get(),"Rolled metal"),
                        new Category(ObjectId.get(),"General construction materials"),
                        new Category(ObjectId.get(),"Finishing"),
                        new Category(ObjectId.get(),"Lumber"),
                        new Category(ObjectId.get(),"Building mixtures"),
                        new Category(ObjectId.get(),"Building walls"),
                        new Category(ObjectId.get(),"Electrician"),
                        new Category(ObjectId.get(),"Other")
                ))
        ));

        Category plants = new Category(ObjectId.get(),"Plants");

        category.setSubCategories(Set.of(appliances,furnitureAndInterior,dishes,food,repair,plants));
        mongoTemplate.save(category);
    }
}
