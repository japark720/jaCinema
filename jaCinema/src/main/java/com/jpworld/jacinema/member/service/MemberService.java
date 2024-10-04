package com.jpworld.jacinema.member.service;

import com.jpworld.jacinema.member.domain.Member;
import com.jpworld.jacinema.member.domain.MemberToken;
import com.jpworld.jacinema.member.kakaoDTO.KakaoTokenResponseDTO;
import com.jpworld.jacinema.member.kakaoDTO.KakaoUserInfoResponseDTO;
import com.jpworld.jacinema.member.repository.MemberRepository;
import com.jpworld.jacinema.types.OAuthProvider;
import com.jpworld.jacinema.types.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long findMemberOrAddMember(KakaoTokenResponseDTO token, KakaoUserInfoResponseDTO userInfo) {
        return memberRepository.findByEmail(userInfo.getKakaoAccount().getEmail())
                .map(Member::getId)
                .orElseGet(() -> addMember(token, userInfo));
    }

    private Long addMember(KakaoTokenResponseDTO token, KakaoUserInfoResponseDTO userInfo) {
        KakaoUserInfoResponseDTO.KakaoAccount account = userInfo.getKakaoAccount();

        MemberToken memberToken = MemberToken.builder()
                .accessToken(token.getAccessToken())
                .expiresIn(token.getExpiresIn())
                .refreshToken(token.getRefreshToken())
                .refreshTokenExpiresIn(token.getRefreshTokenExpiresIn())
                .build();

        Member member = Member.builder()
                .email(account.getEmail())
                .nickName(account.getProfile().getNickName())
                .gender(account.getGender())
                .birthYear(account.getBirthYear())
                .phoneNumber(account.getPhoneNumber())
                .oAuthProvider(OAuthProvider.KAKAO)
                .role(Role.USER)
                .memberToken(memberToken)
                .build();

        return memberRepository.save(member).getId();
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Transactional
    public Member updateMemberToken(Long id, KakaoTokenResponseDTO token) {

        final Member member = memberRepository.findById(id).orElse(null);

        MemberToken memberToken = MemberToken.builder()
                 .accessToken(token.getAccessToken())
                 .expiresIn(token.getExpiresIn())
                 .refreshToken(token.getRefreshToken())
                 .refreshTokenExpiresIn(token.getRefreshTokenExpiresIn())
                 .build();
         return member.updateMemberToken(memberToken);
    }
}
