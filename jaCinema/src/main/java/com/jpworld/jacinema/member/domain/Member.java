package com.jpworld.jacinema.member.domain;

import com.jpworld.jacinema.types.OAuthProvider;
import com.jpworld.jacinema.types.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String nickName;
    private String gender;
    private String birthYear;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private OAuthProvider oAuthProvider;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String accessToken;
    private String refreshToken;
    private String expiresIn;
    private String refreshTokenExpiresIn;

    @Builder
    public Member(String email, String nickName, String gender, String birthYear, String phoneNumber, OAuthProvider oAuthProvider,
                  Role role, String accessToken, String refreshToken, String expiresInd, String refreshTokenExpiresIn) {
        this.email = email;
        this.nickName = nickName;
        this.gender = gender;
        this.birthYear = birthYear;
        this.phoneNumber = phoneNumber;
        this.oAuthProvider = oAuthProvider;
        this.role = role;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresInd;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }
}
