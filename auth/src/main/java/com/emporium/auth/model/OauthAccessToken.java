package com.emporium.auth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(value = "oauth_access_token")
public class OauthAccessToken {

    @Id
    @Field("token_id")
    private String tokenId;

    @Field("token")
    private String token;

    @Field("authentication_id")
    private String authenticationId;

    @Field("user_name")
    private String username;

    @Field("client_id")
    private String clientId;

    @Field("authentication")
    private String authentication;

    @Field("refresh_token")
    private String refreshToken;
}
