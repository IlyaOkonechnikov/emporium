package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class PersonalBelongingsChangeLog {

    @Changeset(order = 1, id = "initPersonalBelongings", author = "afakherutdinov")
    public void initPersonalBelongings(MongoTemplate mongoTemplate) {
        Category category = new Category("Personal belongings");

        Category clothesShoesAccessories = new Category("Clothes, shoes, accessories");
        Category womenClothing = new Category("Women's clothing", Set.of(
                new Category("Pants"),
                new Category("Outwear"),
                new Category("Jeans"),
                new Category("Swimsuit"),
                new Category("Underwear"),
                new Category("Shoes"),
                new Category("Blazers and suits"),
                new Category("Wedding Dresses"),
                new Category("Tops and T-Shirts"),
                new Category("Knitwear"),
                new Category("Other")
        ));

        Category menClothing = new Category("Men's clothing", Set.of(
                new Category("Pants"),
                new Category("Outwear"),
                new Category("Jeans"),
                new Category("Underwear"),
                new Category("Shoes"),
                new Category("Blazers and suits"),
                new Category("Shirts"),
                new Category("Knitwear and T-Shirts"),
                new Category("Other")
        ));

        Category accessories = new Category("Accessories");

        clothesShoesAccessories.setSubCategories(Set.of(womenClothing, menClothing, accessories));

        Category childrensWear = new Category("Children's wear and shoes");

        Category forGirls = new Category("For girls", Set.of(
                new Category("Pants"),
                new Category("Outwear"),
                new Category("Jumpsuits and bodysuits"),
                new Category("Shoes"),
                new Category("Pajamas"),
                new Category("Dresses and skirts"),
                new Category("Knitwear"),
                new Category("Hats, mittens,scarves"),
                new Category("Other")
        ));

        Category forBoys = new Category("For boys", Set.of(
                new Category("Pants"),
                new Category("Outwear"),
                new Category("Jumpsuits and bodysuits"),
                new Category("Shoes"),
                new Category("Pajamas"),
                new Category("Knitwear"),
                new Category("Hats, mittens,scarves"),
                new Category("Other")
        ));

        childrensWear.setSubCategories(Set.of(forGirls, forBoys));

        Category forChildren = new Category("Goods for children and toys", Set.of(
                new Category("Сar seats"),
                new Category("Bicycles and kick scooters"),
                new Category("Children's furniture"),
                new Category("Strollers"),
                new Category("Toys"),
                new Category("Bedding"),
                new Category("Products for feeding"),
                new Category("Bathing goods"),
                new Category("Goods for school")
        ));

        Category watchesAndOther = new Category("Watches and jewelry", Set.of(
                new Category("Bijouterie"),
                new Category("Watches"),
                new Category("Jewelery")
        ));

        Category beauty = new Category("Beauty and health", Set.of(
                new Category("Cosmetics"),
                new Category("Perfumery"),
                new Category("Devices and accessories"),
                new Category("Hygiene products"),
                new Category("Hair products"),
                new Category("Medical devices"),
                new Category("Biologically active additives")
        ));

        category.setSubCategories(Set.of(clothesShoesAccessories, childrensWear, forChildren, watchesAndOther, beauty));
        mongoTemplate.save(category);
    }
}
