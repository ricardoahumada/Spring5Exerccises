package com.bananaapps.bananamusic.controller;

import com.bananaapps.bananamusic.domain.music.Song;
import com.bananaapps.bananamusic.domain.music.SongCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql("classpath:testing.sql")
//@Sql(value = "classpath:testing_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({"dev"})
class SongServiceControllerTest_RestTemplate {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void getSongsByKeywords() throws Exception {
        String keyword = "a";

        ResponseEntity<String> response = restTemplate
                .getForEntity("http://localhost:" + port + "/catalog/keyword/" + keyword,
                        String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Diva");
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
    }


    @Test
    void createSong() throws Exception {
        Song newSong = new Song("Sonata", "Beethoven", "2023-01-04", new BigDecimal("13.99"), SongCategory.CLASSICAL);

        final String baseUrl = "http://localhost:" + port + "/catalog";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("ACCEPT", "application/json");

        HttpEntity<Song> request = new HttpEntity<>(newSong, headers);

        ResponseEntity<Song> response = this.restTemplate.postForEntity(uri, request, Song.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Song rSong = response.getBody();
        assertThat(rSong.getId()).isGreaterThan(0);

        System.out.println("\n\nresponse: " + response + "\n\n");
    }
}