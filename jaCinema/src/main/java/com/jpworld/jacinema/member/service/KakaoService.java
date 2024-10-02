package com.jpworld.jacinema.member.service;

import com.jpworld.jacinema.member.kakaoDTO.KakaoAccessTokenInfoDTO;
import com.jpworld.jacinema.member.kakaoDTO.KakaoLogoutDTO;
import com.jpworld.jacinema.member.kakaoDTO.KakaoTokenResponseDTO;
import com.jpworld.jacinema.member.kakaoDTO.KakaoUserInfoResponseDTO;
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

    // 토큰 받기
    public KakaoTokenResponseDTO getKakaoAccessToken(String code) {
        KakaoTokenResponseDTO kakaoTokenResponseDTO = WebClient.create(KAUTH_TOKEN_URL_HOST)
                .post()
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

    // 사용자 정보 가져오기
    public KakaoUserInfoResponseDTO getUserInfo(String accessToken) {
        KakaoUserInfoResponseDTO kakaoUserInfoResponseDTO = WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("InvalidParameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoUserInfoResponseDTO.class)
                .block();
        return kakaoUserInfoResponseDTO;
    }

    // 로그아웃
    public KakaoLogoutDTO logout(String accessToken) {
        KakaoLogoutDTO kakaoLogoutDTO = WebClient.create(KAUTH_USER_URL_HOST)
                .post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v1/user/logout")
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("InvalidParameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoLogoutDTO.class)
                .block();
        return kakaoLogoutDTO;
    }

    // 카카오톡 연결 끊기 response가 logout과 동일해서 같이 사용..
    public KakaoLogoutDTO unlink(String accessToken) {
        KakaoLogoutDTO kakaoLogoutDTO = WebClient.create(KAUTH_USER_URL_HOST)
                .post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v1/user/unlink")
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("InvalidParameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoLogoutDTO.class)
                .block();
        return kakaoLogoutDTO;
    }

    // 토큰 정보 보기
    public KakaoAccessTokenInfoDTO accessTokenInfo(String accessToken) {
        KakaoAccessTokenInfoDTO kakaoAccessTokenInfoDTO = WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v1/user/access_token_info")
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("InvalidParameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoAccessTokenInfoDTO.class)
                .block();
        return kakaoAccessTokenInfoDTO;
    }

    // 토큰 갱신
    public KakaoTokenResponseDTO refreshToken(String refreshToken) {
        KakaoTokenResponseDTO kakaoTokenResponseDTO = WebClient.create()
                .post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "refresh_token")
                        .queryParam("cliênt_id", clientId)
                        .queryParam("refresh_token", refreshToken)
                        .queryParam("client_secret", clientSecret)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("InvalidParameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoTokenResponseDTO.class)
                .block();
        return kakaoTokenResponseDTO;
    }
}
