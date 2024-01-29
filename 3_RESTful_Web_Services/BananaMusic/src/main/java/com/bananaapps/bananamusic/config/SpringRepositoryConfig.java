package com.bananaapps.bananamusic.config;

import com.bananaapps.bananamusic.persistence.music.JpaSongRepository;
import com.bananaapps.bananamusic.persistence.music.SongRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringRepositoryConfig {
    @Bean
    public SongRepository jpaSongRepository() {
        return new JpaSongRepository();
    }

}