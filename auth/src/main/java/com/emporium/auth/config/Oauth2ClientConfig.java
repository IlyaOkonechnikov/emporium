package com.emporium.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Oauth2ClientConfig {

    private final String registrationId;

    public Oauth2ClientConfig(@Value("${spring.security.oauth2.client.registration.emporium.id}") String registrationId) {
        this.registrationId = registrationId;
    }

    @Bean
    ReactiveClientRegistrationRepository registrationRepository(
            @Value("${spring.security.oauth2.client.provider.emporium.token-uri}") String tokenUri,
            @Value("${spring.security.oauth2.client.registration.emporium.client-id}") String clientId,
            @Value("${spring.security.oauth2.client.registration.emporium.client-secret}") String clientSecret,
            @Value("${spring.security.oauth2.client.registration.emporium.authorization-grant-type}") String grantType) {
        ClientRegistration registration = ClientRegistration
                .withRegistrationId(registrationId)
                .tokenUri(tokenUri)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .authorizationGrantType(new AuthorizationGrantType(grantType))
                .build();
        return new InMemoryReactiveClientRegistrationRepository(registration);
    }

    @Bean
    WebClient webClient(ReactiveClientRegistrationRepository registrationRepository) {
        InMemoryReactiveOAuth2AuthorizedClientService authorizedClientService =
                new InMemoryReactiveOAuth2AuthorizedClientService(registrationRepository);
        AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager clientManager =
                new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(registrationRepository, authorizedClientService);
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth =
                new ServerOAuth2AuthorizedClientExchangeFilterFunction(clientManager);
        oauth.setDefaultClientRegistrationId(registrationId);
        return WebClient.builder()
                .filter(oauth)
                .build();
    }
}
