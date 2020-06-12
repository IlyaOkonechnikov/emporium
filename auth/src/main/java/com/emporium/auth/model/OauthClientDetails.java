package com.emporium.auth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

@Data
public class OauthClientDetails {

    @Id
    @Field("client_id")
    private String clientId;

    @Field("resource_ids")
    private String resourceIds;

    @NotNull
    @Field("client_secret")
    private String clientSecret;

    @Field("scope")
    private String scope;

    @Field("authorized_grant_types")
    private String authorizedGrantTypes;

    @Field("web_server_redirect_uri")
    private String webServerRedirectUri;

    @Field("authorities")
    private String authorities;

    @Field("access_token_validity")
    private Integer accessTokenValidity;

    @Field("refresh_token_validity")
    private Integer refreshTokenValidity;

    @Field("additional_information")
    private String additionalInformation;

    @Field("autoapprove")
    private String autoApprove;
}
