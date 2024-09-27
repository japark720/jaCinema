package com.jpworld.jacinema.member.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoResponseDTO {
    private KakaoTokenResponseDTO tokenResponse;
    private KakaoUserInfoResponseDTO infoResponse;

    public KakaoResponseDTO(KakaoTokenResponseDTO tokenResponseDTO, KakaoUserInfoResponseDTO infoResponseDTO) {
        this.tokenResponse = tokenResponseDTO;
        this.infoResponse = infoResponseDTO;
    }
}
