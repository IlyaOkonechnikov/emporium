package com.emporium.auth.repository;

import com.emporium.auth.model.OauthClientDetails;

public interface OauthClientDetailsRepositoryBase {

    boolean deleteByClientId(String clientId);

    boolean update(OauthClientDetails mongoClientDetails);

    boolean updateClientSecret(String clientId, String newSecret);

    OauthClientDetails findByClientId(String clientId);
}
