package com.eric6166.gateway.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SecurityConfig {

    SecurityProps securityProps;
    OAuth2ClientProperties oAuth2ClientProperties;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        return serverHttpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers(securityProps.getSkipUrls()).permitAll()
                        .anyExchange().authenticated())
                .oauth2Login(Customizer.withDefaults())
//                .oauth2Login(oAuth2Login -> oAuth2Login.clientRegistrationRepository(clientRegistrationRepository()))
//                .oauth2Login(oauth2 -> oauth2
//                        .authenticationMatcher(new PathPatternParserServerWebExchangeMatcher("/login/oauth2/code/{registrationId}")))
//                .oauth2Login(oauth2 -> oauth2
//                        .authenticationMatcher(new PathPatternParserServerWebExchangeMatcher("/login/oauth2/callback/{registrationId}")))
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .oauth2Client(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .build();
    }


//    @Bean
//    public ReactiveClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryReactiveClientRegistrationRepository(microservicesAdminClientRegistration());
//    }
////
//    public ClientRegistration microservicesAdminClientRegistration() {
//        var keycloak = oAuth2ClientProperties.getProvider().get("keycloak");
//        var microservicesAuthClient = oAuth2ClientProperties.getRegistration().get("microservices-admin-client");
//
//        return ClientRegistration.withRegistrationId("microservices-admin-client")
//                .clientId(microservicesAuthClient.getClientId())
//                .clientSecret(microservicesAuthClient.getClientSecret())
////                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .clientAuthenticationMethod(new ClientAuthenticationMethod(microservicesAuthClient.getClientAuthenticationMethod()))
////                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .authorizationGrantType(new AuthorizationGrantType(microservicesAuthClient.getAuthorizationGrantType()))
//                .redirectUri(microservicesAuthClient.getRedirectUri())
//                .scope(microservicesAuthClient.getScope())
//                .authorizationUri(keycloak.getAuthorizationUri())
//                .tokenUri(keycloak.getTokenUri())
//                .userInfoUri(keycloak.getUserInfoUri())
////                .userNameAttributeName(IdTokenClaimNames.SUB)
//                .userNameAttributeName(keycloak.getUserNameAttribute())
//                .jwkSetUri(keycloak.getJwkSetUri())
//                .clientName(microservicesAuthClient.getClientName())
//                .build();
//    }

}
