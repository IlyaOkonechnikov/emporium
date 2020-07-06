package com.emporium.auth.config;

import com.emporium.auth.model.OauthClientDetails;
import com.emporium.auth.repository.OauthClientDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@SuppressWarnings("deprecation")
public class MongoClientDetailsService implements ClientDetailsService, ClientRegistrationService {

    private final OauthClientDetailsRepository oauthClientDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        try {
            OauthClientDetails mongoClientDetails = oauthClientDetailsRepository.findByClientId(clientId);
            BaseClientDetails client = new BaseClientDetails(mongoClientDetails.getClientId(),
                    toCommaSeparatedString(mongoClientDetails.getResourceIds()),
                    toCommaSeparatedString(mongoClientDetails.getScope()),
                    toCommaSeparatedString(mongoClientDetails.getAuthorizedGrantTypes()),
                    toCommaSeparatedString(mongoClientDetails.getAuthorities()),
                    toCommaSeparatedString(mongoClientDetails.getRegisteredRedirectUri()));
            client.setAccessTokenValiditySeconds(mongoClientDetails.getAccessTokenValiditySeconds());
            client.setRefreshTokenValiditySeconds(mongoClientDetails.getRefreshTokenValiditySeconds());
            client.setClientSecret(mongoClientDetails.getClientSecret());
            return client;
        } catch (IllegalArgumentException e) {
            throw new ClientRegistrationException("No client details found for client id: " + clientId, e);
        }
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) {
        OauthClientDetails oauthClientDetails = OauthClientDetails.builder()
                .clientId(clientDetails.getClientId())
                .clientSecret(passwordEncoder.encode(clientDetails.getClientSecret()))
                .scope(clientDetails.getScope())
                .resourceIds(clientDetails.getResourceIds())
                .authorizedGrantTypes(clientDetails.getAuthorizedGrantTypes())
                .registeredRedirectUris(clientDetails.getRegisteredRedirectUri())
                .authorities(new ArrayList<>(clientDetails.getAuthorities()))
                .accessTokenValiditySeconds(clientDetails.getAccessTokenValiditySeconds())
                .refreshTokenValiditySeconds(clientDetails.getRefreshTokenValiditySeconds())
                .additionalInformation(clientDetails.getAdditionalInformation())
                .build();
        oauthClientDetailsRepository.save(oauthClientDetails);
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) {
        OauthClientDetails oauthClientDetails = OauthClientDetails.builder()
                .clientId(clientDetails.getClientId())
                .clientSecret(clientDetails.getClientSecret())
                .scope(clientDetails.getScope())
                .resourceIds(clientDetails.getResourceIds())
                .authorizedGrantTypes(clientDetails.getAuthorizedGrantTypes())
                .registeredRedirectUris(clientDetails.getRegisteredRedirectUri())
                .authorities(new ArrayList<>(clientDetails.getAuthorities()))
                .accessTokenValiditySeconds(clientDetails.getAccessTokenValiditySeconds())
                .refreshTokenValiditySeconds(clientDetails.getRefreshTokenValiditySeconds())
                .additionalInformation(clientDetails.getAdditionalInformation())
                .autoApproveScopes(getAutoApproveScopes(clientDetails))
                .build();
        boolean result = oauthClientDetailsRepository.update(oauthClientDetails);
        if (!result) {
            throw new NoSuchClientException("No such client id: " + clientDetails.getClientId());
        }
    }

    @Override
    public void updateClientSecret(String clientId, String secret) {
        boolean result = oauthClientDetailsRepository.updateClientSecret(clientId, passwordEncoder.encode(secret));
        if (!result) {
            throw new NoSuchClientException("No such client id: " + clientId);
        }
    }

    @Override
    public void removeClientDetails(String clientId) {
        boolean result = oauthClientDetailsRepository.deleteByClientId(clientId);
        if (!result) {
            throw new NoSuchClientException("No such client id: " + clientId);
        }
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        return oauthClientDetailsRepository.findAll().stream()
                .map(mongoClientDetails -> new BaseClientDetails(mongoClientDetails.getClientId(),
                        toCommaSeparatedString(mongoClientDetails.getResourceIds()),
                        toCommaSeparatedString(mongoClientDetails.getScope()),
                        toCommaSeparatedString(mongoClientDetails.getAuthorizedGrantTypes()),
                        toCommaSeparatedString(mongoClientDetails.getAuthorities()),
                        toCommaSeparatedString(mongoClientDetails.getRegisteredRedirectUri())))
                .collect(Collectors.toList());
    }

    private Set<String> getAutoApproveScopes(ClientDetails clientDetails) {
        if (clientDetails.isAutoApprove("true")) {
            return new HashSet<>(Collections.singletonList("true"));
        }
        return clientDetails.getScope().stream().filter(clientDetails::isAutoApprove).collect(Collectors.toSet());
    }

    private String toCommaSeparatedString(Iterable<?> collection) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<?> iterator = collection.iterator();
        if (iterator.hasNext()) {
            stringBuilder.append(iterator.next());
            while (iterator.hasNext()) {
                stringBuilder.append(",");
                stringBuilder.append(iterator.next());
            }
        }
        return stringBuilder.toString();
    }
}