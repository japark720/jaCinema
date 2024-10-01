package com.jpworld.jacinema.member.kakaoDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoUserInfoResponseDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class KakaoAccount {

        @JsonProperty("profile")
        private Profile profile;

        @JsonProperty("email")
        private String email;

        @JsonProperty("birthyear")
        private String birthYear;

        @JsonProperty("gender")
        private String gender;

        @JsonProperty("phone_number")
        private String phoneNumber;

        @Getter
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public class Profile {

            @JsonProperty("nickname")
            private String nickName;
        }
    }
}
