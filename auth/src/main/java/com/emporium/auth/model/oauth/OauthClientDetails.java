package com.emporium.auth.model.oauth;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@Builder
@SuppressWarnings("deprecation")
@Document(value = "oauth_client_details")
public class OauthClientDetails implements ClientDetails {

    @Id
    @Field("_id")
    private String id;

    @Field("client_id")
    @Indexed(unique = true)
    private String clientId;

    @NotNull
    @Field("client_secret")
    private String clientSecret;

    @Field("scope")
    private Set<String> scope;

    @Field("resource_ids")
    private Set<String> resourceIds;

    @Field("authorized_grant_types")
    private Set<String> authorizedGrantTypes;

    @Field("registered_redirect_uris")
    private Set<String> registeredRedirectUris;

    @Field("authorities")
    private List<GrantedAuthority> authorities;

    @Field("access_token_validity_seconds")
    private Integer accessTokenValiditySeconds;

    @Field("refresh_token_validity_seconds")
    private Integer refreshTokenValiditySeconds;

    @Field("additional_information")
    private transient Map<String, Object> additionalInformation;

    @Field("auto_approve_scopes")
    private Set<String> autoApproveScopes;

    public Set<String> getScope() {
        return Objects.isNull(scope) ? Collections.emptySet() : scope;
    }

    public Set<String> getResourceIds() {
        return Objects.isNull(resourceIds) ? Collections.emptySet() : resourceIds;
    }

    public Set<String> getAuthorizedGrantTypes() {
        return Objects.isNull(authorizedGrantTypes) ? Collections.emptySet() : authorizedGrantTypes;
    }

    public List<GrantedAuthority> getAuthorities() {
        return Objects.isNull(authorities) ? Collections.emptyList() : authorities;
    }

    public Map<String, Object> getAdditionalInformation() {
        return Objects.isNull(additionalInformation) ? Collections.emptyMap() : additionalInformation;
    }

    @Override
    public boolean isScoped() {
        return this.scope != null && !this.scope.isEmpty();
    }

    @Override
    public boolean isSecretRequired() {
        return this.clientSecret != null;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return Objects.isNull(registeredRedirectUris) ? Collections.emptySet() : registeredRedirectUris;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return Objects.nonNull(autoApproveScopes) &&
                autoApproveScopes.stream().anyMatch(auto -> auto.equals("true") || scope.matches(auto));
    }
}