package com.jpworld.jacinema.member.controller;


import com.jpworld.jacinema.member.domain.Member;
import com.jpworld.jacinema.member.dto.KakaoResponseDTO;
import com.jpworld.jacinema.member.dto.KakaoTokenResponseDTO;
import com.jpworld.jacinema.member.dto.KakaoUserInfoResponseDTO;
import com.jpworld.jacinema.member.service.KakaoService;
import com.jpworld.jacinema.member.service.MemberService;
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
    private final MemberService memberService;

    @GetMapping("/callback")
    public ResponseEntity<?> callback(@RequestParam("code") String code) throws IOException {
        KakaoTokenResponseDTO kakaoTokenResponseDTO = kakaoService.getAccessTokenFromKakao(code);
        String accessToken = kakaoTokenResponseDTO.getAccessToken();
        KakaoUserInfoResponseDTO userInfoDTO = kakaoService.getUserInfo(accessToken);
        KakaoResponseDTO kakaoResponseDTO = new KakaoResponseDTO(kakaoTokenResponseDTO, userInfoDTO);

        Long member = memberService.findMemberOrAddMember(kakaoResponseDTO);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
}
