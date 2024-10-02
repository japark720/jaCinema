package com.jpworld.jacinema.member.kakaoDTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class KakaoAccessTokenInfoDTO {

    Long id;
    Integer expiresIn;
    Integer appId;
}
