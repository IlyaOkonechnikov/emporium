package com.emporium.auth.repository;

import com.emporium.auth.model.OauthClientDetails;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OauthClientDetailsRepositoryImpl implements OauthClientDetailsRepositoryBase {

    private final MongoTemplate mongoTemplate;

    @Override
    public boolean deleteByClientId(String clientId) {
        Query query = Query.query(Criteria.where("client_id").is(clientId));
        DeleteResult deleteResult = mongoTemplate.remove(query, OauthClientDetails.class);
        return deleteResult.getDeletedCount() == 1;
    }

    @Override
    public boolean update(OauthClientDetails mongoClientDetails) {
        Query query = Query.query(Criteria.where("client_id").is(mongoClientDetails.getClientId()));
        Update update = Update.update("scope", mongoClientDetails.getScope())
                .set("access_token_validity_seconds", mongoClientDetails.getAccessTokenValiditySeconds())
                .set("refresh_token_validity_seconds", mongoClientDetails.getRefreshTokenValiditySeconds())
                .set("additional_information", mongoClientDetails.getAdditionalInformation())
                .set("resource_ids", mongoClientDetails.getResourceIds())
                .set("authorized_grant_types", mongoClientDetails.getAuthorizedGrantTypes())
                .set("authorities", mongoClientDetails.getAuthorities())
                .set("auto_approve_scopes", mongoClientDetails.getAutoApproveScopes())
                .set("registered_redirect_uris", mongoClientDetails.getRegisteredRedirectUri());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, OauthClientDetails.class);
        return updateResult.getModifiedCount() == 1;
    }

    @Override
    public boolean updateClientSecret(String clientId, String newSecret) {
        Query query = Query.query(Criteria.where("client_id").is(clientId));
        Update update = Update.update("client_secret", newSecret);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, OauthClientDetails.class);
        return updateResult.getModifiedCount() == 1;
    }

    @Override
    public OauthClientDetails findByClientId(String clientId) {
        Query query = Query.query(Criteria.where("client_id").is(clientId));
        OauthClientDetails mongoClientDetails = mongoTemplate.findOne(query, OauthClientDetails.class);
        if (mongoClientDetails == null) {
            throw new IllegalArgumentException("No such client id: " + clientId);
        }
        return mongoClientDetails;
    }
}
