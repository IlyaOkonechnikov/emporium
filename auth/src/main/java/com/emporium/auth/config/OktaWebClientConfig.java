package com.emporium.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OktaWebClientConfig {

  public static final String DEFAULT_CLIENT_REGISTRATION_ID = "okta";

  @Bean
  WebClient webClient(ClientRegistrationRepository clientRegistrations, OAuth2AuthorizedClientRepository authorizedClients) {
    ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
        clientRegistrations, authorizedClients
    );
    oauth2.setDefaultOAuth2AuthorizedClient(true);
    oauth2.setDefaultClientRegistrationId(DEFAULT_CLIENT_REGISTRATION_ID);
    return WebClient.builder()
        .apply(oauth2.oauth2Configuration())
        .build();
  }
}