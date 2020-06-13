package com.emporium.auth.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(value = "oauth_code")
public class OauthCode {

    @Field("code")
    private String code;
}
