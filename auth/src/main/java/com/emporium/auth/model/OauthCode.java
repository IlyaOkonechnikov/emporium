package com.emporium.auth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(value = "oauth_code")
public class OauthCode {

    @Id
    @Field("_id")
    private String id;

    @Field("code")
    private String code;
}
