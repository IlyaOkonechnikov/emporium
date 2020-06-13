package com.emporium.auth.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(value = "oauth_refresh_token")
public class OauthRefreshToken {

    @Field("token_id")
    private String tokenId;

    @Field("token")
    private String token;

    @Field("authentication")
    private String authentication;
}
