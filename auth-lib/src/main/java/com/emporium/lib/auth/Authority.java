package com.emporium.lib.auth;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Document(value = "authorities")
public class Authority {

    @Id
    private CompositeKey id;

    private static class CompositeKey implements Serializable {

        @NotNull
        private String username;

        @NotNull
        private String authority;
    }
}
