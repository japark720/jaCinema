package com.jpworld.jacinema.member.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class OAuthLoginController {

    @Value("${spring.oauth.kakao.client-id}")
    private String kakaoClientId;

    @Value("${spring.oauth.kakao.url.redirect-uri}")
    private String kakaoRedirectUri;

    @Value("${spring.oauth.kakao.url.auth}")
    private String kakaoAuthUrl;

    @GetMapping
    public String login(Model model) {
        String kakaoLocation = kakaoAuthUrl +
                "/oauth/authorize?response_type=code&client_id=" + kakaoClientId
                + "&redirect_uri=" + kakaoRedirectUri;

        model.addAttribute("kakaoLocation", kakaoLocation);
        return "login";
    }
}
