package com.bananaapps.bananamusic.domain.auth;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthResponse {
    private String email;
    private String accessToken;
}