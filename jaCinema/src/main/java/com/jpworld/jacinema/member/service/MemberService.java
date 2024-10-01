package com.jpworld.jacinema.member.service;

import com.jpworld.jacinema.member.domain.Member;
import com.jpworld.jacinema.member.kakaoDTO.KakaoResponseDTO;
import com.jpworld.jacinema.member.kakaoDTO.KakaoTokenResponseDTO;
import com.jpworld.jacinema.member.kakaoDTO.KakaoUserInfoResponseDTO;
import com.jpworld.jacinema.member.repository.MemberRepository;
import com.jpworld.jacinema.types.OAuthProvider;
import com.jpworld.jacinema.types.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long findMemberOrAddMember(KakaoResponseDTO kakaoResponseDTO) {
        return memberRepository.findByEmail(kakaoResponseDTO.getInfoResponse().getKakaoAccount().getEmail())
                .map(Member::getId).orElseGet(() -> addMember(kakaoResponseDTO));
    }

    private Long addMember(KakaoResponseDTO kakaoResponseDTO) {

        KakaoUserInfoResponseDTO.KakaoAccount userInfoAccount = kakaoResponseDTO.getInfoResponse().getKakaoAccount();
        KakaoTokenResponseDTO token = kakaoResponseDTO.getTokenResponse();

        Member member = Member.builder()
                .email(userInfoAccount.getEmail())
                .nickName(userInfoAccount.getProfile().getNickName())
                .gender(userInfoAccount.getGender())
                .birthYear(userInfoAccount.getBirthYear())
                .phoneNumber(userInfoAccount.getPhoneNumber())
                .oAuthProvider(OAuthProvider.KAKAO)
                .role(Role.USER)
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .expiresInd(token.getExpiresIn())
                .refreshTokenExpiresIn(token.getRefreshTokenExpiresIn())
                .build();

        return memberRepository.save(member).getId();
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }
}
