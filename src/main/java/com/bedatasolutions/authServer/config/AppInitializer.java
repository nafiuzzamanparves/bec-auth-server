package com.bedatasolutions.authServer.config;

import com.bedatasolutions.authServer.dao.client.Client;
import com.bedatasolutions.authServer.repository.client.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Component
public class AppInitializer implements CommandLineRunner {

    private final JpaRegisteredClientRepository registeredClientRepository;
    private final ClientRepository clientRepository;

    public AppInitializer(JpaRegisteredClientRepository registeredClientRepository, ClientRepository clientRepository) {
        this.registeredClientRepository = registeredClientRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(String... args) {
        final String clientId = "oidc-client";
        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(clientId)
                .clientSecret("{noop}secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://localhost:8080/login/oauth2/code/oidc-client")
                .postLogoutRedirectUri("http://localhost:8080/logout")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofMinutes(30)).build())
                .build();

        Optional<Client> existingClient = clientRepository.findByClientId(clientId);
        if (existingClient.isEmpty()) {
            registeredClientRepository.save(oidcClient);
            System.out.println("Registered Client saved to the database!");
        } else {
            System.out.println("Client already exists!");
        }
    }
}