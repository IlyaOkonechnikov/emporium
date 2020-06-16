package com.emporium.auth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(value = "oauth_client_token")
public class OauthClientToken {

    @Id
    @Field("_id")
    private String id;

    @Field("authentication_id")
    @Indexed(unique = true)
    private String authenticationId;

    @Field("token_id")
    private String tokenId;

    @Field("token")
    private String token;

    @Field("user_name")
    private String username;

    @Field("scope")
    private String scope;
}
