package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class PersonalBelongingsChangeLog {

    @Changeset(order = 1,id = "initPersonalBelongings",author = "afakherutdinov")
    public void initPersonalBelongings(MongoTemplate mongoTemplate) {
        Category category = new Category("Personal belongings");

        Category clothesShoesAccessories = new Category(ObjectId.get(),"Clothes, shoes, accessories");
        Category womenClothing = new Category(ObjectId.get(),"Women's clothing", Set.of(
                        new Category(ObjectId.get(),"Pants"),
                        new Category(ObjectId.get(),"Outwear"),
                        new Category(ObjectId.get(),"Jeans"),
                        new Category(ObjectId.get(),"Swimsuit"),
                        new Category(ObjectId.get(),"Underwear"),
                        new Category(ObjectId.get(),"Shoes"),
                        new Category(ObjectId.get(),"Blazers and suits"),
                        new Category(ObjectId.get(),"Wedding Dresses"),
                        new Category(ObjectId.get(),"Tops and T-Shirts"),
                        new Category(ObjectId.get(),"Knitwear"),
                        new Category(ObjectId.get(),"Other")
        ));

        Category menClothing = new Category(ObjectId.get(),"Men's clothing", Set.of(
                        new Category(ObjectId.get(),"Pants"),
                        new Category(ObjectId.get(),"Outwear"),
                        new Category(ObjectId.get(),"Jeans"),
                        new Category(ObjectId.get(),"Underwear"),
                        new Category(ObjectId.get(),"Shoes"),
                        new Category(ObjectId.get(),"Blazers and suits"),
                        new Category(ObjectId.get(),"Shirts"),
                        new Category(ObjectId.get(),"Knitwear and T-Shirts"),
                        new Category(ObjectId.get(),"Other")
        ));

        Category accessories = new Category(ObjectId.get(),"Accessories");

        clothesShoesAccessories.setSubCategories(Set.of(womenClothing,menClothing,accessories));

        Category childrensWear = new Category(ObjectId.get(),"Children's wear and shoes");

        Category forGirls = new Category(ObjectId.get(),"For girls", Set.of(
                new Category(ObjectId.get(),"Pants"),
                new Category(ObjectId.get(),"Outwear"),
                new Category(ObjectId.get(),"Jumpsuits and bodysuits"),
                new Category(ObjectId.get(),"Shoes"),
                new Category(ObjectId.get(),"Pajamas"),
                new Category(ObjectId.get(),"Dresses and skirts"),
                new Category(ObjectId.get(),"Knitwear"),
                new Category(ObjectId.get(),"Hats, mittens,scarves"),
                new Category(ObjectId.get(),"Other")
        ));

        Category forBoys = new Category(ObjectId.get(),"For boys", Set.of(
                new Category(ObjectId.get(),"Pants"),
                new Category(ObjectId.get(),"Outwear"),
                new Category(ObjectId.get(),"Jumpsuits and bodysuits"),
                new Category(ObjectId.get(),"Shoes"),
                new Category(ObjectId.get(),"Pajamas"),
                new Category(ObjectId.get(),"Knitwear"),
                new Category(ObjectId.get(),"Hats, mittens,scarves"),
                new Category(ObjectId.get(),"Other")
        ));

        childrensWear.setSubCategories(Set.of(forGirls,forBoys));

        Category forChildren = new Category(ObjectId.get(),"Goods for children and toys", Set.of(
                new Category(ObjectId.get(),"Сar seats"),
                new Category(ObjectId.get(),"Bicycles and kick scooters"),
                new Category(ObjectId.get(),"Children's furniture"),
                new Category(ObjectId.get(),"Strollers"),
                new Category(ObjectId.get(),"Toys"),
                new Category(ObjectId.get(),"Bedding"),
                new Category(ObjectId.get(),"Products for feeding"),
                new Category(ObjectId.get(),"Bathing goods"),
                new Category(ObjectId.get(),"Goods for school")
        ));

        Category watchesAndOther = new Category(ObjectId.get(),"Watches and jewelry", Set.of(
                new Category(ObjectId.get(),"Bijouterie"),
                new Category(ObjectId.get(),"Watches"),
                new Category(ObjectId.get(),"Jewelery")
        ));

        Category beauty = new Category(ObjectId.get(),"Beauty and health",Set.of(
                new Category(ObjectId.get(),"Cosmetics"),
                new Category(ObjectId.get(),"Perfumery"),
                new Category(ObjectId.get(),"Devices and accessories"),
                new Category(ObjectId.get(),"Hygiene products"),
                new Category(ObjectId.get(),"Hair products"),
                new Category(ObjectId.get(),"Medical devices"),
                new Category(ObjectId.get(),"Biologically active additives")
        ));

        category.setSubCategories(Set.of(clothesShoesAccessories,childrensWear,forChildren,watchesAndOther,beauty));
        mongoTemplate.save(category);
    }
}
