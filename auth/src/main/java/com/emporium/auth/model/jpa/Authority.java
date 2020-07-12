package com.emporium.auth.model.jpa;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Document(value = "authorities")
public class Authority {

    @Id
    private CompositeKey id;

    @Data
    public static class CompositeKey implements Serializable {

        @NotNull
        @Indexed(unique = true)
        public String username;

        @NotNull
        @Indexed(unique = true)
        private String authority;
    }
}
