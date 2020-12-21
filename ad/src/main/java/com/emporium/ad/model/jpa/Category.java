package com.emporium.ad.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "categories")
public class Category {

    @Id
    @Field("_id")
    private ObjectId id;

    @NotNull
    private String name;

    @Field("subCategories")
    private Set<Category> subCategories;

    public Category(String name) {
        this.id = ObjectId.get();
        this.name = name;
    }

    public Category(String name, Set<Category> subCategories) {
        this.id = ObjectId.get();
        this.name = name;
        this.subCategories = subCategories;
    }
}
