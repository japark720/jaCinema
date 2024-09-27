package com.jpworld.jacinema.member.controller;


import com.jpworld.jacinema.member.dto.KakaoUserInfoResponseDTO;
import com.jpworld.jacinema.member.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao")
public class KakaoLoginController {

    private final KakaoService kakaoService;

    @GetMapping("/callback")
    public ResponseEntity<?> callback(@RequestParam("code") String code) throws IOException {
        String accessToken = kakaoService.getAccessTokenFromKakao(code);
        KakaoUserInfoResponseDTO userInfo = kakaoService.getUserInfo(accessToken);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }
}
