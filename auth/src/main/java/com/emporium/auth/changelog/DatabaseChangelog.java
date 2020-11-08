package com.emporium.auth.changelog;

import com.emporium.auth.model.oauth.OauthClientDetails;
import com.kuliginstepan.mongration.annotation.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Changelog
public class DatabaseChangelog {

    @Changeset(order = 1, id = "initOauthClientDetails", author = "iokonechnikov")
    public void initOauthClientDetails(MongoTemplate mongoTemplate) {
//        OauthClientDetails oauthClientDetails = OauthClientDetails.builder()
//                .clientId("clientId")
//                .clientSecret("{bcrypt}$2a$10$vCXMWCn7fDZWOcLnIEhmK.74dvK1Eh8ae2WrWlhr2ETPLoxQctN4.")
//                .scope(Stream.of("read", "write").collect(Collectors.toSet()))
//                .authorizedGrantTypes(Stream.of("password", "refresh_token", "client_credentials").collect(Collectors.toSet()))
//                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_CLIENT")))
//                .accessTokenValiditySeconds(300)
//                .build();
//        mongoTemplate.save(oauthClientDetails);
    }
}