package com.jpworld.jacinema.member.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

@Entity
@Getter
@RequiredArgsConstructor
public class MemberToken extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "member_token_id")
    private Long id;

    private String accessToken;
    private String expiresIn;
    private String refreshToken;
    private String refreshTokenExpiresIn;

    @Builder
    public MemberToken(String accessToken, String expiresIn, @Nullable String refreshToken, @Nullable String refreshTokenExpiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }
}
