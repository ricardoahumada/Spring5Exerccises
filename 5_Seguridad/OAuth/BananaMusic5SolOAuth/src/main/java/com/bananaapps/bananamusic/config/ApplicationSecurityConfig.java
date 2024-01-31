package com.bananaapps.bananamusic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ApplicationSecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/catalog/**").permitAll()
                .antMatchers(HttpMethod.POST, "/catalog/**").hasAuthority("SCOPE_music.write")
                .mvcMatchers("/cart/**").authenticated()
                .mvcMatchers("/cart/**").hasAuthority("SCOPE_music.read")
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}