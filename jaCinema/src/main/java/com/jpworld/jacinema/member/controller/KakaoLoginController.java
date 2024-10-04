package com.jpworld.jacinema.member.controller;


import com.jpworld.jacinema.member.domain.Member;
import com.jpworld.jacinema.member.kakaoDTO.*;
import com.jpworld.jacinema.member.service.KakaoService;
import com.jpworld.jacinema.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao")
public class KakaoLoginController {

    private final KakaoService kakaoService;
    private final MemberService memberService;

    /**
     * 인가 코드 받기
     * 토큰 받기
     * 사용자 정보 가져오기
     * @param code
     */
    @GetMapping("/callback")
    public ResponseEntity<?> callback(@RequestParam("code") String code) throws IOException {
        KakaoTokenResponseDTO kakaoTokenResponseDTO = kakaoService.getKakaoAccessToken(code);
        String accessToken = kakaoTokenResponseDTO.getAccessToken();
        KakaoUserInfoResponseDTO userInfoDTO = kakaoService.getUserInfo(accessToken);

        Long member = memberService.findMemberOrAddMember(kakaoTokenResponseDTO, userInfoDTO);
        Member findMember = memberService.findMemberById(member);
        return new ResponseEntity<>(findMember, HttpStatus.OK);
    }

    /**
     * 로그아웃
     * @param accessToken
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam("accessToken")String accessToken) throws IOException {
        KakaoLogoutDTO kakaoLogoutDTO = kakaoService.logout(accessToken);
        return new ResponseEntity<>(kakaoLogoutDTO, HttpStatus.OK);
    }

    /**
     * 연결 끊기
     * @param accessToken
     */
    @PostMapping("/unlink")
    public ResponseEntity<?> unlink(@RequestParam("accessToken")String accessToken) throws IOException {
        KakaoLogoutDTO kakaoLogoutDTO = kakaoService.unlink(accessToken);
        return new ResponseEntity<>(kakaoLogoutDTO, HttpStatus.OK);
    }

    /**
     * 토큰 정보 보기
     * @param accessToken
     */
    @GetMapping("/accessTokenInfo")
    public ResponseEntity<?> accessTokenInfo(@RequestParam("accessToken")String accessToken) throws IOException {
        KakaoAccessTokenInfoDTO kakaoAccessTokenInfoDTO = kakaoService.accessTokenInfo(accessToken);
        return new ResponseEntity<>(kakaoAccessTokenInfoDTO, HttpStatus.OK);
    }

    /**
     * 토큰 갱신하기
     * @param refreshToken
     */
    @PostMapping("/oauth/token")
    public ResponseEntity<?> oauthToken(@RequestParam("id") Long memberId, @RequestParam("refresh_token")String refreshToken) throws IOException {
        KakaoTokenResponseDTO kakaoTokenResponseDTO = kakaoService.refreshToken(refreshToken);
        memberService.updateMemberToken(memberId, kakaoTokenResponseDTO);
        return new ResponseEntity<>(kakaoTokenResponseDTO, HttpStatus.OK);
    }
}
