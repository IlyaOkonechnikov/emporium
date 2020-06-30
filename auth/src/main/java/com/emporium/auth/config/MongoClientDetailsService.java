package com.emporium.auth.config;

import com.emporium.auth.model.OauthClientDetails;
import com.emporium.auth.repository.OauthClientDetailsRepository;
import com.google.common.base.Joiner;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.filter;
import static com.google.common.collect.Sets.newHashSet;

@Component
@RequiredArgsConstructor
@SuppressWarnings("deprecation")
public class MongoClientDetailsService implements ClientDetailsService, ClientRegistrationService {

    private final OauthClientDetailsRepository oauthClientDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        try {
            final OauthClientDetails mongoClientDetails = oauthClientDetailsRepository.findByClientId(clientId);

            BaseClientDetails client = new BaseClientDetails(mongoClientDetails.getClientId(),
                    Joiner.on(",").join(mongoClientDetails.getResourceIds()),
                    Joiner.on(",").join(mongoClientDetails.getScope()),
                    Joiner.on(",").join(mongoClientDetails.getAuthorizedGrantTypes()),
                    Joiner.on(",").join(mongoClientDetails.getAuthorities()),
                    Joiner.on(",").join(mongoClientDetails.getRegisteredRedirectUri()));
            client.setAccessTokenValiditySeconds(mongoClientDetails.getAccessTokenValiditySeconds());
            client.setRefreshTokenValiditySeconds(mongoClientDetails.getRefreshTokenValiditySeconds());
            client.setClientSecret(mongoClientDetails.getClientSecret());
            return client;
        } catch (IllegalArgumentException e) {
            throw new ClientRegistrationException("No Client Details for client id", e);
        }
    }

    @Override
    public void addClientDetails(final ClientDetails clientDetails) throws ClientAlreadyExistsException {
        final OauthClientDetails mongoClientDetails = new OauthClientDetails(clientDetails.getClientId(),
                passwordEncoder.encode(clientDetails.getClientSecret()),
                clientDetails.getScope(),
                clientDetails.getResourceIds(),
                clientDetails.getAuthorizedGrantTypes(),
                clientDetails.getRegisteredRedirectUri(),
                newArrayList(clientDetails.getAuthorities()),
                clientDetails.getAccessTokenValiditySeconds(),
                clientDetails.getRefreshTokenValiditySeconds(),
                clientDetails.getAdditionalInformation(),
                null);
        oauthClientDetailsRepository.save(mongoClientDetails);
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        final OauthClientDetails mongoClientDetails = new OauthClientDetails(clientDetails.getClientId(),
                clientDetails.getClientSecret(),
                clientDetails.getScope(),
                clientDetails.getResourceIds(),
                clientDetails.getAuthorizedGrantTypes(),
                clientDetails.getRegisteredRedirectUri(),
                newArrayList(clientDetails.getAuthorities()),
                clientDetails.getAccessTokenValiditySeconds(),
                clientDetails.getRefreshTokenValiditySeconds(),
                clientDetails.getAdditionalInformation(),
                getAutoApproveScopes(clientDetails));
        final boolean result = oauthClientDetailsRepository.update(mongoClientDetails);
        if (!result) {
            throw new NoSuchClientException("No such Client Id");
        }
    }

    @Override
    public void updateClientSecret(String clientId, String secret) {
        final boolean result = oauthClientDetailsRepository.updateClientSecret(clientId, passwordEncoder.encode(secret));
        if (!result) {
            throw new NoSuchClientException("No such client id");
        }
    }

    @Override
    public void removeClientDetails(String clientId) {
        final boolean result = oauthClientDetailsRepository.deleteByClientId(clientId);
        if (!result) {
            throw new NoSuchClientException("No such client id");
        }
    }

    @Override
    public List<ClientDetails> listClientDetails() {

        return oauthClientDetailsRepository.findAll().stream()
                .map(mongoClientDetails -> new BaseClientDetails(mongoClientDetails.getClientId(),
                        Joiner.on(",").join(mongoClientDetails.getResourceIds()),
                        Joiner.on(",").join(mongoClientDetails.getScope()),
                        Joiner.on(",").join(mongoClientDetails.getAuthorizedGrantTypes()),
                        Joiner.on(",").join(mongoClientDetails.getAuthorities()),
                        Joiner.on(",").join(mongoClientDetails.getRegisteredRedirectUri())))
                .collect(Collectors.toList());
    }

    private Set<String> getAutoApproveScopes(final ClientDetails clientDetails) {
        if (clientDetails.isAutoApprove("true")) {
            return newHashSet("true");
        }
        return filter(clientDetails.getScope(), clientDetails::isAutoApprove);
    }
}