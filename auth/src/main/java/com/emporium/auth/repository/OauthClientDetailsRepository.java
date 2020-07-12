package com.emporium.auth.repository;

import com.emporium.auth.model.oauth.OauthClientDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OauthClientDetailsRepository extends MongoRepository<OauthClientDetails, String>, OauthClientDetailsRepositoryBase {

}
