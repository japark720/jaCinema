package com.jpworld.jacinema.member.service;

import com.jpworld.jacinema.member.dto.KakaoTokenResponseDTO;
import com.jpworld.jacinema.member.dto.KakaoUserInfoResponseDTO;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class KakaoService {

    @Value("${spring.oauth.kakao.client-id}")
    private String clientId;

    @Value("${spring.oauth.kakao.url.auth}")
    private String KAUTH_TOKEN_URL_HOST;

    @Value("${spring.oauth.kakao.url.api}")
    private String KAUTH_USER_URL_HOST;

    @Value("${spring.oauth.kakao.client-secret}")
    private String clientSecret;

    public KakaoTokenResponseDTO getAccessTokenFromKakao(String code) {
        KakaoTokenResponseDTO kakaoTokenResponseDTO = WebClient.create(KAUTH_TOKEN_URL_HOST).post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientId)
                        .queryParam("code", code)
                        .queryParam("client_secret", clientSecret)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new RuntimeException("Internal Server error")))
                .bodyToMono(KakaoTokenResponseDTO.class)
                .block();
        return kakaoTokenResponseDTO;
    }

    public KakaoUserInfoResponseDTO getUserInfo(String accessToken) {
        KakaoUserInfoResponseDTO kakaoUserInfoResponseDTO = WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("InvalidParameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoUserInfoResponseDTO.class)
                .block();
        return kakaoUserInfoResponseDTO;
    }
}
