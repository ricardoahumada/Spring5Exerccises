package com.bananaapps.bananamusic.controller;

import com.bananaapps.bananamusic.domain.StatusMessage;
import com.bananaapps.bananamusic.domain.music.Song;
import com.bananaapps.bananamusic.domain.music.SongCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@Sql(value = "classpath:testing.sql")
//@Sql(value = "classpath:testing_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({"dev"})
class SongServiceControllerTest {

    @Autowired
    private SongServiceController controller;

    @Test
    void getSongById() {
        Long id = 1L;
        ResponseEntity<Song> response = controller.getSongById(id);
        System.out.println("response:" + response.getBody());

        assertThat(response.getStatusCode().value())
                .isEqualTo(HttpStatus.OK.value());

        assertThat(response.getBody())
                .isNotNull();

        assertThat(response.getBody().getId())
                .isEqualTo(id);

    }

    @Test
    void getSongsByKeywords() {
        String keyword = "a";
        ResponseEntity<Collection<Song>> response = controller.getSongsByKeywords(keyword);
        System.out.println("response:" + response.getBody());

        assertThat(response.getStatusCode().value())
                .isEqualTo(HttpStatus.OK.value());

        assertThat(response.getBody())
                .isNotNull();
    }

    @Test
    void createSong() {
        Song newSong = new Song("Sonata", "Beethoven", "2023-01-04", new BigDecimal("13.99"), SongCategory.CLASSICAL);

        ResponseEntity<Song> response = controller.createSong(newSong);
        System.out.println("***** response:" + response);

        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getBody().getId()).isGreaterThan(0);
    }

    @Test
    void saveSongs() {
        List<Song> songs = List.of(
                new Song("New One", "New", "2013-01-04", new BigDecimal("13.99"), SongCategory.RAP),
                new Song("Another New One", "Newer", "2013-02-05", new BigDecimal("14.99"), SongCategory.POP)
        );

        ResponseEntity<StatusMessage> response = controller.saveSongs(songs);
        assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
    }
}