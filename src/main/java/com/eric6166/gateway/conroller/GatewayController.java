package com.eric6166.gateway.conroller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GatewayController {

//    OAuth2AuthorizedClient oAuth2AuthorizedClient;

    @PostMapping(value = "/token")
    public Mono<String> getToken() {
        return Mono.just("");
    }

//    @GetMapping(value = "/token2")
//    public Mono<String> getToken2(OAuth2AuthorizedClient authorizedClient) {
//        return Mono.just(authorizedClient.getAccessToken().getTokenValue());
//    }
}
