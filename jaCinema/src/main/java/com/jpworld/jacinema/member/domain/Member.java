package com.jpworld.jacinema.member.domain;

import com.jpworld.jacinema.oauth.OAuthProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String nickName;
    private String gender;
    private String birthYear;
    private String phoneNumber;
    private OAuthProvider oAuthProvider;

}
